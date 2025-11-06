package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> positions, List<MoveDirection> moves) {
        for (Vector2d p : positions) {
            animals.add(new Animal(p));
        }
        this.moves = moves;
    }
    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            int idx = i % animals.size();
            Animal a = animals.get(idx);
            a.move(moves.get(i));
            System.out.println("Zwierzę " + idx + " : " + a.toString());
        }
    }
}
