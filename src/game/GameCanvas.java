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
        width = getWidth();
        height = getHeight();
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
        
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

    public void renderPixels(int xPos, int yPos, int[] pixels, int size, int scale) {
        for (int y = 0; y < size; y++) {
            int yOff = y * scale + yPos;
            for (int x = 0; x < size; x++) {
                int xOff = x * scale + xPos;
                renderPixel(xOff, yOff, pixels[y * size + x], scale);
            }
        }
    }

    public void renderPixel(int x, int y, int color, int scale) {
        if(color == 0xff_ff_00_ff) {
            return;
        }
        for (int yOff = y; yOff < scale + y; yOff++) {
            for (int xOff = x; xOff < scale + x; xOff++) {
                if (yOff < 0 || yOff >= height || xOff < 0 || xOff >= width) {
                    return;
                    //the pixel is out of the screen area
                }
                pixels[yOff * width + xOff] = color;
            }
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void clear() {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = Color.black.getRGB();
        }
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
    }
}
