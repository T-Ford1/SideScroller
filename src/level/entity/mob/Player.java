/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.entity.mob;

import game.Game;
import game.GameCanvas;
import graphics.Sprite;
import graphics.SpriteSet;
import input.Keyboard;
import input.Mouse;
import level.Level;
import level.entity.projectile.WizardProjectile;
import level.tile.Tile;

/**
 *
 * @author Terrell
 */
public class Player extends Mob {

    private final Keyboard input;
    private final Mouse mouse;

    public Player(Level l, Keyboard k, Mouse m, int xS, int yS) {
        super(l, 0, 0, xS, yS);
        input = k;
        mouse = m;
        animation = SpriteSet.PLAYER_SET;
    }

    public Player(Level l, Keyboard k, Mouse m, int spwnX, int spwnY, int xS, int yS) {
        super(l, spwnX, spwnY, xS, yS);
        input = k;
        mouse = m;
        animation = SpriteSet.PLAYER_SET;
    }

    protected void move() {
        int xMove = input.left ? (input.right ? 0 : -move) : (input.right ? move : 0);
        int yMove = input.up ? (input.down ? 0 : -move) : (input.down ? move : 0);
        super.move(xMove, yMove, isInside(level, Tile.LAVA_TILE) | isInside(level, Tile.WATER_TILE));
    }

    public void update() {
        input.update();
        move();
        if (mouse.getButton() == 1) {
            double theta = Math.atan2(mouse.getY() - getRenderY() - 30, mouse.getX() - getRenderX() - 30);
            int xSpawn = (int) (Math.cos(theta) * 50);
            int ySpawn = (int) (Math.sin(theta) * 50);
            WizardProjectile proj = new WizardProjectile(level, this, xSpawn, ySpawn, theta);
            super.shoot(proj);
        }
        mouse.update();
        super.update();
    }

    public void render(GameCanvas screen) {
        super.render(screen);
        Sprite render = animation.getSprite(dir, anim);
        screen.renderPixels(getRenderX(), getRenderY(), render.getPixels(), render.SIZE, Game.SCALE);
    }

    /**
     * returns an array with values: [0] = leftX [1] = rightX [2] = topY [3] =
     * bottomY
     *
     * @return
     */
    protected int[] getBounds() {
        int s = Game.SCALE;
        return new int[]{getX() + 9 * s, getX() + 24 * s - 1, getY() + 16 * s, getY() + 32 * s - 1};
    }

    /**
     * returns an array with values: [0] top left [1] top right [2] bottom left
     * [3] bottom right
     *
     * @param l the level the player is in
     * @return
     */
    public Tile[] getOccupiedTiles(Level l) {
        int[] bounds = getBounds();
        return new Tile[]{
            l.getTilePixels(bounds[0], bounds[2]),
            l.getTilePixels(bounds[1], bounds[2]),
            l.getTilePixels(bounds[0], bounds[3]),
            l.getTilePixels(bounds[1], bounds[3])};
    }

    protected boolean isInside(Level l, Tile t) {
        Tile[] occupied = getOccupiedTiles(l);
        for (Tile o : occupied) {
            if (o.equals(t)) {
                return true;
            }
        }
        return false;
    }

    public int getMouseX() {
        return mouse.getX();
    }

    public int getMouseY() {
        return mouse.getY();
    }

    public int getMouseButton() {
        return mouse.getButton();
    }

    protected void onCollision() {
        removed = true;
    }
}
