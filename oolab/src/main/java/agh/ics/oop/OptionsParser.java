package agh.ics.oop;
import agh.ics.oop.model.MoveDirection;
import java.util.Arrays;

public class OptionsParser {
    public static MoveDirection[] parse(String[] args){
        MoveDirection[] out = new MoveDirection[args.length];
        int validMove = 0;
        for(int i = 0; i < args.length; i++){
            out[validMove] = switch(args[i])
            {
                case "f" -> MoveDirection.FORWARD;
                case "b" ->  MoveDirection.BACKWARD;
                case "r" ->  MoveDirection.RIGHT;
                case "l" ->  MoveDirection.LEFT;
                default -> null;
            };
            if(out[validMove] != null){
                validMove++;
            }
        }
        return Arrays.copyOf(out,validMove);
    }
}
