package agh.ics.oop;

import agh.ics.oop.Simulation;
import java.util.ArrayList;
import java.util.List;


public class SimulationEngine {
    private final List<Simulation> simulations;

    private final List<Thread> threads = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation simulation : simulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        threads.clear();
        for (Simulation simulation : simulations) {
            Thread t = new Thread(simulation);
            threads.add(t);
            t.start();
        }
    }
    public void awaitSimulationsEnd() {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        threads.clear();
    }


}
