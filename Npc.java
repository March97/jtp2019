package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Npc extends Sprite{
	
	private String dialog1 = "Daj mi spokój. Lepiej";
	private String dialog2 = "porozmawiaj z mnichem";
	private String dialog3 = "przy karczmie";
	private boolean interrupt = false;
	
	public Npc(int x, int y, String string) {
		super(x, y);
		
		initNpc(string);
	}
	
	public void initNpc(String string) {
		
		loadImage(string);
		getImageDimensions();
	}
	
	public void draw(Graphics2D g2d) {
		if(visible) {
			g2d.drawImage(getImage(), getX(), getY(), null);
			if(interrupt)
				drawDialog(g2d);
		}
	}
	
	private void drawDialog(Graphics2D g2d) {
		g2d.setColor(new Color(115, 75, 20));
		g2d.fillRoundRect(getX() - 47, getY() - 47, 154, 44, 10, 10);
		g2d.setColor(new Color(255, 250, 185));
		g2d.fillRoundRect(getX() - 45, getY() - 45, 150, 40, 10, 10);
		g2d.setColor(new Color(115, 75, 20));
		g2d.setFont(new Font("Helvetica", Font.BOLD, 12));
		g2d.drawString(getDialog1(), getX() - 40, getY() - 32);
		g2d.drawString(getDialog2(), getX() - 40, getY() - 22);
		g2d.drawString(getDialog3(), getX() - 40, getY() - 12);
	}

	public String getDialog1() {
		return dialog1;
	}

	public void setDialog1(String dialog) {
		this.dialog1 = dialog;
	}

	public String getDialog2() {
		return dialog2;
	}

	public void setDialog2(String dialog2) {
		this.dialog2 = dialog2;
	}

	public String getDialog3() {
		return dialog3;
	}

	public void setDialog3(String dialog3) {
		this.dialog3 = dialog3;
	}

	public boolean isInterrupt() {
		return interrupt;
	}

	public void setInterrupt(boolean interrupt) {
		this.interrupt = interrupt;
	}
}
