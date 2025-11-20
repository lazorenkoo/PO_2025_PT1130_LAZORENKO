package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
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
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }


    protected String draw(Vector2d lowerLeft, Vector2d upperRight) {
        return new MapVisualizer(this).draw(lowerLeft, upperRight);
    }


    @Override
    public abstract boolean canMoveTo(Vector2d position);

    @Override
    public abstract WorldElement objectAt(Vector2d position);

    @Override
    public abstract String toString();
}
