package seedu.address.logic.commands.event.util;

import java.util.List;
import java.util.Set;

import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.event.EditEventCommand;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.Title;
import seedu.address.model.event.timeslot.Timeslot;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Note;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Photo;
import seedu.address.model.person.Position;
import seedu.address.model.person.Priority;
import seedu.address.model.person.ReadOnlyPerson;
import seedu.address.model.person.Status;
import seedu.address.model.relationship.Relationship;
import seedu.address.model.tag.Tag;

//@@author reginleiff

/**
 * Abstraction for shared static functions between event commands.
 */
public class CommandUtil {
    //@@author sebtsh
    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    public static Person createEditedPerson(ReadOnlyPerson personToEdit,
                                             EditCommand.EditPersonDescriptor editPersonDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(personToEdit.getAddress());
        Company updatedCompany = editPersonDescriptor.getCompany().orElse(personToEdit.getCompany());
        Position updatedPosition = editPersonDescriptor.getPosition().orElse(personToEdit.getPosition());
        Status updatedStatus = editPersonDescriptor.getStatus().orElse(personToEdit.getStatus());
        Priority updatedPriority = editPersonDescriptor.getPriority().orElse(personToEdit.getPriority());
        Note updatedNote = editPersonDescriptor.getNote().orElse(personToEdit.getNote());
        Photo updatedPhoto = editPersonDescriptor.getPhoto().orElse(personToEdit
                .getPhoto());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(personToEdit.getTags());
        Set<Relationship> updatedRel = editPersonDescriptor.getRelation().orElse(personToEdit.getRelation());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedCompany,
                updatedPosition, updatedStatus, updatedPriority, updatedNote,
                updatedPhoto, updatedTags, updatedRel);
    }
    //@@author

    /**
     * Creates and returns a {@code Event} with the details of {@code eventToEdit}
     * edited with {@code editEventDescriptor}.
     */
    public static Event createEditedEvent(ReadOnlyEvent eventToEdit,
                                           EditEventCommand.EditEventDescriptor editEventDescriptor) {
        assert eventToEdit != null;

        Title updatedTitle = editEventDescriptor.getTitle().orElse(eventToEdit.getTitle());
        Timeslot updatedTimeslot = editEventDescriptor.getTimeslot().orElse(eventToEdit.getTimeslot());
        Description updatedDescription = editEventDescriptor.getDescription().orElse(eventToEdit.getDescription());
        List<ReadOnlyPerson> originalPersonList = eventToEdit.getPersonList();

        return new Event(updatedTitle, updatedTimeslot, updatedDescription, originalPersonList);
    }
}
