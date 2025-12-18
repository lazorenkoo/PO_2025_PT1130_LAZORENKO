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

        int simulationCount = 100;
        List<Simulation> list = new ArrayList<>();

        for (int i = 0; i < simulationCount; i++) {
            var rectMap = new RectangularMap("Rect#" + i, 5, 5);
            ((AbstractWorldMap) rectMap).addObserver(new ConsoleMapDisplay());

            var grassMap = new GrassField("Grass#" + i, 10);
            ((AbstractWorldMap) grassMap).addObserver(new ConsoleMapDisplay());

            list.add(new Simulation(rectMap, startPositions, moves));
            list.add(new Simulation(grassMap, startPositions, moves));
        }

        SimulationEngine engine = new SimulationEngine(list);
        engine.runAsyncInThreadPool();
        engine.awaitSimulationsEnd();
    }
}
