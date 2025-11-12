package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulationTest {

    private static List<MoveDirection> parse(String... raw) {
        return OptionsParser.parse(raw);
    }

    @Test
    @DisplayName("NORTH (2,2) -- kończy w (3,1)")
    void orientationAndMovement() {
        // Given
        var positions = List.of(new Vector2d(2, 2));
        var moves = parse("r", "f", "right", "forward");

        // When
        var sim = new Simulation(positions, moves);
        sim.run();

        // Then
        Animal a0 = sim.getAnimals().get(0);
        assertTrue(a0.isAt(new Vector2d(3, 1)));
    }

    @Test
    @DisplayName("r,f,r,f,r,f,r,f --  pelny cykl orientacji wraca do (2,2)")
    void orientationCycle() {
        var positions = List.of(new Vector2d(2, 2));
        var moves = parse("r","f","r","f","r","f","r","f");

        var sim = new Simulation(positions, moves);
        sim.run();
        //Po 4x(RIGHT,FORWARD) pozycja powinna wrocic do punktu startowego (2,2)
        assertTrue(sim.getAnimals().get(0).isAt(new Vector2d(2, 2)));
    }

    @Test
    @DisplayName("Given (0,0) -- nie wychodzi poza mapę (0,0)")
    void boundary() {
        var sim = new Simulation(List.of(new Vector2d(0, 0)), parse("b", "l", "f"));
        sim.run();
        //Proby wyjscia poza mapę są ignorowane -- zostaje (0,0)"
        assertTrue(sim.getAnimals().get(0).isAt(new Vector2d(0, 0)));
    }

    @Test
    @DisplayName("2 zwierzęta")
    void round() {
        var sim = new Simulation(
                List.of(new Vector2d(2, 2), new Vector2d(3, 4)),
                parse("f", "f", "r", "f", "b")
        );
        sim.run();

        // #0: f -> (2,3); r -> obrot; b przy EAST -> (1,3)
        // #1: f -> proba (3,5) -> granica => (3,4); f -> znow sciana => (3,4)
        assertTrue(sim.getAnimals().get(0).isAt(new Vector2d(1, 3)));
        assertTrue(sim.getAnimals().get(1).isAt(new Vector2d(3, 4)));
    }

    @Test
    @DisplayName("Parser -- ignoruje zle i mapuje dobre")
    void parser() {
        var moves = OptionsParser.parse(new String[]{"x", "l", "foo", "BACKWARD", "b"});
        assertEquals(2, moves.size());
        assertEquals(List.of(MoveDirection.LEFT, MoveDirection.BACKWARD), moves);
    }
}