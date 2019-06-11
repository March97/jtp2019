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
		add(mainPanel);
	}
}