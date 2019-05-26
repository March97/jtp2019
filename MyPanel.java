package jtp2019;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
import java.util.List;

public class MyPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int HERO_X = 50;
	private final int HERO_Y = 50;
	private Timer timer;
	private Hero hero;
	private final int DELAY = 20; //opoznienie animacji
	
	
	public MyPanel() {
		
		initPanel();
	}
	
	private void initPanel() {
	
		addKeyListener(new TAdapter());
		//setBackground(Color.BLACK);
		setFocusable(true);
		hero = new Hero(HERO_X, HERO_Y);
		timer = new Timer(DELAY, this);
		timer.restart();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
	
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
		
		List<Missile> missiles = hero.getMissiles();
		
		for(Missile missile : missiles) {
			
			g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		updateMissiles();
		updateHero();
		repaint();
	}
	
	private void updateMissiles () {
		List<Missile> missiles = hero.getMissiles();
		
		for(int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			
			if (missile.isVisible()) {
				
				missile.move();
			} else {
				
				missiles.remove(i);
			}
		}
	}
	
	private void updateHero() {
		
		hero.move();
	}
	
	public class TAdapter extends KeyAdapter{

		@Override
		public void keyPressed(KeyEvent e) {
			hero.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			hero.keyReleased(e);
		}
	}
}