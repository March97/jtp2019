package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Toolkit;

public class LeftPanel {
	
	public void draw(Graphics2D g2d, int health, int exp, int potions, int attack, int def, int x, int y) {
		
		g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/wood/wood2.jpg"), 1040, 0, null);
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("Helvetica", Font.BOLD, 18));
		g2d.drawString("¯ycie: " + health, 1080, 45);
		g2d.drawString("Doœwiadczenie: " + exp, 1080, 95);
		g2d.drawString("x " + potions, 1170, 180);
		g2d.drawString("x " + attack, 1170, 260);
		g2d.drawString("x " + def, 1170, 340);
		g2d.drawString("( " + x + ", " + y + " )", 1180, 680);
		g2d.setColor(Color.RED);
		g2d.fillRect(1080, 50, 160, 10);
		g2d.setColor(Color.GREEN);
		g2d.fillRect(1080, 50, (int) (health * 1.6), 10);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(1080, 100, 160, 10);
		g2d.setColor(Color.YELLOW);
		g2d.fillRect(1080, 100, (int) (exp / 100), 10);
		g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/potion/potion.png"), 1120, 150, null);
		g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/potion/sword.png"), 1120, 230, null);
		g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/potion/shield.png"), 1120, 310, null);
	}
}
