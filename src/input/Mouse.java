/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Terrell
 */
public class Mouse implements MouseMotionListener, MouseListener {
    
    private int x, y, b;
    private boolean clicked;
    
    public Mouse() {
        x = -1;
        y = -1;
        b = -1;
    }

    public void mouseDragged(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    public void mouseMoved(MouseEvent me) {
        x = me.getX();
        y = me.getY();
    }

    public void mouseClicked(MouseEvent me) {
        b = me.getButton();
        clicked = true;
    }

    public void mousePressed(MouseEvent me) {
        b = me.getButton();
    }

    public void mouseReleased(MouseEvent me) {
        b = -1;
    }

    public void mouseEntered(MouseEvent me) {

    }

    public void mouseExited(MouseEvent me) {

    }
    
    public void update() {
        if(clicked) {
            b = -1;
        }
        clicked = false;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getButton() {
        return b;
    }
}
