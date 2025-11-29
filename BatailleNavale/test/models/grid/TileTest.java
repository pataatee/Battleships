package models.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void isFree() {
        var t = new SeaTile();
        assertTrue(t.isFree());
    }

    @Test
    void onHit() {
        var t = new SeaTile();
        t.onHit();
        assertSame(TileState.MISS, t.getStateName());
    }
}