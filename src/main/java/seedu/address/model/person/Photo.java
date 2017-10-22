package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.awt.*;
import java.net.URL;

import seedu.address.commons.exceptions.IllegalValueException;

/**
 * Represents a Person's photo in the address book.
 */
public class Photo {
    public static final String MESSAGE_PHOTO_CONSTRAINTS =
            "Photo can only be in JPG format";
    public static final String PHOTOURL_VALIDATION_REGEX =
            "file:\\/\\/\\w+\\.\\w";
    public final String photoURL;

    /**
     * Validates given photo number.
     *
     * @throws IllegalValueException if given photo string is invalid.
     */
    public Photo(String photoURL) throws IllegalValueException {
        requireNonNull(photoURL);
        if (!isValidPhotoURL(photoURL)) {
            throw new IllegalValueException(MESSAGE_PHOTO_CONSTRAINTS);
        }
        this.photoURL = photoURL;
    }

    /**
     * Returns true if a given string is a valid person photo number.
     */
    public static boolean isValidPhotoURL(String testURL) {
        return testURL.matches(PHOTOURL_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return photoURL;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Photo // instanceof handles nulls
                && this.photoURL.equals(((Photo) other).photoURL)); // state check
    }

    @Override
    public int hashCode() {
        return photoURL.hashCode();
    }

}
