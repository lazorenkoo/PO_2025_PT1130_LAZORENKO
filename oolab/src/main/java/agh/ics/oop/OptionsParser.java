package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static List<MoveDirection> parse(String[] args) {
        List<MoveDirection> result = new ArrayList<>();
        for (String s : args) {
            switch (s) {
                case "f", "forward"  -> result.add(MoveDirection.FORWARD);
                case "b", "backward" -> result.add(MoveDirection.BACKWARD);
                case "r", "right"    -> result.add(MoveDirection.RIGHT);
                case "l", "left"     -> result.add(MoveDirection.LEFT);
                // niepoprawne opcje pomijamy
                default -> { }
            }
        }
        return result;
    }
}
