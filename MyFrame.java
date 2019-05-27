package jtp2019;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame{

	private static final long serialVersionUID = 1L;

	public MyFrame() {
		initUI();
	}
	
	private void initUI() {
		
		JPanel panel = new MyPanel();
		
		add(panel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setTitle("DM GAME");
		//setLocation(50, 50);
		setResizable(false);
		
		setVisible(true);
	}
}