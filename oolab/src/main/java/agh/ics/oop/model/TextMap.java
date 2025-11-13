package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap implements WorldNumberPositionMap<String, Integer> {

    private final List<String> data = new ArrayList<>();

    @Override
    public boolean place(String s, Integer position) {
        data.add(s);
        return true;
    }

    @Override
    public void move(String s, MoveDirection direction) {
        int i = data.indexOf(s);
        if (i < 0) return;

        switch (direction) {
            case FORWARD, RIGHT -> {
                if (i < data.size() - 1) swap(i, i + 1);
            }
            case BACKWARD, LEFT -> {
                if (i > 0) swap(i, i - 1);
            }
        }
    }

    @Override
    public boolean isOccupied(Integer position) {
        return position >= 0 && position < data.size();
    }

    @Override
    public String objectAt(Integer position) {
        if (!isOccupied(position)) return null;
        return data.get(position);
    }

    private void swap(int i, int j) {
        String tmp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, tmp);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
