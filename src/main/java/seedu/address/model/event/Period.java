//@@author shuangyang
package seedu.address.model.event;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents an Event's period of repetition in the address book.
 * Guarantees: is valid as declared in {@link #isValidPeriod(String)}
 */
public class Period {
    public static final String MESSAGE_PERIOD_CONSTRAINTS =
            "Period should be positive integers smaller than 366 (days)";

    /*
     *
     */
    public static final String PERIOD_VALIDATION_REGEX = "([0-9]|[1-9][0-9]|[1-2][0-9][0-9]|3[0-5][0-9]|36[0-6])";

    public final String period;

    /**
     * Validates given period.
     *
     * @throws IllegalValueException if given period string is invalid.
     */
    public Period(String period) throws IllegalValueException {
        requireNonNull(period);
        String trimmedPeriod = period.trim();
        if (!isValidPeriod(trimmedPeriod)) {
            throw new IllegalValueException(MESSAGE_PERIOD_CONSTRAINTS);
        }
        this.period = trimmedPeriod;
    }

    /**
     * Returns true if a given string is a valid event period.
     */
    public static boolean isValidPeriod(String test) {
        return !test.equals("") && test.matches(PERIOD_VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return period;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Period // instanceof handles nulls
                && this.period.equals(((Period) other).period)); // state check
    }

    @Override
    public int hashCode() {
        return period.hashCode();
    }
}
