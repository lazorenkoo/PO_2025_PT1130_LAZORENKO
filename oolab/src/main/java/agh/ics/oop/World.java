package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.MapDirection;
import agh.ics.oop.model.Animal;
import agh.ics.oop.model.RectangularMap;
import agh.ics.oop.model.TextMap;


import java.util.List;
import java.util.ArrayList;


public class World {

    public static void main(String[] args) {
        // Vector2d position1 = new Vector2d(1,2);
        // System.out.println(position1);
        // Vector2d position2 = new Vector2d(-2,1);
        // System.out.println(position2);
        // System.out.println(position1.add(position2));

        //System.out.println(MapDirection.WEST);
        //System.out.println(MapDirection.SOUTH);
        //System.out.println(MapDirection.NORTH);
        //System.out.println(MapDirection.EAST);
        // MapDirection d = MapDirection.NORTH;
        // d = d.next();
        // System.out.println(d);
        // d= d.next();
        // System.out.println(d);
        // d= d.next();
       //  System.out.println(d);
       //  d= d.next();
        // System.out.println(d);
        //MapDirection d = MapDirection.NORTH;
         //d = d.previous();
        // System.out.println(d);
        // d= d.previous();
         //System.out.println(d);
        // d= d.previous();
        //  System.out.println(d);
       //   d= d.previous();
       //  System.out.println(d);

        // MapDirection d = MapDirection.NORTH;
        // System.out.println(d.toUnitVector());
        // d= d.next();
        // System.out.println(d.toUnitVector());
        // d =d.next();
        // System.out.println(d.toUnitVector());
        // d= d.next();
        // System.out.println(d.toUnitVector());

        // Symulacja zwierząt 2D
        var animalMap = new RectangularMap(5, 5);
        var animals   = List.of(
                new Animal(new Vector2d(2,2)),
                new Animal(new Vector2d(3,4))
        );
        var positions = List.of(
                new Vector2d(2,2),
                new Vector2d(3,4)
        );
        var moves     = OptionsParser.parse(args);
        new Simulation<Animal, Vector2d>(
                animalMap, animals, positions, moves
        ).run();

// Symulacja napisów 1D
        var textMap   = new TextMap();
        var words     = List.of("Ala", "ma", "sowoniedźwiedzia");
        var indexes   = List.of(0, 1, 2);
        var movesText = OptionsParser.parse(new String[]{"f", "f", "b", "r", "l"});
        new Simulation<String, Integer>(textMap, words, indexes, movesText).run();


    }
    public static void run(List<MoveDirection> moves) {
        for (MoveDirection m : moves) {
            String out = switch (m) {
                case FORWARD  -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwierzak idzie do tyłu";
                case LEFT     -> "zwierzak skręca w lewo";
                case RIGHT    -> "zwierzak skręca w prawo";
            };
        System.out.println(out);
        }
    }
}
