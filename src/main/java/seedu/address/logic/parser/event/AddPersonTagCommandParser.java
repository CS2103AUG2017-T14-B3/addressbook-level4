package seedu.address.logic.parser.event;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.event.AddPersonTagCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

//@@author reginleiff
public class AddPersonTagCommandParser implements Parser<AddPersonTagCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the AddPersonTagCommand
     * and returns an AddPersonTagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPersonTagCommand parse(String args) throws ParseException {
        requireNonNull(args);

        Index eventIndex;
        Index personIndex;

        String[] arguments = args.trim().split(" ");

        try {
            eventIndex = ParserUtil.parseIndex(arguments[0]);
            personIndex = ParserUtil.parseIndex(arguments[1]);
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPersonTagCommand.MESSAGE_USAGE));
        }

        return new AddPersonTagCommand(eventIndex, personIndex);
    }
}
