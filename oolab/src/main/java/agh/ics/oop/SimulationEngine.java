package agh.ics.oop;

import agh.ics.oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationEngine {

    private final List<Simulation> simulations;
    private final List<Thread> threads = new ArrayList<>();

    private ExecutorService executorService;
    private final List<Future<?>> futures = new ArrayList<>();

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        for (Simulation s : simulations) {
            s.run();
        }
    }

    public void runAsync() {
        for (Simulation s : simulations) {
            Thread t = new Thread(s);
            threads.add(t);
            t.start();
        }
    }

    public void runAsyncInThreadPool() {
        if (executorService == null || executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(4);
        }

        futures.clear();
        for (Simulation s : simulations) {
            futures.add(executorService.submit(s));
        }
    }

    public void awaitSimulationsEnd() {
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Await interrupted", e);
            }
        }
        threads.clear();

        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }
}

