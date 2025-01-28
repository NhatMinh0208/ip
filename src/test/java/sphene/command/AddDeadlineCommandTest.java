package sphene.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import sphene.exception.InvalidDateTimeException;


public class AddDeadlineCommandTest {
    @Test
    public void addDeadlineCommand_normalInput_success() {
        assertDoesNotThrow(() ->
                new AddDeadlineCommand("hello", "2025-03-25T08:12:23"));
    }

    @Test
    public void addDeadlineCommand_incorrectFormatBy_exceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddDeadlineCommand("hello", "2025-03-25T08:1223"));
    }

    @Test
    public void addDeadlineCommand_correctLeapDay_success() {
        assertDoesNotThrow(() ->
                new AddDeadlineCommand("hello", "2028-02-29T09:23:34"));
    }

    @Test
    public void addDeadlineCommand_incorrectLeapDay_exceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddDeadlineCommand("hello", "2027-02-29T09:23:34"));
    }
}
