package agh.ics.oop.model;


public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private static final Vector2d LOWER_BOUND = new Vector2d(0,0);
    private static final Vector2d UPPER_BOUND = new Vector2d(4,4);
    public Animal ()
    {
        this.position = new Vector2d(2,2);
        this.orientation = MapDirection.NORTH;
    }
    public Animal(Vector2d position)
    {
        this.position = position;
        this.orientation = MapDirection.NORTH;
    }

    public String toString()
    {
        return "Animal position: " + "(x: " + position.getX() + ",y: " + position.getY() + "), orientation: " + orientation;
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public void move(MoveDirection direction)
    {
        switch(direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD, BACKWARD -> {
                Vector2d MoveVector = orientation.toUnitVector();
                if (direction == MoveDirection.BACKWARD)
                    MoveVector = MoveVector.opposite();
                Vector2d NewPosition = position.add(MoveVector);
                if(NewPosition.follows(LOWER_BOUND) && NewPosition.precedes(UPPER_BOUND))
                    position = NewPosition;
            }
        }
    }
}
