package jtp2019;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class LeftPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final int x = 1080;
	private final int y = 0;
	private final int BOARD_WIDTH = 200;
	private final int BOARD_HEIGHT = 720;
	private Image image;
	
	public LeftPanel() {
		
		initPanel();
	}
	
	public void initPanel() {
		
		setBackground(Color.ORANGE);
		setBounds(x, y, BOARD_WIDTH, BOARD_HEIGHT);
		loadImage("src/resources/wood/wood1.jpg");
	}
	
	protected void loadImage(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		image = ii.getImage();	
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, this);
	}
	
}
