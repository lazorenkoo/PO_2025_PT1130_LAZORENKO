package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap {

    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.lowerLeft  = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    private boolean inBounds(Vector2d p) {
        return p.follows(lowerLeft) && p.precedes(upperRight);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return inBounds(position) && !animals.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return draw(lowerLeft, upperRight);
    }
}
