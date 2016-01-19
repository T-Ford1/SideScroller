/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Terrell
 */
public class SpriteSheet {

    private final String path;
    public final int SIZE;
    private int width;
    private int[] pixels;

    public SpriteSheet(String p, int s) {
        SIZE = s;
        path = p;
        try {
            BufferedImage image = ImageIO.read(new File(path));
            pixels = new int[(width = image.getWidth()) * image.getHeight()];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getPixel(int x, int y) {
        return pixels[y * width + x];
    }
    
    public int[] getPixels(int xOff, int yOff) {
        int[] sprite_pixels = new int[SIZE * SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                sprite_pixels[y * SIZE + x] = pixels[(yOff + y) * width + xOff + x];
            }
        }
        return sprite_pixels;
    }
    
    public static final SpriteSheet PIXEL_16_SHEET = new SpriteSheet("./res/textures/sprites_16pix.png", 16);
    public static final SpriteSheet PIXEL_32_SHEET = new SpriteSheet("./res/textures/sprites_32pix.png", 32);
    
    public static final SpriteSheet PLAYER = new SpriteSheet("./res/textures/sprites_32pix_player.png", 32);
    public static final SpriteSheet WIZARD = new SpriteSheet("./res/projectiles/wizard.png", 64);
}
