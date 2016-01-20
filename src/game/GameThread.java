/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author ford.terrell
 */
public class GameThread extends Thread {
    
    private final Game frame;
    private boolean running;
    
    public GameThread(Game g) {
        super("GameThread");
        frame = g;
    }
    
    public void run() {
        long startTime = System.nanoTime();
        int frames_per_second = 0, ticks = 0, ticks_per_second = 0, seconds_passed = 0;
        final double ns = 1_000_000_000.0 / 60.0;
        //conversion from nanoseconds to 1/60 of a second
        while (running) {
            if ((System.nanoTime() - startTime) / 1_000_000_000 > seconds_passed) {
                seconds_passed++;
                //if a second has passed
                frame.setTitle("Game | " + frames_per_second + " fps, " + ticks_per_second + " ups, "
                        + seconds_passed + (seconds_passed == 1 ? " second" : " seconds") + " running");
                frames_per_second = 0;
                ticks_per_second = 0;
            }
            //do game updates (60 per second)
            //if scheduled for one or multiple
            //updates, do game tick
            while ((System.nanoTime() - startTime) / ns > ticks) {
                frame.tick();
                //update the game
                ticks++;
                //one more tick recorded
                ticks_per_second++;
                //one more tick this second recorded
            }
            //if has updated game, render image
            frame.render();
            frames_per_second++;
        }
    }
    
    public void start() {
        running = true;
        super.start();
    }
    
    public void end() {
        try {
            running = false;
            join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
