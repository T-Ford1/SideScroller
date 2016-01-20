/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.entity.projectile;

import graphics.SpriteSet;
import level.Level;
import level.entity.Entity;
import level.entity.mob.Mob;

/**
 *
 * @author Terrell
 */
public abstract class Projectile extends Entity {

    protected int damage, rateOfFire, anim,
            spawnX, spawnY, relativeX, relativeY;
    protected double theta, speed, range;

    protected final Mob source;
    protected SpriteSet animation;

    private final double xStep, yStep;

    public Projectile(Level l, Mob s, int spwnX, int spwnY, double t) {
        super(l, spwnX, spwnY, 0, 0);
        theta = t;
        speed = 10;
        source = s;
        range = 450;
        rateOfFire = 10;
        spawnX = s.getX();
        spawnY = s.getY();
        xStep = Math.cos(theta) * speed;
        yStep = Math.sin(theta) * speed;

    }

    private double distance(int x, int y) {
        return Math.sqrt(x * x + y * y);
    }

    public void update() {
        relativeX = source.getX() - spawnX;
        relativeY = source.getY() - spawnY;
        if (range < distance(getX(), getY())) {
            removed = true;
        }
        if (!removed) {
            move(xStep, yStep, false);
            super.update();
        }
    }

    public int getFireRate() {
        return rateOfFire;
    }
    
    public int getRelativeX() {
        return getX() - relativeX;
    }
    
    public int getRelativeY() {
        return getY() - relativeY;
    }
    
    public int getRenderX() {
        return source.getRenderX();
    }
    
    public int getRenderY() {
        return source.getRenderY();
    }
}
