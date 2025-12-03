package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.List;

public class World {
    public static void main(String[] args) {
        List<MoveDirection> moves;
        try {
            moves = OptionsParser.parse(args);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return;
        }

        var startPositions = List.of(
                new Vector2d(2, 2),
                new Vector2d(3, 4)
        );

        WorldMap map = new GrassField(10);

        if (map instanceof AbstractWorldMap abstractMap) {
            abstractMap.addObserver(new ConsoleMapDisplay());
        }
        Simulation simulation = new Simulation(map, startPositions, moves);
        simulation.run();
    }
}
