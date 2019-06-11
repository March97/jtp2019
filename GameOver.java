package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class GameOver {
	
	private boolean isEnter = false;

	public void draw(Graphics g) {
		
		g.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/gameover.jpg"), 0, 0, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 28));
		g.drawString("Naciœnij ENTER klawisz, aby rozpocz¹æ od nowa.", 280, 450);
	}


	public boolean isEnter() {
		return isEnter;
	}

	public void setEnter(boolean isEnter) {
		this.isEnter = isEnter;
	}

	public void keyPressed(KeyEvent e) {
	
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_ENTER) {
			isEnter = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_ENTER) {
			isEnter = false;
		}
	}
}