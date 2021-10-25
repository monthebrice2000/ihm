package grapher.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Interaction implements MouseListener, MouseMotionListener {


    Grapher grapher;
    Point point;
    public Interaction(Grapher grapher) {
        
        this.grapher = grapher;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        System.out.println( e.getPoint() );
        if( e.getButton() == 1 ){
            this.grapher.zoom( e.getPoint(), 5);
            System.out.println( "Grapher was zoomed" );
        }else{
            this.grapher.zoom( e.getPoint(), -5);
            System.out.println( "Grapher was dezoomed"  );
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println( "Mouse Clicked");
        System.out.println( e.getPoint() );
        System.out.println( new Point( e.getPoint().x, e.getPoint().y ) );
        this.point = new Point( e.getPoint().x, e.getPoint().y );

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println( "Mouse Released");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse Entered");

    }

    @Override
    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");

    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if( e.getButton() == 1 ){
            System.out.println("Mouse Dragged click to move");
            Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
            this.grapher.setCursor( cursor );
            this.grapher.setVisible(true);
            System.out.println( this.point );
            this.grapher.translate( e.getX() - this.point.x, e.getY() - this.point.x );
            System.out.println( "Grapher was moved right");
            this.grapher.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        }else {
            System.out.println("Mouse Dragged click to zoom");
            System.out.println( this.point );
            this.grapher.zoom( new Point( e.getPoint().x, e.getPoint().y), this.point );
        }


    }

    @Override
    public void mouseMoved(MouseEvent e) {
        //System.out.println( "Mouse Moved");

    }
}
