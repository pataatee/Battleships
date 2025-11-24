package models.grid;

import models.state.StateName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void isFree() {
        var t = new Tile(0,0);
        assertTrue(t.isFree());
    }

    @Test
    void onHit() {
        var t = new Tile(0,0);
        t.onHit();
        assertSame(StateName.MISS, t.getStateName());
    }
}