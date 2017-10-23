package seedu.address.logic.parser.event;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.event.FindEventCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.TitleContainsKeywordsPredicate;

/**
 * @@reginleiff
 * Parses input arguments and creates a new FindEventCommand object
 */
public class FindEventCommandParser implements Parser<FindEventCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the FindEventCommand
     * and returns an FindEventCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindEventCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindEventCommand.MESSAGE_USAGE));
        }

        String[] titleKeywords = trimmedArgs.split("\\s+");

        return new FindEventCommand(new TitleContainsKeywordsPredicate(Arrays.asList(titleKeywords)));
    }
}
