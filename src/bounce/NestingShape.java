package bounce;

import java.util.ArrayList;
import java.util.List;

/** Creates a NestingShape object with default values for state
 * @author: Ryan Orense, rore098
 */

public class NestingShape extends Shape {

    protected List<Shape> _shapes = new ArrayList<Shape>();

    public NestingShape() { super(); }

    /** Creates a NestingShape object with specified location values,
     * default values for other state items
     */
    public NestingShape(int x, int y){ super(x,y); }

    /** Creates a NestingShape with specified values for location, velocity
     * and directions. Non-specified state items take on default values.
     */
    public NestingShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    /** Creates a NestingShape with specified values for location, velocity,
     * direction, width and height.
     */
    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height) {
        super(x, y, deltaX, deltaY, width, height);
    }

    /** Creates a NestingShape with specified values for location, velocity,
     * direction, width, height and text.
     */
    public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        this.text = text;
    }

    /** Attempts to add a shape to the NestingShape object. If successful
     * a 2-way link is established between the Nesting Shape and the newly
     * added shape. Has Package visibility. Must throw an IllegalArgumentException
     * if an attempt to add a shape to a nesting shape instance where the
     * shape is already a child within a NestingShape instance and also when
     * the shape does not fit the bounds of the proposed NestingShape object.
     */
    public void add(Shape shape) {
        if (shape._parent != null) {
            throw new IllegalArgumentException();
        } else if (shape._x < this._x) {
            throw new IllegalArgumentException();
        } else if (shape._y < this._y) {
            throw new IllegalArgumentException();
        } else  if (shape._width + shape._x > this._width + this._x) {
            throw new IllegalArgumentException();
        } else if (shape._height + shape._y > this._height + this._y) {
            throw new IllegalArgumentException();
        } else {
            _shapes.add(shape);
            shape.setParent(this);
        }
    }

    // Draws the NestingShape object (including its children) within the
    // bounds set by the bounding box. The nesting shape children are then
    // painted. The translate method sets the shapes coordinates into the
    // desired location.
    protected void drawShape(Painter painter) {
        // draws the biggest rectangle;
        painter.drawRect(_x, _y, _width, _height);
        painter.translate(_x, _y);
        // draws the smaller shapes
        for (Shape s : _shapes) {
            s.paint(painter);
        }
        painter.translate(-_x, -_y);
    }

    // Moves the NestingShape object (including it's children) within
    // the bounds specified by arguments width and height.
    public void move(int width, int height) {
        super.move(width, height);
        for (Shape s : _shapes) {
            s.move(_width, _height);
        }
    }

    /** Returns true if the shape argument is a child of the NestingShape
     * object on which this method is called, false otherwise.
     */
    public boolean contains(Shape shape) {
        return _shapes.contains(shape);
    }

    /** Removes a particular shape from a NestingShape instance. Once removed
     * the 2 way link between the shapes is destroyed. This method has no
     * effect if the the shape specified to remove is not a child of the
     * NestingShape. Has package visibility.
     */
    public void remove(Shape shape) {
        _shapes.remove(shape);
        shape._parent = null;
    }

    /** Returns the shape at a specified position within a NestingShape.
     * If the position specified is less than zero or greater than the number
     * of children stored in the NestingShape less one this method throws an
     * IndexOutOfBoundException
     */
    public Shape shapeAt(int i) {
        if ( i >= _shapes.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            return _shapes.get(i);
        }
    }

    /** Returns the number of children contained within a NestingShape
     * object. Note this method is not recursive - it simply returns
     * the number of children at the top level within the callee NestingShape
     * object.
     */
    public int shapeCount() {
        return _shapes.size();
    }

    /** Returns the index of a specified child within a NestingShape object.
     * If the shape specified is not actually a child of the NestingShape
     * this method returns -1; otherwise the value returned is in the range
     * required.
     */
    public int indexOf(Shape shape) {
        if (this.contains(shape)){
            return _shapes.indexOf(shape);
        } else {
            return -1;
        }
    }
}
