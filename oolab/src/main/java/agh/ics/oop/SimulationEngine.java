package agh.ics.oop;

import agh.ics.oop.Simulation;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;

    private final List<Thread> threads = new ArrayList<>();
    private ExecutorService executorService;

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
        // czekamy na zwykle wątki (runAsync)
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        threads.clear();

        // czekamy na pule wątków (runAsyncInThreadPool)
        if (executorService != null) {
            executorService.shutdown();
            try {
                executorService.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            executorService = null;
        }
    }
    public void runAsyncInThreadPool() {
        executorService = Executors.newFixedThreadPool(4);  // 4 wątki
        for (Simulation simulation : simulations) {
            executorService.submit(simulation);
        }
    }


}
