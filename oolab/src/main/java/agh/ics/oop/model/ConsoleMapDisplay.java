package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {

    private int updates = 0;

    @Override
    //synchronized
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        updates++;
        System.out.println("[" + worldMap.getId() + "] " + message);
        System.out.println(worldMap);
        System.out.println("[" + worldMap.getId() + "] liczba aktualizacji: " + updates);
        System.out.println();
    }
}
