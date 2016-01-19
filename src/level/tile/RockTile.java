/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.tile;

import game.Game;
import game.GameCanvas;
import graphics.Sprite;

/**
 *
 * @author Terrell
 */
public class RockTile extends Tile {

    public RockTile(Sprite s) {
        super(s);
    }

    @Override
    public void render(int x, int y, GameCanvas frame) {
        frame.renderPixels(x, y, sprite.getPixels(), sprite.SIZE, Game.SCALE);
    }

    @Override
    public boolean isSolid() {
        return true;
    }
    
}
