package sphene.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import sphene.exception.InvalidDateTimeException;
import sphene.exception.InvalidDateTimeRangeException;


public class AddEventCommandTest {
    @Test
    public void addEventCommand_normalInput_success() {
        assertDoesNotThrow(() ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2026-02-28T09:23:34"));
    }

    @Test
    public void addEventCommand_incorrectFormatFrom_exceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddEventCommand("hello", "2025-0325T08:12:23", "2026-02-28T09:23:34"));
    }

    @Test
    public void addEventCommand_incorrectFormatTo_exceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2026-02-2809:23:34"));
    }

    @Test
    public void addEventCommand_correctLeapDay_success() {
        assertDoesNotThrow(() ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2028-02-29T09:23:34"));
    }

    @Test
    public void addEventCommand_incorrectLeapDay_exceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2027-02-29T09:23:34"));
    }

    @Test
    public void addEventCommand_fromAfterTo_exceptionThrown() {
        assertThrowsExactly(InvalidDateTimeRangeException.class, () ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2025-03-25T08:12:22"));
    }

    @Test
    public void addEventCommand_fromEqualsTo_success() {
        assertDoesNotThrow(() ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2025-03-25T08:12:23"));
    }
}
