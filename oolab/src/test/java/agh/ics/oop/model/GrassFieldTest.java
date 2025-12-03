package agh.ics.oop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    @Test
    @DisplayName("place() można postawić zwierzę na wolnym polu")
    void placeOnFreeField() throws IncorrectPositionException {
        // given
        var map = new GrassField(10);
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
        var map = new GrassField(10);
        var a1 = new Animal(new Vector2d(2, 2));
        var a2 = new Animal(new Vector2d(2, 2));

        map.place(a1);

        // when + then
        assertThrows(
                IncorrectPositionException.class,
                () -> map.place(a2)
        );
        assertSame(a1, map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("GrassField zwierzę może wyjść poza 5x5")
    void grassFieldIsUnbounded() throws IncorrectPositionException {
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
    @DisplayName("objectAt() zwierzę ma priorytet nad trawą")
    void animalHasPriorityOverGrass() throws IncorrectPositionException {
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
