package sphene.command;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.Test;

import sphene.exception.InvalidDateTimeException;


public class AddEventCommandTest {
    @Test
    public void addEventCommand_normalInput_Success() {
        assertDoesNotThrow(() ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2026-02-28T09:23:34"));
    }

    @Test
    public void addEventCommand_incorrectFormatFrom_ExceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddEventCommand("hello", "2025-0325T08:12:23", "2026-02-28T09:23:34"));
    }

    @Test
    public void addEventCommand_incorrectFormatTo_ExceptionThrown() {
        assertThrowsExactly(InvalidDateTimeException.class, () ->
                new AddEventCommand("hello", "2025-03-25T08:12:23", "2026-02-2809:23:34"));
    }
}
