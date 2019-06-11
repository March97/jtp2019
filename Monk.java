package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Monk extends Sprite {

	private boolean interrupt = false;
	
	public Monk(int x, int y, String string) {
		super(x, y);
		
		initNpc(string);
	}
	
	public void initNpc(String string) {
		
		loadImage(string);
		getImageDimensions();
	}
	
	public void draw(Graphics2D g2d) {
		if(visible) {
			g2d.setColor(Color.YELLOW);
			Font big = new Font("Helvetica", Font.BOLD, 32);
			g2d.setFont(big);
			g2d.drawString("!", getX() + 11, getY() - 5);
			g2d.drawImage(getImage(), getX(), getY(), null);
			if(interrupt)
				drawDialog(g2d);
		}
	}
	
	private void drawDialog(Graphics2D g2d) {
		g2d.setColor(new Color(115, 75, 20));
		g2d.fillRoundRect(getX() - 107, getY() - 95, 324, 92, 10, 10);
		g2d.setColor(new Color(255, 250, 185));
		g2d.fillRoundRect(getX() - 105, getY() - 93, 320, 88, 10, 10);
		g2d.setColor(new Color(115, 75, 20));
		g2d.setFont(new Font("Helvetica", Font.BOLD, 12));
		g2d.drawString("Witaj W�drowcze!", getX() - 100, getY() - 82);
		g2d.drawString("W podziemiach domu przy po�udniowym murze", getX() - 100, getY() - 72);
		g2d.drawString("zal�g�y si� potowry, kt�re atakuj� nasze miaso w nocy.", getX() - 100, getY() - 62);
		g2d.drawString("Pozb�d� si� ich, a ludzie b�d� Ci wdzi�czni.", getX() - 100, getY() - 52);
		g2d.setColor(Color.RED);
		g2d.drawString("U mnie mo�esz ulepszy� sw�j sprz�t i kupi� mikstury.", getX() - 100, getY() - 42);
		g2d.drawString("[E] - kup mikstury, koszt 1000 do�w.", getX() - 100, getY() - 32);
		g2d.drawString("[A] - wzmocnij atak, koszt 1500 do�w.", getX() - 100, getY() - 22);
		g2d.drawString("[D] - wzmocnij obron�, koszt 2000 do�w.", getX() - 100, getY() - 12);
	}
	
	public boolean isInterrupt() {
		return interrupt;
	}

	public void setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}
}
