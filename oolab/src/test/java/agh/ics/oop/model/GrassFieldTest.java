package agh.ics.oop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    @DisplayName("place() – można postawić zwierzę na wolnym polu")
    void placeOnFreeField() {
        // given
        var map = new GrassField(10);
        var animal = new Animal(new Vector2d(2, 2));

        // when
        boolean placed = map.place(animal);

        // then
        assertTrue(placed);
        assertSame(animal, map.objectAt(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("place() – nie można postawić dwóch zwierząt na tym samym polu")
    void cannotPlaceOnOccupiedField() {
        // given
        var map = new GrassField(10);
        var a1 = new Animal(new Vector2d(2, 2));
        var a2 = new Animal(new Vector2d(2, 2));

        // when
        boolean first = map.place(a1);
        boolean second = map.place(a2);

        // then
        assertTrue(first);
        assertFalse(second);
        assertSame(a1, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("GrassField – brak granic, zwierzę może wyjść  poza 5x5")
    void grassFieldIsUnbounded() {
        // given
        var map = new GrassField(0);
        var animal = new Animal(new Vector2d(0, 0));
        map.place(animal);

        // when
        for (int i = 0; i < 100; i++) {
            map.move(animal, MoveDirection.FORWARD);
        }

        // then
        assertTrue(animal.getPosition().getY() > 4);
    }

    @Test
    @DisplayName("objectAt() – zwierzę ma priorytet nad trawą")
    void animalHasPriorityOverGrass() {
        // given
        var map = new GrassField(10);

        Vector2d grassPos = null;
        outer:
        for (int x = -10; x <= 10; x++) {
            for (int y = -10; y <= 10; y++) {
                WorldElement el = map.objectAt(new Vector2d(x, y));
                if (el instanceof Grass) {
                    grassPos = new Vector2d(x, y);
                    break outer;
                }
            }
        }
        assertNotNull(grassPos);
        var animal = new Animal(grassPos);

        // when
        map.place(animal);

        // then
        WorldElement result = map.objectAt(grassPos);
        assertTrue(result instanceof Animal);
        assertSame(animal, result);
    }
}
