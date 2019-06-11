package jtp2019;

import javax.swing.JFrame;

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