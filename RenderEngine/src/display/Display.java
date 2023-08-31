package display;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


/**
 * Modified from Obicere
 * https://stackoverflow.com/questions/29449436/updating-a-java-canvas-that-is-a-component-of-a-jframe
 */
public class Display extends Canvas{

    private int width = 1000;
    private int height = 750;
    private int frameTime = 15;
    private Rgb pixelValues[][] = new Rgb[width][height];
    
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public int getFrameTime() {
        return frameTime;
    }

    public Display(Keyboard keyboard) {

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                setPixel(x, y, new Rgb(0, 0, 0, 0));
            }
        }

    
        final JFrame frame = new JFrame("Shape Drawer");
        final MyPanel panel = new MyPanel();

        frame.add(panel);
        frame.addKeyListener(keyboard);


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setSize(getWidth(), getHeight());

        createRepaintTimer(frame);

        
        

    }

    // Just makes calls for the frame to paint every 15 milliseconds
    private void createRepaintTimer(final JFrame frame) {
        final Timer timer = new Timer(frameTime, null);

        timer.addActionListener(e -> {
            if (!frame.isVisible()) {
                timer.stop();
            } else {
                frame.repaint();
            }
        });

        timer.start();
    }

    // public static void main(final String[] args) {
    //     SwingUtilities.invokeLater(Display::new);
    // }

    public class MyPanel extends JPanel {

        @Override
        protected void paintComponent(final Graphics g) {

            

            int w = getWidth();
            int h = getHeight();

            int type = BufferedImage.TYPE_INT_ARGB;

            BufferedImage image = new BufferedImage(w, h, type);
                       

            for(int x = 0; x < w; x++) {
                for(int y = 0; y < h; y++) {
                    image.setRGB(x, y, getPixel(x,y));
                }
            }

            g.drawImage(image, 0, 0, null);
        }
    }
    private int getPixel(int x, int y) {

        int r = pixelValues[x][y].r;
        int g = pixelValues[x][y].g;
        int b = pixelValues[x][y].b;
        int a = pixelValues[x][y].a;

        return new Color(r, g, b, a).getRGB();
    }

    public void setPixel(int x,int y, Rgb rgb) {
        pixelValues[x][y] = rgb;
    }


    public void clear() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                setPixel(x, y, new Rgb(0, 0, 0, 0));
            }
        }
    }
}