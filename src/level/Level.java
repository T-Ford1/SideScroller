/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import game.Game;
import game.GameCanvas;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import level.tile.Tile;

/**
 *
 * @author Terrell
 */
public abstract class Level {

    protected int width, height;
    protected int[] tiles;

    public Level(int w, int h) {
        width = w;
        height = h;
        tiles = new int[width * height];
    }
    
    public Level(String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));
            height = image.getHeight();
            width = image.getWidth();
            tiles = new int[width * height];
            image.getRGB(0, 0, image.getWidth(), image.getHeight(), tiles, 0, image.getWidth());
            for (int i = 0; i < tiles.length; i++) {
                tiles[i] &= 255;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        
    }

    public abstract void update();

    public void render(int xPos, int yPos, GameCanvas frame) {
        int step = Game.SIZE * Game.SCALE;
        int xLeft = xPos / step;
        int xRight = (xPos + frame.getWidth()) / step;
        int yTop = yPos / step;
        int yBottom = (yPos + frame.getHeight()) / step;
        //choosing the first and last tiles to render
        for (int y = yTop - 1; y <= yBottom; y++) {
            for (int x = xLeft - 1; x <= xRight; x++) {
                getTile(x, y).render(x * step - xPos, y * step - yPos, frame);
            }
        }
    }

    public Tile getTile(int x, int y) {
        int index = x + y * width;
        if (index >= 0 && index < tiles.length && x >= 0 && x < width && y >= 0) {
            switch(tiles[index]) {
                case 0:
                    return Tile.LAVA_TILE;
                case 1:
                    return Tile.DIRT_TILE;
                case 2:
                    return Tile.GRASS_TILE;
                case 3:
                    return Tile.WOOD_TILE;
                case 4:
                    return Tile.ROCK_TILE;
            }
        }
        return Tile.VOID_TILE;
    }
    
    public Tile getTilePixels(int x, int y) {
        int s = Game.SIZE * Game.SCALE;
        if (x >= 0 && y >= 0) {
            return getTile(x / s, y / s);
        }
        return Tile.VOID_TILE;
    }
}
