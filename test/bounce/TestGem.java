package bounce;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * A class that implements test cases aimed at identifying bugs in the
 * implementations of classes Shape and GemShape.
 */
public class TestGem extends TestCase{
    private MockPainter _painter;

    @Before
    public void setUp() {
        _painter = new MockPainter();
    }

    /** A test to see whether a diamond shape with the right coordinates
     * are created in the program (where the width <= 40).
     */
    @Test
    public void testSmallGemShape() {
        GemShape shape = new GemShape(0,0,5,5,30,20);
        shape.paint(_painter);
        assertEquals("(line 0,10,15,0)(line 15,0,30,10)"
                + "(line 30,10,15,20)(line 15,20,0,10)", _painter.toString());
    }

    /** A test to see whether a hexagon shape with the right coordinates
     * are created in the program (where width > 40).
     */
    @Test
    public void testRegularGemShape(){
        GemShape shape = new GemShape(0,0,5,5,60,20);
        shape.paint(_painter);
        assertEquals("(line 20,0,40,0)(line 40,0,60,10)"
                + "(line 60,10,40,20)(line 40,20,20,20)"
                + "(line 20,20,0,10)(line 0,10,20,0)", _painter.toString());
    }
}
