package bounce.forms;

import bounce.Shape;
import bounce.ImageRectangleShape;
import bounce.NestingShape;
import bounce.ShapeModel;
import bounce.forms.ImageFormElement;
import bounce.forms.ShapeFormElement;
import bounce.forms.util.Form;
import bounce.forms.util.FormHandler;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;



/** ImageShapeFormHandler class allows for a more accurate image shape form
 *  loading with image scaling using background thread. An improvement from
 *  the SimpleImageShapeFormHandler. The biggest improvement is that it
 *  includes a swing worker, therefore making it run faster.
 * @author Ryan Orense, rore098
 */
public class ImageShapeFormHandler implements FormHandler {

    private ShapeModel _model;
    private NestingShape _parent;

    // Creates a ImageShapeFormHandler
    public ImageShapeFormHandler(ShapeModel model, NestingShape nest) {
        _model = model;
        _parent = nest;
    }

    /**
     * Reads form data that describes an ImageRectangleShape. Based on the
     * data, this ImageShapeFormHandler creates a new ImageRectangleShape
     * object, adds it to a ShapeModel and to a NestingShape within the model.
     */
    public void processForm(Form _form) {
        long startTime = System.currentTimeMillis();

        // Reads the values from the form
        File imageFile = (File) _form.getFieldValue(File.class, ImageFormElement.IMAGE);
        int width = _form.getFieldValue(Integer.class, ShapeFormElement.WIDTH);
        int deltaX = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_X);
        int deltaY = _form.getFieldValue(Integer.class, ShapeFormElement.DELTA_Y);

        // Calls on a swingworker.
        SwingWorker<Shape, Void> worker = new SwingWorker<Shape, Void>() {

            /** The doInBackground function contains the logic of the background
             * task. This is usually the task that is time consuming and by
             * introducing it, it will make the application run faster.
             * This runs on the worker thread.
             */
            @Override
            protected Shape doInBackground() throws Exception {

                // This loads the original image.
                BufferedImage fullImage = null;
                try {
                    fullImage = ImageIO.read(imageFile);
                } catch (IOException e) {
                    System.out.println("Error");
                }

                int fullImageWidth = fullImage.getWidth();
                int fullImageHeight = fullImage.getHeight();

                BufferedImage scaledImage = fullImage;

                // Scaling the image
                if (fullImageWidth > width) {
                    double scaleFactor = (double) width / (double) fullImageWidth;
                    int height = (int) ((double) fullImageHeight * scaleFactor);

                    scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                    Graphics2D g = scaledImage.createGraphics();

                    // Method drawImage() scales an already loaded image. The
                    // ImageObserver argument is null because we don't need to monitor
                    // the scaling operation.
                    g.drawImage(fullImage, 0, 0, width, height, null);
                }


                // Creates the new shape and adds it to the model.
                ImageRectangleShape imageShape = new ImageRectangleShape(deltaX, deltaY, scaledImage);
                return imageShape;
            }

            /** This function is called when the thread finished its execution.
             */
            @Override
            protected void done() {
                try {
                    _model.add(get(), _parent);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                // Displays the time it took for the image to load.
                long elapsedTime = System.currentTimeMillis() - startTime;
                System.out.println("Image loading and scaling took " + elapsedTime + "ms.");
            }
        };
        worker.execute();
    }
}