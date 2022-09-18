package bounce;

public class GemShape extends Shape {

    public GemShape(){
        super();
    }

    // Creating a GemShape instance with variables
    public GemShape(int x, int y) {
        super(x, y);
    }

    /** Creates a GemShape with specified values for location, velocity
     * and directions. Non-specified state items take on default values.
     */
    public GemShape(int x, int y, int deltaX, int deltaY) {
        super(x, y, deltaX, deltaY);
    }

    /** Creates a GemShape with specified values for location, velocity,
     * directions, width and height.
     */
    public GemShape(int x, int y, int deltaX, int deltaY, int width, int height) {
       super(x,y,deltaX,deltaY,width,height);
    }

    /** Creates a GemShape with specified values for location, velocity,
     * directions, width, height and text.
     */
    public GemShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
        _x = x;
        _y = y;
        _deltaX = deltaX;
        _deltaY = deltaY;
        _width = width;
        _height = height;
        this.text = text;
    }

    /** Draws the gemShape depending on the width length (if width > it becomes a hexagon,
     * otherwise it's a diamond shape.
     */
    protected void drawShape(Painter painter) {

        // For a hexagon shape
        if (_width >= 40) {
            int[] CoordsX = new int[6];
            CoordsX[0] = _x + 20;            // Top left point
            CoordsX[1] = _x + _width -20;    // Top right point
            CoordsX[2] = _x + _width;        // Right point
            CoordsX[3] = CoordsX[1];         // Bottom right point
            CoordsX[4] = CoordsX[0];         // Bottom left point
            CoordsX[5] = _x;                 // Left point

            int[] CoordsY = new int[6];
            CoordsY[0] = _y;                 // Top left point
            CoordsY[1] = CoordsY[0];         // Top right point
            CoordsY[2] = _y + _height/2;     // Right point
            CoordsY[3] = _y + _height;       // Bottom right point
            CoordsY[4] = CoordsY[3];         // Bottom left point
            CoordsY[5] = CoordsY[2];         // Left point

            for (int i = 0; i < 6; i++) {
                if (i < 5) {
                    painter.drawLine(CoordsX[i], CoordsY[i], CoordsX[i + 1], CoordsY[i + 1]);
                } else {
                    painter.drawLine(CoordsX[i], CoordsY[i], CoordsX[0], CoordsY[0]);
                    break;
                }
            }
        }

        // For a diamond shape
        else if (_width < 40) {
            int[] CoordsX = new int[4];
            CoordsX[0] = _x;                 // Left point
            CoordsX[1] = _x + _width/2;      // Top point
            CoordsX[2] = _x + _width;        // Right point
            CoordsX[3] = CoordsX[1];         // Bottom point

            int[] CoordsY= new int[4];
            CoordsY[0] = _y + _height/2;     // Left point
            CoordsY[1] = _y;                 // Top point
            CoordsY[2] = _y + _height/2;     // Right point
            CoordsY[3] = _y + _height;       // Bottom point

            for (int i = 0; i < 4; i++) {
                if (i < 3) {
                    painter.drawLine(CoordsX[i], CoordsY[i], CoordsX[i + 1], CoordsY[i + 1]);
                } else {
                    painter.drawLine(CoordsX[i], CoordsY[i], CoordsX[0], CoordsY[0]);
                    break;
                }
            }
        }
    }
}
