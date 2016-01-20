/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import level.Level;
import level.NexusLevel;
import level.entity.mob.Player;

/**
 *
 * @author ford.terrell
 */
public class Game extends JFrame {

    private static final long serialVersionUID = 1L;

    public static final int SIZE = 32;
    public static final int SCALE = 2;

    public static final int SIZE_BIT_MASK = 31;
    public static final int SCALE_BIT_MASK = 1;
    
    public static final int SIZE_BIT_SHIFT = 5;
    public static final int SCALE_BIT_SHIFT = 1;

    private final Player player;
    private final GameCanvas canvas;
    private final BufferStrategy bs;
    private final GameThread thread;
    
    private static final Level level = new NexusLevel("./res/levels/nexus_level_2.png");

    public Game() {
        super("Game");
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        add(canvas = new GameCanvas());
        pack();
        canvas.initRender();
        player = new Player(level, canvas.addKeys(), canvas.addMouse(), 50 * SIZE * SCALE, 50 * SIZE * SCALE, 
                canvas.getRenderWidth() / 2 - SIZE, canvas.getRenderHeight() / 2 - SIZE);
        canvas.createBufferStrategy(3);
        bs = canvas.getBufferStrategy();
        thread = new GameThread(this);
        setVisible(true);
    }

    public void tick() {
        level.update();
        player.update();
    }

    public void render() {
        //canvas.clear();
        level.render(player.getX() - player.getRenderX(), player.getY() - player.getRenderY(), canvas);
        player.render(canvas);
        Graphics g = bs.getDrawGraphics();
        g.drawImage(canvas.getImage(), 0, 0, null);
        g.setFont(new Font(Font.SERIF, 0, 25));
        g.setColor(Color.black);
        g.drawString("X: " + (player.getX()) + ", Y: " + (player.getY()), 5, 30);
        g.dispose();
        bs.show();
    }

    public synchronized void start() {
        thread.start();
    }

    public synchronized void stop() {
        thread.end();
    }
}
