package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

import java.util.*;

public abstract class AbstractWorldMap implements WorldMap {

    protected final Map<Vector2d, Animal> animals = new HashMap<>();
    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d pos = animal.getPosition();
        if (!canMoveTo(pos)) {
            throw new IncorrectPositionException(pos);
        }
        animals.put(pos, animal);
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
