package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;


public class World {

    public static void main(String[] args) {
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
        }
        System.out.println(out);
    }
}
