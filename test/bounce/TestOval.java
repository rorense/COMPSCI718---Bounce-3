package bounce;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * A class that implements test cases aimed at identifying bugs in the
 * implementations of classes Shape and OvalShape.
 */
public class TestOval {
    // Fixture object that is used by the tests.
    private MockPainter _painter;

    /**
     * This method is called automatically by the JUnit test-runner immediately
     * before each @Test method is executed. setUp() recreates the fixture so
     * that there no side effects from running individual tests.
     */
    @Before
    public void setUp() {
        _painter = new MockPainter();
    }

    /** A test to see if an oval shaped is drawn using default inputs.
     */
    @Test
    public void testOvalShape1() {
        OvalShape shape = new OvalShape();
        shape.paint(_painter);
        assertEquals("(oval 0,0,25,35)",_painter.toString());
    }

    /** A test to see if an oval shaped is drawn using 2 inputs.
     */
    @Test
    public void testOvalShape2() {
        OvalShape shape = new OvalShape(5,10);
        assertEquals(5,shape.x());
        assertEquals(10,shape.y());
        assertEquals(5,shape.deltaX());
        assertEquals(5,shape.deltaY());
        assertEquals(25,shape.width());
        assertEquals(35,shape.height());
    }

    /** A test to see if an oval shaped is drawn using 6 inputs.
     */
    @Test
    public void testOvalShape3() {
        OvalShape shape = new OvalShape(10,15,20,60,45,55);
        assertEquals(10,shape.x());
        assertEquals(15,shape.y());
        assertEquals(20,shape.deltaX());
        assertEquals(60,shape.deltaY());
        assertEquals(45,shape.width());
        assertEquals(55,shape.height());
    }
}
