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

        int simulationCount = 100;
        List<Simulation> list = new ArrayList<>();

        for (int i = 0; i < simulationCount; i++) {
            list.add(new Simulation(rectangular, startPositions, moves));
            list.add(new Simulation(grassField,  startPositions, moves));
        }

        SimulationEngine engine = new SimulationEngine(list);
        engine.runAsyncInThreadPool();
        engine.awaitSimulationsEnd();
    }
}
