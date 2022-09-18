package bounce;

import java.awt.Color;
import java.lang.reflect.Field;

/** A child class that creates a dynamic rectangle shape deriving from parent shape class.
 * A dynamic rectangle changes its properties as it touches the sides of the frame.
 * @author: Ryan Orense, rore098
 */
public class DynamicRectangle extends Shape{

    private Color _color = Color.BLACK; // Default color is black.
    private String colourString;
    private Boolean _dynamic = false; // false = normal rectangle, true = dynamic rectangle

    // Getting the name of the color
    public static String getColourName (Color c) {
        for (Field f : Color.class.getFields()) {
            try {
                if (f.getType() == Color.class && f.get(null).equals(c)) {
                    return f.getName();
                }
            } catch (java.lang.IllegalAccessException e) {

            }
        }
        return "";
    }

    /** Creates a DynamicRectangleShape object with specified color string values,
     */
    public DynamicRectangle() {
        super();
        colourString = getColourName(_color);
    }

    /** Creates a DynamicRectangleShape object with specified color object
     * and the color string value.
     */
    public DynamicRectangle(Color c) {
        super();
        _color = c;
        colourString = getColourName(_color);
    }

    /** Creates a DynamicRectangleShape with specified values for location, velocity
     * and directions. Non-specified state items take on default values.
     */
    public DynamicRectangle(int x, int y, int deltaX, int deltaY) {
        super(x,y,deltaX,deltaY);
        colourString = getColourName(_color);
    }

    /** Creates a DynamicRectangleShape with specified values for location, velocity,
     * directions, width, height and colour string value.
     */
    public DynamicRectangle(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x,y,deltaX,deltaY,width,height);
        colourString = getColourName(_color);
    }

    /** Creates a DynamicRectangleShape with specified values for location, velocity,
     * directions, color value and color string value
     */
    public DynamicRectangle(int x, int y, int deltaX, int deltaY, Color c) {
        super(x, y, deltaX, deltaY);
        _color = c;
        colourString = getColourName(_color);
    }

    /** Creates a DynamicRectangleShape with specified values for location, velocity,
     * directions, width, height, color value and colour string value.
     */
    public DynamicRectangle(int x, int y, int deltaX, int deltaY, int width, int height, Color c) {
        super(x,y,deltaX,deltaY,width,height);
        _color = c;
        colourString = getColourName(_color);
    }

    /** Creates a DynamicRectangleShape with specified values for location, velocity,
     * directions, width, height and text.
     */
    public DynamicRectangle(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        this.text = text;
    }

    /** Creates a DynamicRectangleShape with specified values for location, velocity,
     * directions, width, height, text and color value.
     */
    public DynamicRectangle(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color c) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        this.text = text;
        _color = c;
    }

    /** Takes over the painter interface and if the dynamic boolean is true, then the shape is
     * solid, otherwise the shape is hollow.
     */
    protected void drawShape(Painter painter) {
        Color temp = painter.getColour();
        String tempColor = getColourName(temp);
        painter.setColor(_color, colourString);
        if (_dynamic) {
            painter.fillRect(_x, _y, _width, _height, colourString);
        } else {
            painter.drawRect(_x, _y, _width, _height);
        }
        painter.setColor(temp, tempColor);
    }

    // Calls the move method in the parent class but adds the feature of changing dynamic boolean
    // everytime it touches the wall. This allows the changing of solid and empty colors as it
    // bounces off the walls.
    public void move(int width, int height) {
        super.move(width, height);
        if (_x == 0 || (_x + _width) == width) {
            _dynamic = true;
        } else if (_y == 0 ||(_y + _height) == height) {
            _dynamic = false;
        }
    }
}
