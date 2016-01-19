/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.entity;

import game.GameCanvas;
import java.util.Random;
import level.Level;

/**
 *
 * @author Terrell
 */
public abstract class Entity {
    
    protected final Level level;
    protected Random random;

    protected boolean moving, removed;
    
    protected int update, move, dir;
    
    private double x, y, renderX, renderY, xRemain, yRemain;

    /**
     * 
     * @param l
     * @param xSpwn the starting x coord on the map
     * @param ySpwn the starting y coord on the map
     * @param xS the x coord to render to the screen
     * @param yS the y coord to render to the screen
     */
    public Entity(Level l, int xSpwn, int ySpwn, int xS, int yS) {
        x = xSpwn;
        y = ySpwn;
        renderX = xS;
        renderY = yS;
        random = new Random();
        level = l;
    }

    public void update() {
        moving = false;
        update++;
    }

    public abstract void render(GameCanvas frame);
    
    protected abstract int[] getBounds();
    
    protected abstract void onCollision();

    protected void move(double x, double y, boolean slow) {
        if (x != 0) {
            xMove(level, (dir = x > 0 ? 1 : 0), slow ? x / 4 : x);
            moving = true;
        }
        if (y != 0) {
            yMove(level, (dir = y > 0 ? 3 : 2), slow ? y / 4 : y);
            moving = true;
        }
    }

    private void xMove(Level l, int dir, double x) {
        int step = x < 0 ? -1 : 1;
        int xDist = (int) (xRemain + x);
        xRemain += x - xDist;
        for (int i = 0; i != xDist;) {
            move(step, 0);
            if (collision(l, dir)) {
                move(-step, 0);
                onCollision();
                break;
            }
            i += dir == 1 ? 1 : -1;
        }
    }

    private void yMove(Level l, int dir, double y) {
        int step = y < 0 ? -1 : 1;
        int yDist = (int) (yRemain + y);
        yRemain += y - yDist;
        for (int i = 0; i != yDist;) {
            move(0, step);
            if (collision(l, dir)) {
                move(0, -step);
                onCollision();
                break;
            }
            i += dir == 3 ? 1 : -1;
        }
    }
    
    private void move(double xChange, double yChange) {
        x += xChange;
        y += yChange;
    }
    
    private boolean collision(Level l, int dir) {
        int[] bounds = getBounds();
        if (dir < 2) {
            return l.getTilePixels(bounds[dir], bounds[2]).isSolid()
                    || l.getTilePixels(bounds[dir], bounds[3]).isSolid();
        }
        return l.getTilePixels(bounds[0], bounds[dir]).isSolid()
                || l.getTilePixels(bounds[1], bounds[dir]).isSolid();
    }
    
    public boolean isMoving() {
        return moving;
    }
    
    public void remove() {
        removed = true;
    }
    
    public boolean isRemoved() {
        return removed;
    }
    
    public void moveRenderPosition(double xOff, double yOff) {
        renderX += xOff;
        renderY += yOff;
    }
    
    public void setRenderPosition(int xS, int yS) {
        renderX = xS;
        renderY = yS;
    }
    
    public void setPosition(double xPos, double yPos) {
        x = xPos;
        y = yPos;
    }
    
    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getRenderX() {
        return (int) renderX;
    }

    public int getRenderY() {
        return (int) renderY;
    }
}
