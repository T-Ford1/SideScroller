package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import input.Keyboard;
import input.Mouse;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 *
 * @author ford.terrell
 */
public class GameCanvas extends Canvas {

    private static final long serialVersionUID = 1L;
    private int width, height;
    private BufferedImage image;
    private int[] pixels;

    public void renderString(Graphics g, String s) {
        g.setColor(Color.black);
        g.drawString(s, 10, 25);
    }

    public void initRender() {
        requestFocus();
        setRenderSize(getWidth() - 300, getHeight() - 200);
    }

    public Keyboard addKeys() {
        Keyboard keys = new Keyboard();
        addKeyListener(keys);
        return keys;
    }

    public Mouse addMouse() {
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        return mouse;
    }

    public void renderPixels(int xPos, int yPos, int[] pixels, int width, int height, int scale) {
        for (int y = 0; y < height; y++) {
            int yOff = y * scale + yPos;
            int index = y * width;
            for (int x = 0; x < width; x++) {
                int xOff = x * scale + xPos;
                renderPixel(xOff, yOff, pixels[index + x], scale);
            }
        }
    }

    public void renderPixel(int x, int y, int color, int scale) {
        if (color == 0xff_ff_00_ff) {
            return;
        }
        for (int yOff = y; yOff < scale + y; yOff++) {
            int index = yOff * getWidth();
            if (yOff < 0 || yOff >= height) {
                return;
            }
            for (int xOff = x; xOff < scale + x; xOff++) {
                if (xOff < 0 || xOff >= width) {
                    return;
                }
                //the pixel is out of the screen area
                pixels[index + xOff] = color;
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public int getRenderHeight() {
        return height;
    }

    public int getRenderWidth() {
        return width;
    }

    public void setRenderSize(int w, int h) {
        width = w;
        height = h;
        image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    }
}
