package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Menu {
	
	private int currentChoice = 0;
	private String[] options = { "START", "POMOC", "WYJŒCIE"};
	private String title = "DM GAME";
	private Color titleColor;
	private Color optionColor;
	private Font titleFont;
	private Font font;
	private boolean inMenu;
	private boolean help;
	
	public Menu() {
		
		init();
	}
	
	public void init() {
		titleColor = Color.RED;
		optionColor = Color.WHITE;
		titleFont = new Font("Arial", Font.BOLD, 70);
		font = new Font("Arial", Font.BOLD, 40);
		inMenu = true;
		help = false;
	}
	
	public void draw(Graphics2D g) {
		
		g.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/menu.png"), 0, 0, null);
		if(!help) {
			g.setColor(titleColor);
			g.setFont(titleFont);
			g.drawString(title, 200, 150);
			g.setFont(font);
			
			for(int i = 0; i < options.length; i++) {
				if(i == currentChoice) {
					g.setColor(Color.GREEN);
				}
				else {
					g.setColor(optionColor);
				}
				g.drawString(options[i], 200, 360 + i * 45);
			}
		} else {
			g.setColor(Color.YELLOW);
			g.setFont(new Font("Arial", Font.BOLD, 40));
			g.drawString("POMOC", 200, 150);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Arial", Font.BOLD, 30));
			g.drawString("[STRZA£KI] - PORUSZANIE POSTACI¥", 200, 250);
			g.drawString("[SPACJA] - ATAK", 200, 285);
			g.drawString("[ENTER] - PRZEJŒCIE PRZEZ DRZWI", 200, 320);
			g.drawString("[Z] - U¯YCIE MIKSTURY LECZNICZEJ", 200, 355);
			g.drawString("[ESC] - WYJŒCIE Z POMOCY", 200, 390);
		}
	}
	
	private void select() {
		
		if(currentChoice == 0) {
			inMenu = false;
		}
		if(currentChoice == 1) {
			help = true;
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public boolean isInMenu() {
		return inMenu;
	}

	public void setInMenu(boolean inMenu) {
		this.inMenu = inMenu;
	}

	public void keyPressed(KeyEvent e) {
		
		int k = e.getKeyCode();
		if(k == KeyEvent.VK_ENTER) {
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1)
				currentChoice = options.length - 1;
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length)
				currentChoice = 0;
		}
		if(k == KeyEvent.VK_ESCAPE) {
			help = false;
		}
	}
	
}
