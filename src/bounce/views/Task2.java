package bounce.views;

import bounce.ShapeModel;
import bounce.ShapeModelEvent;
import bounce.ShapeModelListener;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

/** Task 2 is an extension of task 1, which allows for the modelling of the
 *  JTree as a ShapeModel Component. It transforms ShapeModel events into
 *  TreeModel which is fed into TreeModelListener. It implements the
 *  ShapeModelListener interface which describes the changes to the tree
 *  model.
 *  @author Ryan Orense, rore098
 */
public class Task2 extends Task1 implements ShapeModelListener {

    public Task2(ShapeModel _model) { super (_model); }

    /** The update method accurately depicts when an addition or removal of
     * a shape from a ShapeModel, utilising ShapeModelEvent.
     */
    @Override
    public void update (ShapeModelEvent event) {
        ShapeModelEvent.EventType eventType = event.eventType();

        // Gets the source of the event.
        ShapeModel source = event.source();

        // Records the shape's index after addition and before removal.
        int[] childIndices = {event.index()};

        // Records if the shape's index is being removed.
        Object[] children = {event.operand()};

        // If the eventType is adding shapes,it inserts the treeModelEvent
        // to the listener.
        if (eventType == ShapeModelEvent.EventType.ShapeAdded) {
            TreeModelEvent e = new TreeModelEvent(source, event.parent().path().toArray(), childIndices, children);

            for (TreeModelListener l: _treeModelListener) {
                l.treeNodesInserted(e);
            }

        // If the eventType is removing shapes,it removes the treeModelEvent
        // from the listener.
        } else if (eventType == ShapeModelEvent.EventType.ShapeRemoved) {
            TreeModelEvent e = new TreeModelEvent(source, event.parent().path().toArray(), childIndices, children);

            for (TreeModelListener l: _treeModelListener) {
                l.treeNodesRemoved(e);
            }
        }
    }
}
