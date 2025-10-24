package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class OptionsParserTest {
    @Test
    @DisplayName("empty -> returns empty array")

    void empty() {
        // When
        MoveDirection[] result = OptionsParser.parse(new String[]{});

        // Then
        assertEquals(0, result.length);
    }
    @Test
    @DisplayName("all Valid -> returns all in order")

    void allvalid() {
        // When
        MoveDirection[] result = OptionsParser.parse(new String[]{"f", "b", "r", "l"});

        // Then
        assertArrayEquals(new MoveDirection[]{
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.RIGHT,
                MoveDirection.LEFT
        }, result);
    }
    @Test
    @DisplayName("invalid -> returns all valid in order")

    void Invalid() {
        // When
        MoveDirection[] result = OptionsParser.parse(new String[]{"f", "b", "lalala", "l"});

        // Then
        assertArrayEquals(new MoveDirection[]{
                MoveDirection.FORWARD,
                MoveDirection.BACKWARD,
                MoveDirection.LEFT
        }, result);
    }



}