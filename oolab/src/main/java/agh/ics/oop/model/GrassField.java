package agh.ics.oop.model;

import java.util.*;

public class GrassField extends AbstractWorldMap {

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
    public WorldElement objectAt(Vector2d position) {
        Animal animal = animals.get(position);
        if (animal != null) {
            return animal;
        }
        return grasses.get(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> result = new ArrayList<>();
        result.addAll(animals.values());
        result.addAll(grasses.values());
        return result;
    }

    @Override
    public String toString() {
        Collection<WorldElement> elements = getElements();
        if (elements.isEmpty()) {
            return "";
        }

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (WorldElement el : elements) {
            Vector2d p = el.getPosition();
            if (p.getX() < minX) minX = p.getX();
            if (p.getY() < minY) minY = p.getY();
            if (p.getX() > maxX) maxX = p.getX();
            if (p.getY() > maxY) maxY = p.getY();
        }

        Vector2d lowerLeft  = new Vector2d(minX, minY);
        Vector2d upperRight = new Vector2d(maxX, maxY);

        return draw(lowerLeft, upperRight);
    }
}
