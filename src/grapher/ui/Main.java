/* grapher.ui.Main
 * (c) blanch@imag.fr 2021–                                                */

package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;

import javax.swing.*;
import javax.swing.JSplitPane;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


// main that launch a grapher.ui.Grapher

public class Main extends JFrame {
	Main(String title, String[] expressions) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Grapher grapher = new Grapher();
		String[] ex = {"sin(x)", "cos(x)", "tan(x)"};
		for(String expression: ex) {
			grapher.add(expression);
		}

		//grapher.add(ex[1]);


		Interaction i = new Interaction( grapher );
		grapher.addMouseListener( i );
		grapher.addMouseMotionListener( i );

		add(grapher);

		javax.swing.JSplitPane splitPane = new javax.swing.JSplitPane( javax.swing.JSplitPane.HORIZONTAL_SPLIT );
		javax.swing.JSplitPane splitPane2 = new javax.swing.JSplitPane( JSplitPane.VERTICAL_SPLIT );
		JList<String> myList = new JList<String>( ex );
		splitPane.setRightComponent( grapher );
		JButton button1 = new JButton("Button1");
		JButton button2 = new JButton("Button2");
		splitPane.setLeftComponent( splitPane2 );
		splitPane2.setTopComponent( myList );
		splitPane2.setBottomComponent( new JList<JButton>(new JButton[]{button1, button2}) );
		splitPane.setDividerSize( 10 );
		splitPane.setDividerLocation( 100 );
		//splitPane2.setDividerSize( 10 );
		//splitPane2.setDividerLocation( 100 );
		splitPane.setOneTouchExpandable( true );
		splitPane.addMouseListener( i );
		//splitPane.add( splitPane2 );
		add( splitPane );

		myList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					System.out.println( myList.getSelectedValue().toString() );
					String selectedItem = myList.getSelectedValue().toString();
					Function f = FunctionFactory.createFunction( selectedItem );
					grapher.setSelectedFunctions( f );
				}
			}
		});

		//ToolbarClass toolbar = new ToolbarClass();
		//toolbar.createAndShowGUI();


		pack();
	}



	public static void main(String[] argv) {
		final String[] expressions = argv;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Main("grapher", expressions).setVisible(true);
			}
		});
	}
}
