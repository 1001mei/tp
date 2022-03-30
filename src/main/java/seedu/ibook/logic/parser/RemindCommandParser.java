package seedu.ibook.logic.parser;

import static seedu.ibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.ibook.logic.commands.RemindCommand;
import seedu.ibook.logic.parser.exceptions.ParseException;
import seedu.ibook.model.item.ExpiryDate;

/**
 * Parses input arguments and creates a new ReminderCommand object
 */
public class RemindCommandParser implements Parser<RemindCommand> {

    @Override
    public RemindCommand parse(String userInput) throws ParseException {
        try {
            ExpiryDate expiryDate = ParserUtil.parseNumberIntoDate(userInput);
            return new RemindCommand(expiryDate);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, RemindCommand.MESSAGE_USAGE), pe);
        }
    }
}
