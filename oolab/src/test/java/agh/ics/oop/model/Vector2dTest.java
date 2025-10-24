package agh.ics.oop.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2dTest {

    @Test
    @DisplayName("equals: same coords -> true; different coords -> false")
    void equals_test() {
        // Given
        Vector2d a = new Vector2d(1, 2);
        Vector2d b = new Vector2d(1, 2);
        Vector2d c = new Vector2d(2, 1);

        // When
        boolean ab = a.equals(b);
        boolean ac = a.equals(c);

        // Then
        assertTrue(ab);
        assertFalse(ac);
    }

    @Test
    @DisplayName("toString: prints '(x,y)'")
    void toString_test() {
        // Given
        Vector2d v1 = new Vector2d(0, 1);
        Vector2d v2 = new Vector2d(3, -3);

        // When
        String s1 = v1.toString();
        String s2 = v2.toString();

        // Then
        assertEquals("(0,1)", s1);
        assertEquals("(3,-3)", s2);
    }

    @Test
    @DisplayName("precedes: (x<=, y<=) per axis")
    void precedes_test() {
        // Given
        Vector2d a = new Vector2d(2, 4);
        Vector2d b = new Vector2d(4, 6);
        Vector2d c = new Vector2d(2, 6);

        // When / Then
        assertTrue(a.precedes(b)); // 2<=4 && 4<=6
        assertTrue(a.precedes(c)); // 2<=2 && 4<=6
        assertFalse(b.precedes(a)); // 4<=2 false
    }

    @Test
    @DisplayName("follows: (x>=, y>=) per axis")
    void follows_test() {
        // Given
        Vector2d a = new Vector2d(4, 6);
        Vector2d b = new Vector2d(2, 4);
        Vector2d c = new Vector2d(4, 2);

        // When / Then
        assertTrue(a.follows(b));
        assertTrue(a.follows(c));
        assertFalse(b.follows(a));
    }

    @Test
    @DisplayName("upperRight: max per axis")
    void upperRight_test() {
        // Given
        Vector2d a = new Vector2d(2, 10);
        Vector2d b = new Vector2d(6, 4);

        // When
        Vector2d ur1 = a.upperRight(b);
        Vector2d ur2 = b.upperRight(a);

        // Then
        assertEquals(new Vector2d(6, 10), ur1);
        assertEquals(new Vector2d(6, 10), ur2);
    }

    @Test
    @DisplayName("lowerLeft: min per axis")
    void lowerLeft_test() {
        // Given
        Vector2d a = new Vector2d(2, 10);
        Vector2d b = new Vector2d(6, 4);

        // When
        Vector2d ll1 = a.lowerLeft(b);
        Vector2d ll2 = b.lowerLeft(a);

        // Then
        assertEquals(new Vector2d(2, 4), ll1);
        assertEquals(new Vector2d(2, 4), ll2);
    }

    @Test
    void add_test() {
        // Given
        Vector2d a = new Vector2d(2, -4);
        Vector2d b = new Vector2d(6, 10);

        // When
        Vector2d sum1 = a.add(b);
        Vector2d sum2 = b.add(a);

        // Then
        assertEquals(new Vector2d(8, 6), sum1);
        assertEquals(new Vector2d(8, 6), sum2);
    }

    @Test
    void subtract_test() {
        // Given
        Vector2d a = new Vector2d(2, -4);
        Vector2d b = new Vector2d(6, 10);

        // When
        Vector2d d1 = a.subtract(b);
        Vector2d d2 = b.subtract(a);

        // Then
        assertEquals(new Vector2d(-4, -14), d1);
        assertEquals(new Vector2d(4, 14), d2);
    }

    @Test
    @DisplayName("opposite: negates both components")
    void opposite_test() {
        // Given
        Vector2d v1 = new Vector2d(2, -4);
        Vector2d v2 = new Vector2d(0, 0);

        // When
        Vector2d o1 = v1.opposite();
        Vector2d o2 = v2.opposite();

        // Then
        assertEquals(new Vector2d(-2, 4), o1);
        assertEquals(new Vector2d(0, 0), o2);
    }
}
