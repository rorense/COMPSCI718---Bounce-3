package bounce;

// The child class to create an oval shape.
// @author: Ryan Orense, rore098
public class OvalShape extends Shape{

    public OvalShape() { super(); }

    // Creating OvalShape instance with variables
    public OvalShape(int x, int y) {
        super(x,y);
    }

    /** Creates an OvalShape with specified values for location, velocity
     * and directions. Non-specified state items take on default values.
     */
    public OvalShape(int x, int y, int deltaX, int deltaY) {
        super  (x, y, deltaX, deltaY);
    }

    /** Creates an OvalShape with specified values for location, velocity,
     * directions, width and height.
     */
    public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super  (x, y, deltaX, deltaY, width, height);
    }

    /** Creates an OvalShape with specified values for location, velocity,
     * directions, width, height and text.
     */
    public OvalShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        this.text = text;
    }

    // Paints the oval shape using supplied painter object.
    public void drawShape(Painter painter) {
        painter.drawOval(_x, _y, _width, _height);
    }
}
