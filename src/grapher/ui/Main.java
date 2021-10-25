/* grapher.ui.Main
 * (c) blanch@imag.fr 2021–                                                */

package grapher.ui;

import grapher.fc.Function;
import grapher.fc.FunctionFactory;

import javax.swing.*;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Arrays;


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

		Interaction i = new Interaction( grapher );
		grapher.addMouseListener( i );
		grapher.addMouseMotionListener( i );

		add(grapher);

		javax.swing.JSplitPane splitPane = new javax.swing.JSplitPane( javax.swing.JSplitPane.HORIZONTAL_SPLIT );
		DefaultListModel<String> model = new DefaultListModel<>();
		JList<String> myList = new JList<String>( model );
		model.addAll(Arrays.asList(ex));
		splitPane.setRightComponent( grapher );


		JButton button1 = new JButton("+");
		JButton button2 = new JButton("-");
		JPanel panel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		splitPane.setLeftComponent( panel );
		panel.setLayout( new GridLayout( 2,1));
		panel.add( myList);
		panel.add( buttonsPanel );
		buttonsPanel.setLayout( new GridLayout(1,2));
		buttonsPanel.add( button1 );
		buttonsPanel.add( button2 );

		splitPane.setDividerSize( 10 );
		splitPane.setDividerLocation( 100 );
		splitPane.setOneTouchExpandable( true );
		splitPane.addMouseListener( i );
		add( splitPane );

		myList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					if( myList.getSelectionModel().getMinSelectionIndex( ) >= 0 ){
						System.out.println( "+++++"+model.get( myList.getSelectionModel().getMinSelectionIndex() ) );
						String selectedItem = myList.getSelectedValue().toString();
						Function f = FunctionFactory.createFunction( selectedItem );
						grapher.setSelectedFunctions( f );
					}

				}
			}
		});

		//ToolbarClass toolbar = new ToolbarClass();
		//toolbar.createAndShowGUI();

		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				if( action.equals( "+" ) ){
					System.out.println( "J'ajoute une fonction");
				}
			}
		});

		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				ListSelectionModel selmodel = myList.getSelectionModel();
				int index = selmodel.getMinSelectionIndex();
				if (index >= 0)
					System.out.println( index );
					System.out.println( model );
					model.removeElementAt( index );
					grapher.removeFunctionByClicked();
					myList.setSelectedIndex( model.getSize() - 1 );

			}
		});


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
