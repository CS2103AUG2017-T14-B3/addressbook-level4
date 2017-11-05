package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.bind.annotation.XmlElement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.event.Description;
import seedu.address.model.event.Event;
import seedu.address.model.event.ReadOnlyEvent;
import seedu.address.model.event.Title;
import seedu.address.model.event.timeslot.Timeslot;
import seedu.address.model.person.ReadOnlyPerson;

//@@author reginleiff
/**
 * JAXB-friendly version of the Event.
 */
public class XmlAdaptedEvent {

    @XmlElement(required = true)
    private String title;
    @XmlElement(required = true)
    private String timeslot;
    @XmlElement(required = true)
    private String description;
    @XmlElement(required = true)
    private List<XmlAdaptedPerson> persons;
    /**
     * Constructs an XmlAdaptedEvent.
     * This is the no-arg constructor that is required by JAXB.
     */
    public XmlAdaptedEvent() {
    }


    /**
     * Converts a given Event into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created XmlAdaptedEvent
     */
    public XmlAdaptedEvent(ReadOnlyEvent source) {
        title = source.getTitle().toString();
        timeslot = source.getTimeslot().toString();
        description = source.getDescription().toString();
        persons = new ArrayList<>();
        persons.addAll(source.getPersonList().stream().map(XmlAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this jaxb-friendly adapted event objet into the model's Event object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted event
     */
    public Event toModelType() throws IllegalValueException {
        final Title title = new Title(this.title);
        final Timeslot timeslot = new Timeslot(this.timeslot);
        final Description description = new Description(this.description);
        final ObservableList<ReadOnlyPerson> personList;

        if (this.persons != null) {
            personList = this.persons.stream().map(person -> {
                try {
                    return person.toModelType();
                } catch (IllegalValueException e) {
                    e.printStackTrace();
                    //TODO: better error handling
                    return null;
                }
            }).collect(Collectors.toCollection(FXCollections::observableArrayList));
        } else {
            personList = FXCollections.observableList((new ArrayList<>()));
        }

        return new Event(title, timeslot, description, FXCollections.unmodifiableObservableList(personList));
    }
}

