package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
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

        WorldMap rectangular = new RectangularMap("Rect#1", 5, 5);
        WorldMap grassField  = new GrassField("Grass#1", 10);

        ConsoleMapDisplay display = new ConsoleMapDisplay();
        ((AbstractWorldMap) rectangular).addObserver(display);
        ((AbstractWorldMap) grassField).addObserver(display);


        Simulation s1 = new Simulation(rectangular, startPositions, moves);
        Simulation s2 = new Simulation(grassField,  startPositions, moves);

        List<Simulation> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);

        SimulationEngine engine = new SimulationEngine(list);
        engine.runSync();
    }
}
