package seedu.address.model.event.timeslot;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalTime;

import seedu.address.commons.exceptions.IllegalValueException;

//@@author reginleiff
/**
 * Timeslot contains date and time and is comparable based on year, month, day and starting time, in this order.
 */
public class Timeslot implements Comparable<Timeslot> {

    public static final String MESSAGE_TIMESLOT_CONSTRAINTS =
            "Proper format: [dd/mm/yyyy ssss-eeee] Event timings should not be blank but contain:\n "
                    + "1. A 6-digit date specifying day, month and year (in the format of dd/mm/yyyy) followed by\n "
                    + "2. A 4-digit 24-hour format start timing and end timing separated by a \"-\"";
    public static final String TIMESLOT_VALIDATION_REGEX =
            "(0[1-9]|1[0-9]|2[0-9]|3[0-1])/(0[1-9]|1[0-2])/[0-9][0-9][0-9][0-9] "

                    + "(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]-(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]";

    private Date date;
    private Timing timing;

    /**
     * Validates given Timeslot.
     *
     * @throws IllegalValueException if given timeslot arguments are invalid.
     */
    public Timeslot(String timeslot) throws IllegalValueException {
        requireNonNull(timeslot);
        String trimmedTimeslot = timeslot.trim();

        if (!isValidTiming(trimmedTimeslot)) {
            throw new IllegalValueException(MESSAGE_TIMESLOT_CONSTRAINTS);
        }

        String[] args = trimmedTimeslot.split(" ");
        String dateArgs = args[0];
        String timeArgs = args[1];

        try {
            this.date = new Date(dateArgs);
            this.timing = new Timing(timeArgs);
        } catch (IllegalValueException e) {
            throw e;
        }
    }

    /**
     * Constructs a new Timeslot object with given date and time.
     */
    public Timeslot(Date date, Timing timing) {
        this.date = date;
        this.timing = timing;
    }

    /**
     * Returns true if a given string is a valid event's Timing.
     */
    public static boolean isValidTiming(String test) {
        return test.matches(TIMESLOT_VALIDATION_REGEX);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Timing getTiming() {
        return timing;
    }

    public String toString() {
        return this.date.toString() + " " + this.timing.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Timeslot // instanceof handles nulls
                && this.date.equals(((Timeslot) other).date)) // state check
                && this.timing.equals(((Timeslot) other).timing);
    }

    @Override
    public int compareTo(Timeslot other) {
        if (!this.getDate().equals(other.getDate())) {
            return this.getDate().compareTo(other.getDate());
        } else {
            return this.getTiming().compareTo(other.getTiming());
        }
    }

    //@@author shuang-yang
    /**
     * Increase the date by specified number of days.
     * @return the new timeslot
     */
    public Timeslot plusDays(int days) {
        Date date = this.getDate().addDays(days);
        Timing timing = this.getTiming();
        return new Timeslot(date, timing);
    }

    /**
     * Get the current time as Timeslot.
     * @return the current timesot.
     */
    public static Timeslot getNow() {
        Date dateNow = new Date(LocalDate.now());
        Timing timeNow = new Timing(LocalTime.now());
        return new Timeslot(dateNow, timeNow);
    }

    /**
     * Check if the timeslot is before another timeslot.
     * @return true if it is.
     */
    public boolean isBefore(Timeslot slot) {
        return this.compareTo(slot) < 0;
    }

    //@@author

    //================================= Setter methods for testing ==========================================
    public void withDay(int day) {
        this.date.setDay(day);
    }

    public void withMonth(int month) {
        this.date.setMonth(month);
    }

    public void withYear(int year) {
        this.date.setYear(year);
    }

    public void withStartTime(int start) {
        this.timing.setStart(start);
    }

    public void withEndTime(int end) {
        this.timing.setEnd(end);
    }
}
