package models.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void setSize() {
        var g = new Grid(5);
        assertFalse(g.isTileFree(5,5));
        g.setSize(6);
        assertTrue(g.isTileFree(5,5));
    }

    @Test
    void changeStateOfTile() {
        var g = new Grid(5);
        g.changeStateOfTile(0,0,TileState.TRAP);
        assertFalse(g.isTileFree(0,0));
    }

    @Test
    void hitTile() {
        var g = new Grid(5);
        g.hitTile(0,0);
        assertFalse(g.isTileFree(0,0));
    }

    @Test
    void isTileFree() {
        var g = new Grid(5);
        assertTrue(g.isTileFree(0,0));
        assertFalse(g.isTileFree(6,6));
    }
}