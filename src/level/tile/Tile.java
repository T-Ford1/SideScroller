/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.tile;

import game.GameCanvas;
import graphics.Sprite;

/**
 *
 * @author Terrell
 */
public abstract class Tile {
    
    public int x, y;
    public Sprite sprite;
    
    public Tile(Sprite s) {
        sprite = s;
    }
    
    public abstract void render(int x, int y, GameCanvas frame);
    
    public abstract boolean isSolid();
    
    public boolean isLiquid() {
        return false;
    }
    
    public int getPixel(int x, int y) {
        return sprite.getPixel(x, y);
    }
    
    public int[] getPixels() {
        return sprite.getPixels(); 
    }
    
    public static Tile VOID_TILE = new VoidTile(Sprite.VOID);
    public static Tile MUD_TILE = new BackgroundTile(Sprite.MUD);
    public static Tile ROCK_TILE = new RockTile(Sprite.ROCK);
    public static Tile LAVA_TILE = new LiquidTile(Sprite.LAVA);
    public static Tile DIRT_TILE = new BackgroundTile(Sprite.DIRT);
    public static Tile GRASS_TILE = new BackgroundTile(Sprite.GRASS);
    public static Tile WATER_TILE = new LiquidTile(Sprite.WATER);
    public static Tile WOOD_TILE = new BackgroundTile(Sprite.WOOD);
}
