package seedu.address.model.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@@author reginleiff
public class TitleTest {
    private static final String INVALID_TITLE_EMPTY_STRING = "";
    private static final String INVALID_TITLE_SPACES = "  ";

    private static final String VALID_TITLE_ALPHABETS_ONLY = "birthday";
    private static final String VALID_TITLE_NUMBERS_ONLY = "12345";
    private static final String VALID_TITLE_ALPHANUMERIC = "nus 50th anniversary";
    private static final String VALID_TITLE_ALPHANUMERIC_CAPITAL = "CS2103 MIDTERM";
    private static final String VALID_TITLE_ALL = "NUS'S Championed: 2020 Homecoming Graduation Ceremony";

    @Test
    public void isValidTitleTest() {
        // Invalid titles
        assertInvalidTitle(INVALID_TITLE_EMPTY_STRING); // spaces only
        assertInvalidTitle(INVALID_TITLE_SPACES); // empty string

        // Valid titles
        assertValidTitle(VALID_TITLE_ALPHABETS_ONLY); // alphabets only
        assertValidTitle(VALID_TITLE_NUMBERS_ONLY); // numbers only
        assertValidTitle(VALID_TITLE_ALPHANUMERIC); // alphanumeric characters
        assertValidTitle(VALID_TITLE_ALPHANUMERIC_CAPITAL); // with capital letters


        assertValidTitle(VALID_TITLE_ALL); // long names with symbols
    }

    /**
     * Asserts if title is valid.
     */
    public void assertValidTitle(String title) {
        assertTrue(Title.isValidTitle(title));
    }

    /**
     * Asserts if title is invalid.
     */
    public void assertInvalidTitle(String title) {
        assertFalse(Title.isValidTitle(title));
    }
}
