package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Simulation<T, P> {
    private final WorldMap<T, P> map;
    private final List<T> objects = new ArrayList<>();
    private final List<P> positions = new ArrayList<>();
    private final List<MoveDirection> moves;

    public Simulation(WorldMap<T, P> map,
                      List<T> objects,
                      List<P> positions,
                      List<MoveDirection> moves) {
        this.map = map;
        this.moves = moves;
        for (int i = 0; i < objects.size(); i++) {
            T o = objects.get(i);
            P p = positions.get(i);
            map.place(o, p);
            this.objects.add(o);
            this.positions.add(p);
        }
    }

    public void run() {
        for (int i = 0; i < moves.size(); i++) {
            int idx = i % objects.size();
            map.move(objects.get(idx), moves.get(i));
            System.out.println(map.toString());
        }
    }
}
