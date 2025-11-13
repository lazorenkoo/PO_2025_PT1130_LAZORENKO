package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }
    public Animal(Vector2d position) {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public boolean isAt(Vector2d position) { return this.position.equals(position); }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case EAST  -> ">";
            case SOUTH -> "v";
            case WEST  -> "<";
        };
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT  -> orientation = orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d step = orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD) step = step.opposite();
                Vector2d candidate = position.add(step);
                if (validator.canMoveTo(candidate)) position = candidate;
            }
        }
    }
    public Vector2d getPosition() { return position; }
}
