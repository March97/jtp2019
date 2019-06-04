package jtp2019;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public MyFrame() {
		initUI();
	}
	
	private void initUI() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setTitle("DM GAME");
		setResizable(false);
		setVisible(true);
		
		MyPanel mainPanel = new MyPanel();
		//LeftPanel leftPanel = new LeftPanel();
		
		add(mainPanel);
		//add(leftPanel);
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setSize(200, 720);
		//setLocation(1080, 0);
		//setResizable(false);
		//setVisible(true);
		//JDialog d = new JDialog(this, "JDialog ...", true );
       // d.setDefaultCloseOperation(EXIT_ON_CLOSE);
       // d.setSize(300, 300);
       // d.show();
        //JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.");

		
		
	}
}