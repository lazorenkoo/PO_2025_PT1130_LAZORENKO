package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap<Animal, Vector2d>, MoveValidator {
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;
    private final Map<Vector2d, Animal> animals = new HashMap<>();

    public RectangularMap(int width, int height) {
        this.lowerLeft  = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean place(Animal animal, Vector2d position) {
        if (!canMoveTo(position)) return false;
        animals.put(position, animal);
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
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight) && !isOccupied(position);
    }

    @Override
    public String toString() {
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }
}
