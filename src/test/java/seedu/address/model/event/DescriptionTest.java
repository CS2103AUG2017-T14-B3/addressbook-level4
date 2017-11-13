package seedu.address.model.event;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@@author reginleiff
public class DescriptionTest {
    private static final String INVALID_CASE_ONE = "";
    private static final String INVALID_CASE_TWO = "  ";

    private static final String VALID_CASE_ONE = "srgsrgffdh";
    private static final String VALID_CASE_TWO = "12345";
    private static final String VALID_CASE_THREE = "sasdgsdg898 s9898";
    private static final String VALID_CASE_FOUR = "SGSDF839 SD928 92";
    private static final String VALID_CASE_FIVE = "SDFIUSDFHIHI9839:2983983 2HIUDF938UN OJOFEIJ02JSLDLJDO90JS JOASDJ9";



    @Test
    public void isValidDescriptionTest() {
        // Invalid titles
        assertFalse(Description.isValidDescription(INVALID_CASE_ONE)); // empty string
        assertFalse(Description.isValidDescription(INVALID_CASE_TWO)); // spaces only

        // Valid titles
        assertTrue(Description.isValidDescription(VALID_CASE_ONE)); // alphabets only
        assertTrue(Description.isValidDescription(VALID_CASE_TWO)); // numbers only
        assertTrue(Description.isValidDescription(VALID_CASE_THREE)); // alphanumeric characters
        assertTrue(Description.isValidDescription(VALID_CASE_FOUR)); // with capital letters
        assertTrue(Description.isValidDescription(VALID_CASE_FIVE)); // long strings with all of the above
    }
}
