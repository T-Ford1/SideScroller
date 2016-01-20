/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        for (int cY = 0; cY < height; cY++) {
            for (int cX = 0; cX < width; cX++) {
                corner_pixels[cY * width + cX] = 0xFF_6C_A6_73;
            }
        }
    }
    
    public void render(GameCanvas canvas) {
        canvas.renderPixels(x, y, corner_pixels, width, height, 1);
    }
}
