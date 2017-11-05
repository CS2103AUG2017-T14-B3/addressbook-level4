package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.event.util.CommandUtil.createEditedEvent;
import static seedu.address.logic.commands.event.util.CommandUtil.createEditedPerson;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.UndoableCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.exceptions.EventNotFoundException;
import seedu.address.model.event.exceptions.EventTimeClashException;
import seedu.address.model.person.Person;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

/**
 * Tags a person in an event.
 */
public class DeletePersonTagCommand extends UndoableCommand {
    public static final String COMMAND_WORD = "det";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a person tag by the index in the "
            + "event's person listing from an event by the index number used in the last event listing. "
            + "\nParameters: INDEX_OF_EVENT (must be a positive integer) INDEX_OF_PERSON (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 2 ";

    public static final String MESSAGE_DELETE_TAG_SUCCESS = "%1$s\n has been removed from %2$s.";
    public static final String MESSAGE_ERROR = "Error. Data storage corruption is likely.";
    public static final String MESSAGE_EVENT_MISSING = "The event to be tagged does not exist.";
    public static final String MESSAGE_PERSON_MISSING = "The person index given does not exist.";
    public static final String MESSAGE_NO_TAGS = "This event has no tagged people.";

    private final Index eventIndex;
    private final Index personIndex;

    private EditEventCommand.EditEventDescriptor editEventDescriptor;
    private EditCommand.EditPersonDescriptor editPersonDescriptor;

    /**
     * @param eventIndex   index of the event in the filtered event list to add tag
     * @param personIndex   index of the person in the filtered person list to be tagged in the event
     */
    public DeletePersonTagCommand(Index eventIndex, Index personIndex) {
        requireNonNull(eventIndex);
        requireNonNull(personIndex);

        this.eventIndex = eventIndex;
        this.personIndex = personIndex;
        this.editEventDescriptor = new EditEventCommand.EditEventDescriptor();
        this.editPersonDescriptor = new EditCommand.EditPersonDescriptor();
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {

        List<ReadOnlyEvent> lastShownEventList = model.getFilteredEventList();

        if (eventIndex.getZeroBased() >= lastShownEventList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
        }

        List<ReadOnlyPerson> lastShownPersonList = model.getFilteredPersonList();

        if ((personIndex.getZeroBased() >= lastShownPersonList.size())) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        ReadOnlyEvent eventToTag = lastShownEventList.get(eventIndex.getZeroBased());
        ReadOnlyPerson personToTag = lastShownPersonList.get(personIndex.getZeroBased());

        Event event = createEditedEvent(eventToTag, editEventDescriptor);
        Person person = createEditedPerson(personToTag, editPersonDescriptor);

        UniquePersonList eventPersonList = new UniquePersonList();

        try {
            List<ReadOnlyPerson> personListData = event.getPersonList();
            if (personListData.isEmpty()) {
                throw new CommandException(MESSAGE_NO_TAGS);
            }
            eventPersonList.setPersons(personListData);
            eventPersonList.remove(person);
            event.setPersonList(eventPersonList.asObservableList());
            model.updateEvent(eventToTag, event);
        } catch (PersonNotFoundException pnf) {
            throw new CommandException(MESSAGE_PERSON_MISSING);
        } catch (DuplicatePersonException dpe) {
            throw new CommandException(MESSAGE_ERROR);
        } catch (EventNotFoundException enf) {
            throw new CommandException(MESSAGE_EVENT_MISSING);
        } catch (EventTimeClashException etc) {
            throw new CommandException(MESSAGE_ERROR);
        }
        // update event card //upmodel.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_DELETE_TAG_SUCCESS, person, event));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePersonTagCommand)) {
            return false;
        }

        // state check
        DeletePersonTagCommand e = (DeletePersonTagCommand) other;
        return eventIndex.equals(e.eventIndex) && personIndex.equals(e.personIndex);
    }

}
