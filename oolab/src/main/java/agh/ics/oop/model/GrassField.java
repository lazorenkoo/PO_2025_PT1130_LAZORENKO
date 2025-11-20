package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GrassField implements WorldMap {

    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final Map<Vector2d, Grass> grasses = new HashMap<>();

    public GrassField(int grassCount) {
        Random random = new Random();
        int maxCoord = (int) Math.sqrt(grassCount * 10);

        int created = 0;
        while (created < grassCount) {
            int x = random.nextInt(maxCoord + 1);
            int y = random.nextInt(maxCoord + 1);
            Vector2d pos = new Vector2d(x, y);

            if (grasses.containsKey(pos)) {
                continue;
            }
            grasses.put(pos, new Grass(pos));
            created++;
        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        return !animals.containsKey(position);
    }


    @Override
    public boolean place(Animal animal) {
        Vector2d pos = animal.getPosition();
        if (!canMoveTo(pos)) {
            return false;
        }
        animals.put(pos, animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        Vector2d before = animal.getPosition();
        animal.move(direction, this);
        Vector2d after = animal.getPosition();

        if (!before.equals(after)) {
            animals.remove(before);
            animals.put(after, animal);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        Animal a = animals.get(position);
        if (a != null) {
            return a;
        }
        return grasses.get(position);
    }


    @Override
    public String toString() {
        if (animals.isEmpty() && grasses.isEmpty()) {
            return "";
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (Animal a : animals.values()) {
            Vector2d p = a.getPosition();
            if (p.getX() < minX) minX = p.getX();
            if (p.getY() < minY) minY = p.getY();
            if (p.getX() > maxX) maxX = p.getX();
            if (p.getY() > maxY) maxY = p.getY();
        }
        for (Grass g : grasses.values()) {
            Vector2d p = g.getPosition();
            if (p.getX() < minX) minX = p.getX();
            if (p.getY() < minY) minY = p.getY();
            if (p.getX() > maxX) maxX = p.getX();
            if (p.getY() > maxY) maxY = p.getY();
        }
        Vector2d lowerLeft  = new Vector2d(minX, minY);
        Vector2d upperRight = new Vector2d(maxX, maxY);

        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }
}
