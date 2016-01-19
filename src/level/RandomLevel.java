/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import java.util.Random;

/**
 *
 * @author Terrell
 */
public class RandomLevel extends Level {

    private final Random random;

    public RandomLevel(int w, int h) {
        super(w, h);
        random = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tiles[x + y * width] = random.nextInt(4);
            }
        }
    }

    @Override
    public void update() {
    }
}
