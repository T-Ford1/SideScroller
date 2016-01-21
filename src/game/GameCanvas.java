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

    public void renderDisplay(int xPos, int yPos, int[] pixels, int width, int height) {
        for (int y = 0; y < height; y++) {
            int yOff = y + yPos;
            int index = y * width;
            for (int x = 0; x < width; x++, index++) {
                int xOff = x + xPos;
                this.pixels[yOff * getWidth() + xOff] = pixels[index];
            }
        }
    }

    public void renderPixels(int xPos, int yPos, int[] pixels, int width, int height, int scale) {
        for (int y = 0; y < height; y++) {
            int yOff = y * scale + yPos;
            int index = y * width;
            if (yOff < 0 || yOff >= getRenderHeight()) {
                continue;
            }
            for (int x = 0; x < width; x++, index++) {
                int xOff = x * scale + xPos;
                if (xOff < 0 || xOff >= getRenderWidth()) {
                    continue;
                }
                renderPixel(yOff * getWidth() + xOff, pixels[index], scale);
            }
        }
    }

    private void renderPixel(int index, int color, int scale) {
        if (color == 0xff_ff_00_ff) {
            return;
        }
        for (int yOff = 0; yOff < scale; yOff++) {
            int index2 = yOff * getWidth() + index;
            for (int xOff = 0; xOff < scale; xOff++, index2++) {
                if (index2 < 0 | index2 >= pixels.length) {
                    continue;
                }
                pixels[index2] = color;
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
