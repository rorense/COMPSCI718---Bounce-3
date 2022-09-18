package bounce.views;

import bounce.NestingShape;
import bounce.Shape;
import bounce.ShapeModel;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import java.util.ArrayList;
import java.util.List;

/** Task 1 displays the ShapeModel's shape composition as a JTree Component.
 *  This class will be implemented to the TreeViewer class to display a GUI
 *  of a hierarchy view of the ShapeModel.
 *  Utilises the adapter design method.
 *  @author Ryan Orense, rore098
 */
public class Task1 implements TreeModel {

    protected ShapeModel _adaptee;

    // Protected listener for use in task 2
    protected List <TreeModelListener> _treeModelListener;

    /** Creates an array list with TreeModelListener in mind to hold
     * shape composition.
     */
    public Task1 (ShapeModel model) {
        _adaptee = model;
        _treeModelListener = new ArrayList<>();
    }

    /** Attempts to get the child index from the parent using the
     * instanceof operator. If the parent object is an instance of the
     * the NestingShape, it returns the child's index.
     * Otherwise an error is caught, but nothing happens. This is because
     * it must not return a null value. Therefore, a try-catch block is
     * implemented.
     */
    @Override
    public Object getChild(Object parent, int index) {
        Object result = null;

        try {
            if (parent instanceof NestingShape) {
                NestingShape nestingShape = (NestingShape) parent;
                result = nestingShape.shapeAt(index);
            }
        } catch (IndexOutOfBoundsException e ) {

            }
            return result;
        }

    /**
     * Using instanceof operator once again, this method attempts to get
     * the number of children contained within a NestingShape object.
     * If the node is a leaf or has no children, it returns a 0.
     * parent should be a node previously obtained from the data source.
     */
    @Override
    public int getChildCount (Object parent) {
        int result = 0;
        Shape shape = (Shape) parent;

        if (shape instanceof NestingShape) {
            NestingShape nestingShape = (NestingShape) shape;
            result = nestingShape.shapeCount();
        }
        return result;
    }

    /**
     * Using instanceof operator once again, this method attempts to get
     * the index of the specified child within the NestingShape object.
     * If the shape is not a child of the NestingShape it returns a -1.
     */
    @Override
    public int getIndexOfChild (Object parent, Object child) {
        int indexOfChild = -1;

        if (parent instanceof NestingShape) {
            NestingShape nest = (NestingShape) parent;
            Shape shape = (Shape) child;
            indexOfChild = nest.indexOf(shape);
        }
        return indexOfChild;
    }

    /** Adds the listener for the treeModelEvent posted after the tree changes.
     */
    @Override
    public void addTreeModelListener(TreeModelListener l) {
        _treeModelListener.add(l);
    }

    // Returns the root of the tree. Returns null if the tree has no nodes.
    @Override
    public Object getRoot() { return _adaptee.root(); }

    // Returns a boolean characteristic if the node object is a leaf of the
    // nesting shape. If node is a leaf, it returns a true. Otherwise return
    // a false. The node in this case is the the node obtained from the
    // data source.
    @Override
    public boolean isLeaf(Object node) { return !(node instanceof NestingShape); }


    // Removes the listener added previously from the addTreeModelListener method.
    @Override
    public void removeTreeModelListener (TreeModelListener l) {
        _treeModelListener.remove(1);
    }

    /** If the user has changed the value for the item identified by path
     * to newValue. For this task, it has been left empty.
     */
    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {

    }
}
