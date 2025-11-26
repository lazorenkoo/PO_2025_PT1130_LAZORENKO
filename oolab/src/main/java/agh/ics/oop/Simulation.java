package agh.ics.oop;

import agh.ics.oop.model.*;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final WorldMap map;
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(WorldMap map, List<Vector2d> startPositions, List<MoveDirection> moves) {
        this.map = map;
        this.moves = moves;
        for (Vector2d pos : startPositions) {
            Animal animal = new Animal(pos);
            try {
                map.place(animal);
                animals.add(animal);
            } catch (IncorrectPositionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            int idx = i % animals.size();
            map.move(animals.get(idx), moves.get(i));
            System.out.println(map.toString()); // po każdym ruchu stan mapy
        }
    }
}
