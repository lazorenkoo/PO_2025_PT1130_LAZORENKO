package agh.ics.oop;

public class World {
    public static void main(String args[]){
        System.out.println("Start");
        run(args);
        System.out.println("Stop");
    }

    public static void run(String args[]){
        for(int i=0;i<args.length;i++){
            if (args[i].equals("f")){
                System.out.println("Zwierzak idzie do przodu");
            }
            if (args[i].equals("b")){
                System.out.println("Zwierzak idzie do tylu");
            }
            if (args[i].equals("r")){
                System.out.println("Zwierzak skreca w prawo");
            }
            if (args[i].equals("l")){
                System.out.println("Zwierzak skreca w lewo");
            }
        }
    }
}