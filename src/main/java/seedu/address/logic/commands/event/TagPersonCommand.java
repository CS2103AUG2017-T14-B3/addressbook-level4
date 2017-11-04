package seedu.address.logic.commands.event;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;

public class TagPersonCommand {
    public static final String COMMAND_WORD = "eventtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags a person by the index number used in the last "
            + "person listing to an event by the index number used in the last event listing. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX_OF_EVENT (must be a positive integer) INDEX_OF_CONTACT (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 2 ";

    public static final String MESSAGE_TAGPERSON_SUCCESS = "%1$s\n has been tag in %2$s";
    public static final String MESSAGE_DUPLIATE_TAG = "This contact has already been tagged in the event.";

    private final Index eventIndex;
    private final Index contactIndex;

    /**
     * @param eventIndex   index of the event in the filtered event list to add tag
     * @param contactIndex   index of the person in the filtered person list to be tagged in the event
     */
    public TagPersonCommand(Index eventIndex, Index contactIndex) {
        requireNonNull(eventIndex);
        requireNonNull(contactIndex);

        this.eventIndex = eventIndex;
        this.contactIndex = contactIndex;
    }
}
