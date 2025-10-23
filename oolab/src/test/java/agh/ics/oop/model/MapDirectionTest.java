package agh.ics.oop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
class MapDirectionTest {
    @Test
    void nextToEachSide() {
        // When
        // Then
        assertEquals(MapDirection.EAST,  MapDirection.NORTH.next());
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next());
        assertEquals(MapDirection.WEST,  MapDirection.SOUTH.next());
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next());
    }

    @Test
    void previousToEachSide() {
        // When
        // Then
        assertEquals(MapDirection.WEST,  MapDirection.NORTH.previous());
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous());
        assertEquals(MapDirection.EAST,  MapDirection.SOUTH.previous());
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous());
    }
}