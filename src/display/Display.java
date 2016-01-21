
package display;

import game.GameCanvas;

/**
 *
 * @author ford.terrell
 */
public class Display {
    
    private final int x, y, width, height;
    private final int[] right_pixels, bottom_pixels, corner_pixels;
    
    public Display(GameCanvas canvas) {
        x = canvas.getRenderWidth();
        y = canvas.getRenderHeight();
        //the x position and y position
        //that the rendering of the game
        //display begins
        width = canvas.getWidth() - canvas.getRenderWidth();
        height = canvas.getHeight() - canvas.getRenderHeight();
        //not the actual amounts of heights rendered
        //actually measuring the distance to the
        //edge of the screen
        right_pixels = new int[y * width];
        bottom_pixels = new int[x * height];
        corner_pixels = new int[width * height];
        for (int rY = 0; rY < y; rY++) {
            for (int rX = 0; rX < width; rX++) {
                right_pixels[rY * width + rX] = 0xFF_A5_30_92;
            }
        }
        for (int bY = 0; bY < height; bY++) {
            for (int bX = 0; bX < x; bX++) {
                bottom_pixels[bY * x + bX] = 0xFF_76_F4_D9;
            }
        }
        for (int cY = 0; cY < height; cY++) {
            for (int cX = 0; cX < width; cX++) {
                //corner_pixels[cY * width + cX] = 0xFF_6C_A6_73;
                corner_pixels[cY * width + cX] = (right_pixels[0] + bottom_pixels[0]) / 2;
            }
        }
    }
    
    public void render(GameCanvas canvas) {
        canvas.renderDisplay(x, y, corner_pixels, width, height);
        canvas.renderDisplay(x, 0, right_pixels, width, y);
        canvas.renderDisplay(0, y, bottom_pixels, x, height);
    }
}
