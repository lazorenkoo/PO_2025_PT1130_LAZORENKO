package agh.ics.oop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    @Test
    @DisplayName("place() – można postawić zwierzę na wolnym polu")
    void placeOnFreeField() throws IncorrectPositionException {
        // given
        var map = new RectangularMap(5, 5);
        var animal = new Animal(new Vector2d(2, 2));

        // when
        map.place(animal);

        // then
        assertSame(animal, map.objectAt(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("place() nie można postawić dwóch zwierząt na tym samym polu")
    void cannotPlaceOnOccupiedField() throws IncorrectPositionException {
        // given
        var map = new RectangularMap(5, 5);
        var a1 = new Animal(new Vector2d(2, 2));
        var a2 = new Animal(new Vector2d(2, 2));

        // when
        map.place(a1);

        // then
        assertThrows(
                IncorrectPositionException.class,
                () -> map.place(a2)
        );
        assertSame(a1, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("canMoveTo() – granice mapy blokują ruch")
    void cannotMoveOutsideMap() throws IncorrectPositionException {
        // given
        var map = new RectangularMap(5, 5);
        var animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        // when
        map.move(animal, MoveDirection.BACKWARD);

        // then
        assertEquals(new Vector2d(0, 0), animal.getPosition());
        assertSame(animal, map.objectAt(new Vector2d(0, 0)));
    }

    @Test
    @DisplayName("move() – poprawne przesuwanie w ramach mapy")
    void moveInsideBounds() throws IncorrectPositionException {
        // given
        var map = new RectangularMap(5, 5);
        var animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        // when
        map.move(animal, MoveDirection.FORWARD); // (2,3)
        map.move(animal, MoveDirection.RIGHT);   // EAST
        map.move(animal, MoveDirection.FORWARD); // (3,3)

        // then
        assertEquals(new Vector2d(3, 3), animal.getPosition());
        assertSame(animal, map.objectAt(new Vector2d(3, 3)));
    }
}
