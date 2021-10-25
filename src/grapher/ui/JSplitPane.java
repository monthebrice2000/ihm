package grapher.ui;

import javax.swing.*;

public class JSplitPane extends JFrame {

    public JSplitPane(){
        setTitle("Esseai");
        setSize(400,400);
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setVisible( true );
        setLocationRelativeTo( null );
        javax.swing.JSplitPane splitPane = new javax.swing.JSplitPane( javax.swing.JSplitPane.HORIZONTAL_SPLIT );
        JButton jButton1 = new JButton( "Right" );
        JButton jButton2 = new JButton( "Left" );
        splitPane.setBottomComponent( jButton2 );
        splitPane.setTopComponent( jButton1 );
        splitPane.setRightComponent( new JButton ("111"));
        splitPane.setDividerSize( 10 );
        splitPane.setDividerLocation( 100 );
        splitPane.setOneTouchExpandable( true );

        add( splitPane );

        validate();
    }
}
