package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;


public class World {

    public static void main(String[] args) {
        // Vector2d position1 = new Vector2d(1,2);
        // System.out.println(position1);
        // Vector2d position2 = new Vector2d(-2,1);
        // System.out.println(position2);
        // System.out.println(position1.add(position2));

        System.out.println("Start");
        MoveDirection[] moves = OptionsParser.parse(args);
        run(moves);
        System.out.println("Stop");

    }
    public static void run(MoveDirection[] moves) {
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
