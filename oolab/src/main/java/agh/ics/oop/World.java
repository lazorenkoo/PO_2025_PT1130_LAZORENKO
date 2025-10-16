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
            switch (m) {
                case FORWARD  -> System.out.println("zwierzak idzie do przodu");
                case BACKWARD -> System.out.println("zwierzak idzie do tyłu");
                case LEFT     -> System.out.println("zwierzak skręca w lewo");
                case RIGHT    -> System.out.println("zwierzak skręca w prawo");
            }
        }
    }
}
