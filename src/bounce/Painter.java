package bounce;

import java.awt.*;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Ian Warren
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);

	/** Gets the color object
	 */
	public Color getColour();

	/** Sets the color depending on the color object specified.
	 */
	public void setColor(Color c, String color);

	/** Fills the rectangle as required.
	 */
	public void fillRect(int x, int y, int width, int height, String color);

	/** The translate method changes the origin of the shape into the required
	 * coordinates.
	 */
	public void translate(int x, int y);

	public void drawCentredText(String text, int x, int y, int width, int height);

    public void drawImage(Image picture, int x, int y, int width, int height);
}

