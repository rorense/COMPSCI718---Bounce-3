package bounce;

import java.awt.Color;
import org.junit.Before;
import org.junit.Test;
import junit.framework.TestCase;

/** Test to see if DynamicRectangleShape does its function of hitting the
 * walls and changing rectangle properties.
  */
public class TestDynamicRectangle extends TestCase{
    private MockPainter _painter;

    /** Set up before the test begins
     */
    @Before
    public void setUp() {
        _painter = new MockPainter();
    }

    /** Test to see if bouncing on the top wall changes the rectangle properties.
     */
    @Test
    public void testTopBounce(){
        DynamicRectangle shape = new DynamicRectangle(20,20,-1,-25,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(rectangle 20,20,25,35)(color red)(rectangle 19,0,25,35)"
                + "(color red)(rectangle 18,25,25,35)",  _painter.toString());
    }

    /** Test to see if bouncing on the left wall changes the rectangle properties.
     */
    @Test
    public void testLeftBounce(){
        DynamicRectangle shape= new DynamicRectangle(50,50,-50,1,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(50,50,25,35)(color red)(rectangle 0,51,25,35,red)"
                + "(color red)(rectangle 50,52,25,35,red)",  _painter.toString());
    }

    /** Test to see if bouncing on the right wall changes the rectangle properties.
     */
    @Test
    public void testRightBounce(){
        DynamicRectangle shape= new DynamicRectangle(50,50,50,1,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(rectangle 50,50,25,35)(rectangle 100,51,25,35)"
                + "(rectangle 150,52,25,35)",  _painter.toString());
    }

    /** Test to see if bouncing on the bottom wall changes the rectangle properties.
     */
    @Test
    public void testBottomBounce(){
        DynamicRectangle shape= new DynamicRectangle(450,450,1,50,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(rectangle 450,450,25,35)(color red)(rectangle 451,465,25,35)"
                + "(color red)(rectangle 452,415,25,35)",  _painter.toString());
    }

    /** Test to see if bouncing on the top and left wall changes the rectangle properties.
     */
    @Test
    public void testTopLeftBounce(){
        DynamicRectangle shape= new DynamicRectangle(10,10,-40,-40,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(rectangle 10,10,25,35)(color red)(rectangle 0,0,25,35,red)"
                + "(color red)(rectangle 40,40,25,35,red)",  _painter.toString());
    }

    /** Test to see if bouncing on the top and right wall changes the rectangle properties.
     */
    @Test
    public void testTopRightBounce(){
        DynamicRectangle shape= new DynamicRectangle(475,10,30,-30,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(rectangle 475,10,25,35)(color red)(rectangle 475,0,25,35,red)"
                + "(color red)(rectangle 445,30,25,35,red)",  _painter.toString());
    }

    /** Test to see if bouncing on the bottom and left wall changes the rectangle properties.
     */
    @Test
    public void testBottomLeftBounce(){
        DynamicRectangle shape= new DynamicRectangle(10,460,-30,30,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(rectangle 10,460,25,35)(color red)(rectangle 0,465,25,35,red)"
                + "(color red)(rectangle 30,435,25,35,red)",  _painter.toString());
    }

    /** Test to see if bouncing on the bottom and right wall changes the rectangle properties.
     */
    @Test
    public void testBottomRightBounce(){
        DynamicRectangle shape= new DynamicRectangle(465,455,50,50,Color.RED);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        shape.move(500,500);
        shape.paint(_painter);
        assertEquals("(color red)(rectangle 465,455,25,35)(color red)(rectangle 475,465,25,35,red)"
                + "(color red)(rectangle 425,415,25,35,red)",  _painter.toString());
    }
}
