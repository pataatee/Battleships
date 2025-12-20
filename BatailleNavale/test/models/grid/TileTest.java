package models.grid;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {

    @Test
    void isFree() {
        var t = new SeaTile();
        assertTrue(t.isFree());
    }

    @Test
    void onHit() {
        var t = new SeaTile();
        t.onHit(0, 0);
        assertSame(TileState.MISS, t.getStateName());
    }
}