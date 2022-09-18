package bounce;

import java.awt.*;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Ian Warren
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private final Graphics _g;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
	}

	/**
	 * see bounce.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) { _g.drawRect(x, y, width, height); }

	/**
	 * see bounce.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * see bounce.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

	public void fillRect(int x, int y, int width, int height, String color) {_g.fillRect(x, y, width, height);}

	public Color getColour(){
		return _g.getColor();
	}

	public void setColor(Color c,String color){ _g.setColor(c); }

	public void translate(int x, int y){
		_g.translate(x,y);
	}

	/** A method to draw the text onto the centre of the shape
	 */
	public void drawCentredText(String text, int x, int y, int width, int height) {
		FontMetrics fm = _g.getFontMetrics();
		int ascent = fm.getAscent();
		int descent = fm.getDescent();

		// Getting the x coordinate of the strings top left corner.
		int xPos = x - fm.stringWidth(text)/2 + width/2;
		// Getting the y coordinate of the strings top left corner.
		int yPos = y + height/2;

		if( ascent > descent )
			yPos += (ascent - descent) / 2;
		else if (ascent < descent)
			yPos -= (descent - ascent) / 2;

		_g.drawString(text, xPos, yPos);
	}

	@Override
	public void drawImage(Image picture, int x, int y, int width, int height) {

	}
}