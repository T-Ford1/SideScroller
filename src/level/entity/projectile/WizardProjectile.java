/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level.entity.projectile;

import game.Game;
import game.GameCanvas;
import graphics.SpriteSet;
import level.Level;
import level.entity.mob.Mob;

/**
 *
 * @author Terrell
 */
public class WizardProjectile extends Projectile {

    private final int img, size;

    public WizardProjectile(Level l, Mob s, int spwnX, int spwnY, double theta) {
        super(l, s, spwnX, spwnY, theta);
        rateOfFire = 20;
        animation = SpriteSet.WIZARD_SET;
        img = random.nextInt(animation.getWidth());
        size = img == 2 ? 1 : 2;
    }

    public void update() {
        super.update();
        if (update % 5 == 0) {
            anim = (anim + 1) % animation.getHeight();
        }
    }

    public void render(GameCanvas c) {
        if (!removed) {
            int rndX = getRelativeX() + getRenderX() + Game.SCALE * 11;
            int rndY = getRelativeY() + getRenderY() + Game.SCALE * 10;
            c.renderPixels(rndX, rndY, animation.getSprite(img, anim).getPixels(), animation.SIZE, animation.SIZE, size);
        }
    }

    protected int[] getBounds() {
        int x = source.getX() + getRelativeX();
        int y = source.getY() + getRelativeY();
        if (img == 2) {
            return new int[]{x + 18, x + 76, y + 28, y + 70};
        }
        return new int[]{x + 22, x + 43, y + 20, y + 41};
    }

    protected void onCollision() {
        removed = true;
    }
}
