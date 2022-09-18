package bounce.views;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;

import javax.swing.tree.TreeModel;

import bounce.NestingShape;
import bounce.RectangleShape;
import org.junit.Before;
import org.junit.Test;

import bounce.Shape;
import bounce.ShapeModel;

/**
 * Class to test the TreeModel implementation of class Task1.
 * 
 * @author Ian Warren
 * 
 */
public class TestTask1 {

	private NestingShape _root;
	private NestingShape _emptyNest;
	private Shape _simpleShape;
	private TreeModel _adapter;

	/**
	 * Creates a simple Shape hierarchy as the common fixture for all test
	 * case methods.
	 */
	@Before
	public void setUpShapeModel() {
		ShapeModel model = new ShapeModel(new Dimension(500, 500));
		_adapter = new Task1(model);
			
		_root = model.root();
		_emptyNest = new NestingShape(0, 0, 2, 2, 100, 100);
		_simpleShape = new RectangleShape(0, 0, 1, 1, 50, 50);
			
		model.add(_emptyNest, _root);
		model.add(_simpleShape, _root);
	}
	
	/**
	 * Checks whether getRoot() returns the root NestingShape of a the 
	 * ShapeModel as expected.
	 */
	@Test
	public void test_getRoot() {
		NestingShape nest = ( NestingShape )_adapter.getRoot();
		assertSame( _root, nest );
	}
	
	/**
	 * Checks whether getChildCount() returns zero for an empty NestingShape.
	 */
	@Test
	public void test_getChildCount_OnEmptyNestingShape() {
		int numberOfChildren = _adapter.getChildCount( _emptyNest );
		assertEquals( numberOfChildren, _emptyNest.shapeCount() );
	}
	
	/**
	 * Checks whether getChildCount() returns the actual number of children 
	 * contained in a NestingShape.
	 */
	@Test
	public void test_getChildCount_OnNonEmptyNestingShape() {
		int expectedNumberOfChildren = _adapter.getChildCount( _root );
		int actualNumberOfChidren = _root.shapeCount();
		
		assertEquals( expectedNumberOfChildren, actualNumberOfChidren );
	}
	
	/**
	 * Checks whether getChildCount() returns zero when invoked with an 
	 * argument that refers to a simple Shape instance.
	 */
	@Test
	public void test_getChildCount_OnSimpleShape() {
		int actualNumberOfChildren = _adapter.getChildCount( _simpleShape );
		assertEquals( 0, actualNumberOfChildren );
	}
	
	/**
	 * Checks whether isLeaf() returns false, as required, when supplied with
	 * an empty NestingShape as argument.
	 */
	@Test
	public void test_isLeaf_OnEmptyNestingShape() {
		assertFalse( _adapter.isLeaf( _emptyNest ) );
	}

	/**
	 * Checks whether isLeaf() returns false, as required, when supplied with
	 * a NestingShape that contains children.
	 */	
	@Test
	public void test_isLeaf_OnNonEmptyNestingShape() {
		assertFalse( _adapter.isLeaf( _root ) );
	}
	
	/**
	 * Checks whether isLeaf() returns true, as required, when supplied with
	 * a simple Shape as argument.
	 */
	@Test
	public void test_isLeaf_OnSimpleShape() {
		assertTrue( _adapter.isLeaf( _simpleShape ) );
	}
	
	/**
	 * Checks whether getChild() correctly returns a reference to a 
	 * particular child Shape object. The arguments supplied to getChild() are
	 * a reference to a NestingShape and the index position within the
	 * NestingShape's collection of children that identifies the child Shape 
	 * sought. This particular test supplies a valid index argument.
	 */
	@Test
	public void test_getChild_OnNestingShapeWithInRangeIndex() {
		assertSame( _emptyNest, _adapter.getChild( _root, 0 ) );
	}
	
	/**
	 * Checks whether getChild() returns null, as specified, when an index
	 * argument value is supplied that is out of range. The argument is out of
	 * range if it is negative or >= the number of children contained in the 
	 * NestingShape, which is the first argument.
	 */
	@Test
	public void test_getChild_OnNestingShapeWithOutOfRangeIndex() {
		assertNull( _adapter.getChild( _root, 2 ) );
	}
	
	/**
	 * Checks whether getChild() returns null, as it should when supplied
	 * with a reference to a simple Shape object as the first argument.
	 */
	@Test
	public void test_getChild_OnSimpleShape() {
		assertNull( _adapter.getChild( _simpleShape, 0 ) );
	}
	
	/**
	 * Checks whether getIndexOfChild() returns -1 as specified when supplied
	 * with a reference to a Shape (the second argument) which is not a child of
	 * the NestingShape supplied as the first argument.
	 */
	@Test
	public void test_getIndexOfChild_OnNestingShapeWithNonChild() {
		Shape newShape = new RectangleShape( 0, 0, 1, 1, 10, 10 );
		assertEquals( -1, _adapter.getIndexOfChild( _root, newShape ) );
	}
	
	/**
	 * Checks whether getIndexOfChild() returns the correct index position of
	 * a Shape (second argument) that is a child of a NestingShape (the first 
	 * argument).
	 */
	@Test
	public void test_getIndexOfChild_OnNestingShapeWithChild() {
		assertEquals( 1, _adapter.getIndexOfChild( _root, _simpleShape ) );
	}
	
	/**
	 * Checks whether getIndexOfChild() returns -1 when the first argument
	 * refers to a simple Shape.
	 */
	@Test
	public void test_getIndexOfChild_OnSimpleShape() {
		assertEquals( -1, _adapter.getIndexOfChild( _simpleShape, _root ) );
	}

}
