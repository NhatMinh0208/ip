package sphene.component;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sphene.command.AddDeadlineCommand;
import sphene.command.AddEventCommand;
import sphene.command.AddToDoCommand;
import sphene.command.Command;
import sphene.command.DeleteCommand;
import sphene.command.ExitCommand;
import sphene.command.FindCommand;
import sphene.command.ListCommand;
import sphene.command.MarkCommand;
import sphene.command.SortCommand;
import sphene.command.UnmarkCommand;
import sphene.exception.EmptyFieldException;
import sphene.exception.InvalidDateTimeException;
import sphene.exception.InvalidDateTimeRangeException;
import sphene.exception.InvalidIntException;
import sphene.exception.SyntaxException;
import sphene.exception.UnknownCommandException;

/**
 * Parser that parses a given command string into a Command object.
 */
public class Parser {
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_SORT = "sort";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_DELETE = "delete";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";
    private static final String CMD_FIND = "find";


    private static final Pattern PATTERN_TODO = Pattern.compile(" *(.*)");
    private static final Pattern PATTERN_FIND = Pattern.compile(" *(.*)");
    private static final Pattern PATTERN_DEADLINE = Pattern.compile(" *(.*)/by *(.*)");
    private static final Pattern PATTERN_EVENT = Pattern.compile(" *(.*)/from *(.*)/to *(.*)");
    private static final Pattern PATTERN_MARK = Pattern.compile(" *(-?[0-9]+) *");
    private static final Pattern PATTERN_UNMARK = PATTERN_MARK;
    private static final Pattern PATTERN_DELETE = PATTERN_MARK;


    private static Command parseToDo(String params)
            throws SyntaxException, EmptyFieldException {
        Matcher m = PATTERN_TODO.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_TODO, params, "content");
            }
            return new AddToDoCommand(m.group(1).trim());
        } else {
            throw new SyntaxException(CMD_TODO, params);
        }
    }

    private static Command parseDeadline(String params)
            throws SyntaxException, EmptyFieldException, InvalidDateTimeException {
        Matcher m = PATTERN_DEADLINE.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_DEADLINE, params, "content");
            }
            if (m.group(2).isEmpty()) {
                throw new EmptyFieldException(CMD_DEADLINE, params, "by");
            }
            return new AddDeadlineCommand(m.group(1).trim(), m.group(2).trim());
        } else {
            throw new SyntaxException(CMD_DEADLINE, params);
        }
    }

    private static Command parseEvent(String params)
            throws SyntaxException, EmptyFieldException, InvalidDateTimeException, InvalidDateTimeRangeException {
        Matcher m = PATTERN_EVENT.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_EVENT, params, "content");
            }
            if (m.group(2).isEmpty()) {
                throw new EmptyFieldException(CMD_EVENT, params, "from");
            }
            if (m.group(3).isEmpty()) {
                throw new EmptyFieldException(CMD_EVENT, params, "to");
            }
            return new AddEventCommand(m.group(1).trim(), m.group(2).trim(), m.group(3).trim());
        } else {
            throw new SyntaxException(CMD_EVENT, params);
        }
    }

    private static Command parseMark(String params) throws InvalidIntException {
        Matcher m = PATTERN_MARK.matcher(params);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1));
            return new MarkCommand(index);
        } else {
            throw new InvalidIntException(CMD_MARK, "index", params);
        }
    }

    private static Command parseUnmark(String params) throws InvalidIntException {
        Matcher m = PATTERN_UNMARK.matcher(params);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1));
            return new UnmarkCommand(index);
        } else {
            throw new InvalidIntException(CMD_UNMARK, "index", params);
        }
    }

    private static Command parseDelete(String params) throws InvalidIntException {
        Matcher m = PATTERN_DELETE.matcher(params);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1));
            return new DeleteCommand(index);
        } else {
            throw new InvalidIntException(CMD_DELETE, "index", params);
        }
    }

    private static Command parseFind(String params)
            throws SyntaxException, EmptyFieldException {
        Matcher m = PATTERN_FIND.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_FIND, params, "query");
            }
            return new FindCommand(m.group(1).trim());
        } else {
            throw new SyntaxException(CMD_FIND, params);
        }
    }

    /**
     * Parses the given command string.
     * @param commandStr Command string to be parsed.
     * @return A `Command` object containing the parsed command.
     * @throws UnknownCommandException If the command is not on the list of known command.
     * @throws SyntaxException If the command does not follow the required syntax.
     * @throws EmptyFieldException If a field in the command is empty.
     * @throws InvalidDateTimeException If a field in the command is not a valid datetime.
     * @throws InvalidDateTimeRangeException If fields in the command do not form a valid datetime range.
     * @throws InvalidIntException If a field in the command is not a valid integer.
     */
    public static Command parse(String commandStr)
            throws UnknownCommandException, SyntaxException, EmptyFieldException,
            InvalidDateTimeException, InvalidDateTimeRangeException, InvalidIntException {
        Scanner commandScanner = new Scanner(commandStr);
        String keyword = commandScanner.next();
        try {
            switch (keyword) {
            case CMD_EXIT:
                return new ExitCommand();
            case CMD_LIST:
                return new ListCommand();
            case CMD_SORT:
                return new SortCommand();
            case CMD_TODO:
                return parseToDo(commandScanner.nextLine());
            case CMD_DEADLINE:
                return parseDeadline(commandScanner.nextLine());
            case CMD_EVENT:
                return parseEvent(commandScanner.nextLine());
            case CMD_MARK:
                return parseMark(commandScanner.nextLine());
            case CMD_UNMARK:
                return parseUnmark(commandScanner.nextLine());
            case CMD_DELETE:
                return parseDelete(commandScanner.nextLine());
            case CMD_FIND:
                return parseFind(commandScanner.nextLine());
            default:
                throw new UnknownCommandException(keyword);
            }
        } catch (NoSuchElementException e) {
            throw new SyntaxException(keyword, "");
        }
    }
}
