import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GrassBlockTest {
    private GrassBlock grassBlock;
    @Before
    public void setUp() throws Exception {
        this.grassBlock=new GrassBlock();
    }

    @Test
    public void getBlockType() {
        assertEquals("grass",grassBlock.getBlockType());
    }

    @Test
    public void getColour() {
        assertEquals("green",grassBlock.getColour());
    }

    @Test
    public void isCarryable() {
        assertFalse(grassBlock.isCarryable());
    }

    @Test
    public void isDiggable() {
        assertTrue(grassBlock.isDiggable());
    }

    @Test
    public void isMoveable() {
        assertFalse(grassBlock.isMoveable());
    }
}