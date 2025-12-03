package agh.ics.oop;

import agh.ics.oop.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    private static List<MoveDirection> parse(String... raw) {
        return OptionsParser.parse(raw);
    }

    @Test
    @DisplayName("boundaries")
    void boundaries() {
        // given
        var map = new RectangularMap(5, 5);
        var sim = new Simulation(map, List.of(new Vector2d(0, 0)), parse("b", "l", "f"));

        // when
        sim.run();

        // then
        assertNotNull(map.objectAt(new Vector2d(0, 0)));
        // nic nie pojawiło się poza mapą
        assertFalse(map.isOccupied(new Vector2d(-1, 0)));
        assertFalse(map.isOccupied(new Vector2d(0, -1)));
    }

    @Test
    @DisplayName("orientation + movement")
    void orientationAndMovement() {
        // given
        var map = new RectangularMap(5, 5);
        var sim = new Simulation(
                map,
                List.of(new Vector2d(2, 2)),
                parse("r", "f", "right", "forward")
        );

        // when
        sim.run();

        // then
        assertNotNull(map.objectAt(new Vector2d(3, 1)));
    }

    @Test
    @DisplayName("orientation cycle")
    void orientationCycle() {
        // given
        var map = new RectangularMap(5, 5);
        var sim = new Simulation(
                map,
                List.of(new Vector2d(2, 2)),
                parse("r", "f", "r", "f", "r", "f", "r", "f")
        );

        // when
        sim.run();

        // then
        assertNotNull(map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("roundRobinTwoAnimals")
    void roundRobinTwoAnimals() {
        // given
        var map = new RectangularMap(5, 5);
        var sim = new Simulation(
                map,
                List.of(new Vector2d(2, 2), new Vector2d(3, 4)),
                parse("f", "f", "r", "f", "b")
        );

        // when
        sim.run();

        // then (#0 -> (1,3), #1 zostaje (3,4))
        assertNotNull(map.objectAt(new Vector2d(1, 3)));
        assertNotNull(map.objectAt(new Vector2d(3, 4)));
    }

    @Test
    @DisplayName("upper-right boundaries")
    void upperRightBoundaries() {
        // given
        var map = new RectangularMap(5, 5);
        var sim = new Simulation(
                map,
                List.of(new Vector2d(4, 4)),
                parse("f", "r", "f")
        );

        // when
        sim.run();

        // then
        assertNotNull(map.objectAt(new Vector2d(4, 4)));
        // rogi za mapą puste
        assertFalse(map.isOccupied(new Vector2d(5, 4)));
        assertFalse(map.isOccupied(new Vector2d(4, 5)));
    }

    @Test
    @DisplayName("parser – niepoprawne argumenty rzucają wyjątek")
    void parser() {
        // given
        var raw = new String[]{"x", "l", "foo", "BACKWARD", "b"};

        //  then
        assertThrows(
                IllegalArgumentException.class,
                () -> OptionsParser.parse(raw)
        );
    }

    @Test
    @DisplayName("place – nie można wystartować z dwoma zwierzętami na tym samym polu")
    void cannotPlaceOnOccupiedStart() throws IncorrectPositionException {
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
    @DisplayName("move – zwierzę nie wchodzi na zajętą komórkę")
    void cannotEnterOccupiedCell() throws IncorrectPositionException {
        // given
        var map = new RectangularMap(5, 5);
        var a1 = new Animal(new Vector2d(2, 2)); // NORTH
        var a2 = new Animal(new Vector2d(2, 3)); // stoi nad a1

        map.place(a1);
        map.place(a2);

        // when: a1 próbuje wejść w (2,3) – zajęte
        map.move(a1, MoveDirection.FORWARD);

        // then: a1 zostaje na (2,2), a2 na (2,3)
        assertSame(a2, map.objectAt(new Vector2d(2, 3)));
        assertSame(a1, map.objectAt(new Vector2d(2, 2)));
    }
}
