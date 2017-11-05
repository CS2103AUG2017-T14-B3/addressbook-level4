package seedu.address.model.event;

import static java.time.temporal.ChronoUnit.MINUTES;
import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import seedu.address.model.event.timeslot.Date;
import seedu.address.model.event.timeslot.Timeslot;
import seedu.address.model.event.timeslot.Timing;
import seedu.address.model.person.ReadOnlyPerson;

//@@author reginleiff
/**
 * Represents an Event in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Event implements ReadOnlyEvent {
    private ObjectProperty<Title> title;
    private ObjectProperty<Date> date;
    private ObjectProperty<Timing> timing;
    private ObjectProperty<Timeslot> timeslot;
    private ObjectProperty<Description> description;
    private ObjectProperty<List<ReadOnlyPerson>> personList;


    /**
     * Every field must be present and not null.
     */
    public Event(Title title, Timeslot timeslot, Description description, List<ReadOnlyPerson> personList) {
        requireAllNonNull(title, timeslot, description);
        this.title = new SimpleObjectProperty<>(title);
        this.date = new SimpleObjectProperty<>(timeslot.getDate());
        this.timing = new SimpleObjectProperty<>(timeslot.getTiming());
        this.timeslot = new SimpleObjectProperty<>(timeslot);
        this.description = new SimpleObjectProperty<>(description);
        this.personList = new SimpleObjectProperty<>(personList);
    }

    /**
     * Creates a copy of the given ReadOnlyEvent.
     */
    public Event(ReadOnlyEvent source) {
        this(source.getTitle(), source.getTimeslot(), source.getDescription(), source.getPersonList());
    }

    @Override
    public ObjectProperty<Title> titleProperty() {
        return title;
    }

    @Override
    public Title getTitle() {
        return title.get();
    }

    public void setTitle(Title title) {
        this.title.set(requireNonNull(title));
    }

    @Override
    public ObjectProperty<Date> dateProperty() {
        return date;
    }

    @Override
    public Date getDate() {
        return date.get();
    }

    public void setDate(Date date) {
        this.date.set(requireNonNull(date));
    }

    @Override
    public ObjectProperty<Timeslot> timeslotProperty() {
        return timeslot;
    }

    @Override
    public ObjectProperty<Timing> timingProperty() {
        return timing;
    }

    @Override
    public Timing getTiming() {
        return timing.get();
    }

    public void setTiming(Timing timing) {
        this.timing.set(requireNonNull(timing));
    }

    @Override
    public Timeslot getTimeslot() {
        return timeslot.get();
    }

    public void setTimeslot(Timeslot timeslot) {
        this.timeslot.set(requireNonNull(timeslot));
    }

    @Override
    public ObjectProperty<Description> descriptionProperty() {
        return description;
    }

    @Override
    public Description getDescription() {
        return description.get();
    }

    public void setDescription(Description description) {
        this.description.set(requireNonNull(description));
    }

    @Override
    public ObjectProperty<List<ReadOnlyPerson>> personListProperty() {
        return this.personList;
    }

    @Override
    public List<ReadOnlyPerson> getPersonList() {
        return this.personList.get();
    }

    public void setPersonList(List<ReadOnlyPerson> personList) {
        this.personList.set(personList);
    }

    /**
     * Check if this event happens at an earlier time than the given timeslot.
     * @return true if indeed earlier.
     */
    public boolean happensBefore(Timeslot slot) {
        int comparison = this.getTimeslot().compareTo(slot);
        return comparison < 0;
    }

    /**
     * Check if this event happens at a later time than the given timeslot.
     * @return true if indeed earlier.
     */
    public boolean happensAfter(Timeslot slot) {
        int comparison = this.getTimeslot().compareTo(slot);
        return comparison > 0;
    }

    /**
     * Obtain the duration of the event.
     * @return a Duration object.
     */
    public Duration getDuration() {
        return Duration.ofMinutes(MINUTES.between(this.getStartTime(), this.getEndTime()));
    }

    /**
     * Obtain the start time of the event.
     * @return a LocalTime object.
     */
    public LocalTime getStartTime() {
        int start = this.getTiming().getStart();
        return LocalTime.of(start / 100, start % 100);
    }

    /**
     * Obtain the end time of the event.
     * @return a LocalTime object.
     */
    public LocalTime getEndTime() {
        int end = this.getTiming().getEnd();
        return LocalTime.of(end / 100, end % 100);
    }

    /**
     * Check if two events have time clash.
     * @param other Event to compare with
     * @return true if clashes.
     */
    public boolean clashesWith(Event other) {
        int ts = this.getTiming().getStart();
        int te = this.getTiming().getEnd();
        int os = other.getTiming().getStart();
        int oe = other.getTiming().getEnd();

        if (this.getDate().equals(other.getDate()) && !(ts >= oe) && !(te <= os)) {
            return true;
        }
        return false;
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyEvent // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyEvent) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, timeslot, description);
    }

    @Override
    public String toString() {
        return getAsText();
    }
}
