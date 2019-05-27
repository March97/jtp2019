package jtp2019;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Hero extends Sprite{

	private int dx;
	private int dy;
	private List<Missile> missiles;

	public Hero(int x, int y) {
		super(x, y);
		
		initHero();
	}
	
	private void initHero() {
		
		missiles = new ArrayList<>();
		loadImage("src/resources/paladyn/m_pal281.png");
		getImageDimensions();
	}
	
	public void move(int b_width, int b_height) {
		x += dx;
		y += dy;
		
		if(x < 1) {
			x = 1;
		}
		if(x > b_width - width) {
			x = b_width - width;
		}
		if(y < 1) {
			y = 1;
		}
		if(y > b_height - height) {
			y = b_height - height;
		}
		
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public List<Missile> getMissiles() {
		return missiles;
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SPACE) {
			fire();
		}
		if(key == KeyEvent.VK_LEFT) {
			dx = -1;
		}
		if(key == KeyEvent.VK_RIGHT) {
			dx = 1;
		}
		if(key == KeyEvent.VK_UP) {
			dy = -1;
		}
		if(key == KeyEvent.VK_DOWN) {
			dy = 1;
		}
	}
	
	public void fire() {
		missiles.add(new Missile(x, y, dx, dy));
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if(key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		if(key == KeyEvent.VK_UP) {
			dy = 0;
		}
		if(key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
	
	
}