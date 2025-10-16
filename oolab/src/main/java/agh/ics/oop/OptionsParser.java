package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import java.util.ArrayList;
import java.util.List;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        List<MoveDirection> out = new ArrayList<>();
        for(String arg : args){
            String x = arg;
            switch(x)
            {
                case "f" -> out.add(MoveDirection.FORWARD);
                case "b" ->  out.add(MoveDirection.BACKWARD);
                case "r" ->  out.add(MoveDirection.RIGHT);
                case "l" ->  out.add(MoveDirection.LEFT);
            }
        }
        return out.toArray(new MoveDirection[0]);
    }
}
