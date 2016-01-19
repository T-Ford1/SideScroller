
package graphics;

/**
 *
 * @author Terrell
 */

import static graphics.SpriteSheet.*;

public class Sprite {

    public final int SIZE;
    private final int[] pixels;
    
    
    public Sprite(int x, int y, SpriteSheet s) {
        SIZE = s.SIZE;
        pixels = s.getPixels(x * SIZE, y * SIZE);
    }
    
    public int getPixel(int x, int y) {
        return pixels[y * SIZE + x];
    }
    
    public void setColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = color;
        }
    }
    
    public void removeColor(int color) {
        for (int i = 0; i < pixels.length; i++) {
            if(pixels[i] == color) {
                pixels[i] = -1;
            }
        }
    }
    
    public int[] getPixels() {
        return pixels;
    }
    
    public static final Sprite VOID = new Sprite(0, 0, PIXEL_32_SHEET);
    public static final Sprite GRASS = new Sprite(3, 2, PIXEL_32_SHEET);
    public static final Sprite MUD = new Sprite(4, 0, PIXEL_32_SHEET);
    public static final Sprite ROCK = new Sprite(1, 2, PIXEL_32_SHEET);
    public static final Sprite LAVA = new Sprite(4, 1, PIXEL_32_SHEET);
    public static final Sprite DIRT = new Sprite(2, 2, PIXEL_32_SHEET);
    public static final Sprite WATER = new Sprite(2, 1, PIXEL_32_SHEET);
    public static final Sprite WOOD = new Sprite(3, 1, PIXEL_32_SHEET);
}
