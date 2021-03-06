import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;
import mdlaf.utils.MaterialImageFactory;
import org.jdesktop.swingx.JXTaskPane;
import top.gigabox.supportcomponent.toast.MaterialTost;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MaterialUISwingDemo {

	private static long beforeUsedMem;

	public MaterialUISwingDemo() {
		beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
	}

	public static void main (String[] args) {
		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
		}
		catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace ();
		}

		// basic instantiation of JFrame with various components, including a
		// JMenuBar with some menus and items, as well as a button
		JFrame frame = new JFrame ("Material Design UI for Swing by atharva washimkar");
		frame.setMinimumSize (new Dimension (600, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// configuring the JMenuBar as well as its menus and items
		JMenuBar bar = new JMenuBar ();
		JMenu menu1 = new JMenu ("Option 1 (Animated)");
		JMenu menu2 = new JMenu ("Option 2 (Not animated)");
		class ActionTestJFC extends AbstractAction{

			JComponent component;

			public ActionTestJFC(JComponent component){
				this.component = component;
				putValue(Action.NAME, "Test JFileChooser (Animated)");
				putValue(Action.SHORT_DESCRIPTION, "Test JFileChooser");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.showDialog(component, "Test OK");
			}
		}

		JMenuItem item1 = new JMenuItem ("Item 1 (Animated)");
		item1.setAction(new ActionTestJFC(new JPanel()));
		JMenuItem item2 = new JMenuItem ("Item 2 (Not animated)");

		//Test RadioButtonMenuItem
		JRadioButtonMenuItem jRadioButtonMenuItem = new JRadioButtonMenuItem ();
		jRadioButtonMenuItem.setText ("test RadioButtonMenuItem");
		menu1.add (jRadioButtonMenuItem);
		menu1.addSeparator ();
		//TestCheckBoxMenuItem
		JCheckBoxMenuItem checkBoxMenuItem = new JCheckBoxMenuItem ();
		checkBoxMenuItem.setText ("test");
		menu1.add (checkBoxMenuItem);
		menu1.addSeparator ();
		menu1.add (item1);
		menu2.add (item2);

		bar.add (menu1);
		bar.add (menu2);

		// configuring a simple JButton
		JButton button = new JButton ("PRESS ME");
		button.setBackground (MaterialColors.LIGHT_BLUE_400);
		button.setForeground (Color.WHITE);
		button.setMaximumSize (new Dimension (200, 200));

		JPanel content = new JPanel ();
		content.add (button);

		//Test a MaterialTitleBorder
		TitledBorder materialTitleBorder = new TitledBorder("Test Border");
		content.setBorder(materialTitleBorder);

		// add everything to the frame
		frame.add (bar, BorderLayout.PAGE_START);
		// frame.add (content, BorderLayout.CENTER);

		// start animating!
		// here, 'gray' is the color that the JComponent will transition to when the user hovers over it
		MaterialUIMovement.add (menu1, MaterialColors.GRAY_200);
		MaterialUIMovement.add (item1, MaterialColors.GRAY_200);

		// you can also pass in extra parameters indicating how many intermediate colors to display, as well as the "frame rate" of the animation
		// there will be 5 intermediate colors displayed in the transition from the original components color to the new one specified
		// the "frame rate" of the transition will be 1000 / 30, or 30 FPS
		// the animation will take 5 * 1000 / 30 = 166.666... milliseconds to complete
		MaterialUIMovement.add (button, MaterialColors.LIGHT_BLUE_500, 5, 1000 / 30);

		//
		content.add (new JCheckBox ("checkbox"));
		content.add (new JComboBox<String> (new String[]{"a", "b", "c"}));
		content.add (new JLabel ("label"));
		content.add (new JPasswordField ("password"));
		content.add (new JRadioButton ("radio button"));
		content.add (new JSlider (JSlider.HORIZONTAL, 0, 5, 2));
		content.add (new JSpinner (new SpinnerListModel (new String[]{"d", "e", "f"})));
		content.add (new JTable (new String[][]{{"a", "b", "c"}, {"d", "e", "f"}}, new String[]{"r", "e"}));
		content.add (new JTextField ("text field"));
		content.add (new JToggleButton ("toggle"));

		JToolBar tb = new JToolBar ("toolbar");
		JButton button1 = new JButton ("f");
		class ActionTest extends AbstractAction{

			public ActionTest(){
				putValue(Action.NAME, "f");
				putValue(Action.SHORT_DESCRIPTION, "Test tool tip");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				JPanel jPanel = new JPanel();
				jPanel.add(new JColorChooser());
				dialog.setContentPane(jPanel);
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
				dialog.pack();
			}
		}
		button1.setAction(new ActionTest());
		JButton button2 = new JButton ("e");
		button1.setBackground (MaterialColors.LIGHT_BLUE_400);
		button1.setForeground (Color.WHITE);
		button2.setBackground (MaterialColors.LIGHT_BLUE_400);
		button2.setForeground (Color.WHITE);

        button1.addMouseListener(MaterialUIMovement.getMovement(button1, MaterialColors.LIGHT_BLUE_300));
        button2.addMouseListener(MaterialUIMovement.getMovement(button2, MaterialColors.LIGHT_BLUE_300));

		tb.add (button1);
		tb.addSeparator ();
		tb.add (button2);
		tb.setFloatable (true);
		content.add (tb);

		JTree tree = new JTree (new String[]{"a", "b"});
		tree.setEditable (true);

		content.add (tree);

		JScrollPane sp = new JScrollPane (content);
		sp.setHorizontalScrollBarPolicy (JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		sp.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel pn = new JPanel ();
		JTabbedPane tp = new JTabbedPane ();
		tp.addTab ("bleh1", pn);
		tp.addTab ("bleh", sp);

		frame.add (tp, BorderLayout.CENTER);

		//test progressBar
		JProgressBar progressBar = new JProgressBar ();
		progressBar.setValue (6);
		progressBar.setMaximum (12);
		pn.add (progressBar);

		//test cange coloro maximum value progress bar
		progressBar = new JProgressBar ();
		progressBar.setMaximum (5);
		progressBar.setValue (5);
		pn.add (progressBar);

		JTextPane textPane = new JTextPane ();
		textPane.setText ("Hi I'm super sayan");
		JTextPane textPane1 = new JTextPane ();
		textPane1.setText ("Hi I'm super sayan");
		textPane1.setEnabled (false);
		pn.add (textPane);
		pn.add (textPane1);

		JEditorPane editorPane = new JEditorPane ();
		editorPane.setText ("I added a second character for Arabic support, it is activated according to the locale");
		pn.add (editorPane);

		JButton buttonTwoo = new JButton();
		class ActionToastTest extends AbstractAction{

		    JComponent component;

            public ActionToastTest(JComponent component){
                this.component = component;
                putValue(Action.NAME, "Test Toast");
                putValue(Action.SHORT_DESCRIPTION, "Test Toast");
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                MaterialTost.makeText(frame, "This is a message in a toast component", MaterialTost.SHORT, MaterialTost.NORMAL, MaterialTost.BOTTOM).display();
            }
        }
        buttonTwoo.setAction(new ActionToastTest(pn));
		buttonTwoo.setBackground(MaterialColors.PURPLE_600);
		buttonTwoo.setForeground(MaterialColors.GRAY_100);
		buttonTwoo.addMouseListener(MaterialUIMovement.getMovement(buttonTwoo, MaterialColors.PURPLE_300));
		pn.add(buttonTwoo);

		JButton bottoneConImmagine = new JButton();
		bottoneConImmagine.setIcon(new ImageIcon(MaterialImageFactory.getIstance().getImage(MaterialImageFactory.COMPUTER)));
		pn.add(bottoneConImmagine);

		JButton buttonTestTextFieled = new JButton("Test JtexFiele");


		class AzioneTestJTexField extends AbstractAction{

			public AzioneTestJTexField() {
				putValue(Action.NAME, "testJtextFieled");
			}

			@Override
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new JDialog();
				dialog.add(new JComboBox<String>());
				dialog.add(new JSpinner());
				dialog.setSize(new Dimension(50, 50));
				dialog.setLocationRelativeTo(frame);
				dialog.setVisible(true);
			}
		}

		buttonTestTextFieled.setAction(new AzioneTestJTexField());

		pn.add(buttonTestTextFieled);

		JXTaskPane jxTaskPane = new JXTaskPane();
		jxTaskPane.setTitle("Material UI memory");


		JLabel memoryOccupedNow = new JLabel();

		jxTaskPane.add(memoryOccupedNow);

		//Test effect mouse over
		//Setting default
		JButton testButtonHoverOne = new JButton("Fly over me One");
		testButtonHoverOne.setEnabled(false);
		pn.add(testButtonHoverOne);

		//ModSetting
		JButton testButtonHoverTwo = new JButton("Fly over me Two");
		testButtonHoverTwo.setBackground(MaterialColors.LIGHT_BLUE_500);
		testButtonHoverTwo.setForeground(MaterialColors.WHITE);
		testButtonHoverTwo.addMouseListener(MaterialUIMovement.getMovement(testButtonHoverTwo, MaterialColors.LIGHT_BLUE_200));
		pn.add(testButtonHoverTwo);

		pn.add(jxTaskPane);
		// make everything visible to the world
		frame.pack ();
		frame.setVisible (true);
		frame.setLocationRelativeTo(null);

		long lastUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
		double megamemori = (lastUsedMem - beforeUsedMem) * 9.537 * Math.pow(10, -7);
		memoryOccupedNow.setText("Memory occuped after update: " + megamemori + " MB");
	}
}
