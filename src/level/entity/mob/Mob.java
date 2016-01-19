/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.entity.mob;

import game.GameCanvas;
import graphics.SpriteSet;
import java.util.ArrayList;
import level.Level;
import level.entity.Entity;
import level.entity.projectile.Projectile;

/**
 *
 * @author Terrell
 */
public abstract class Mob extends Entity {

    protected SpriteSet animation;
    protected final ArrayList<Projectile> projectiles;

    protected int anim;
    private int fire;

    public Mob(Level l, int x, int y, int xS, int yS) {
        super(l, x, y, xS, yS);
        projectiles = new ArrayList<>();
        move = 8;
    }

    protected void shoot(Projectile proj) {
        if (fire == 0) {
            projectiles.add(proj);
            fire = proj.getFireRate();
        }
    }

    public void render(GameCanvas screen) {
        for (Projectile p : projectiles) {
            p.render(screen);
        }
    }

    public void update() {
        if (fire > 0) {
            fire--;
        }
        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
            } else {
                projectiles.get(i).update();
            }
        }
        if (update % 10 == 0 && isMoving()) {
            anim = (anim + 1) % animation.getHeight();
        } else if (!isMoving()) {
            dir = 3;
            anim = 0;
        }
        super.update();
    }
}
