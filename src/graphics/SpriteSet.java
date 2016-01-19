/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import static graphics.SpriteSheet.*;

/**
 *
 * @author Terrell
 */
public class SpriteSet {

    public final int SIZE;
    private final int width, height;
    private final Sprite[] sprites;

    public SpriteSet(int x, int y, int width, int height, SpriteSheet sheet) {
        this.width = width;
        this.height = height;
        SIZE = sheet.SIZE;
        sprites = new Sprite[width * height];
        for (int y1 = 0; y1 < height; y1++) {
            int yOff = y1 + y;
            for (int x1 = x; x1 < x + width; x1++) {
                int xOff = x1 + x;
                sprites[y1 * width + x1] = new Sprite(xOff, yOff, sheet);
            }
        }
    }

    public Sprite getSprite(int x, int y) {
        return sprites[y * width + x];
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public static final SpriteSet PLAYER_SET = new SpriteSet(0, 0, 4, 3, PLAYER);
    public static final SpriteSet WIZARD_SET = new SpriteSet(0, 0, 3, 3, WIZARD);
}
