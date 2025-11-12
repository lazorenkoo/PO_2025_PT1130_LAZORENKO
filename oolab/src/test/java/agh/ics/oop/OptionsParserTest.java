package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class OptionsParserTest {
    @Test
    @DisplayName("empty -> returns empty array")

    void empty() {
        // When
        var result = OptionsParser.parse(new String[]{});

        // Then
        assertTrue(result.isEmpty());
    }
    @Test
    @DisplayName("all Valid -> returns all in order")

    void allvalid() {
        // When
        var result = OptionsParser.parse(new String[]{"f", "b", "r", "l"});

        // Then
        assertEquals(List.of(MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT), result);
    }
    @Test
    @DisplayName("invalid -> returns all valid in order")

    void Invalid() {
        // When
        var result = OptionsParser.parse(new String[]{"f", "b", "lalala", "l"});

        // Then
        assertEquals(List.of(
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT), result);
    }



}