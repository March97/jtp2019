package jtp2019;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class MyPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int HERO_X = 50;
	private final int HERO_Y = 50;
	private final int BOARD_WIDTH = 1280;
	private final int BOARD_HEIGHT = 720;
	private Timer timer;
	private Hero hero;
	private List<Monster> monsters;
	private final int DELAY = 10; //opoznienie animacji
	
	private final int[][] pos = {
			{200, 200}, {150, 300}, {300, 100}
	};
	
	
	public MyPanel() {
		
		initPanel();
	}
	
	private void initPanel() {
	
		addKeyListener(new TAdapter());
		//setBackground(Color.BLACK);
		setFocusable(true);
		hero = new Hero(HERO_X, HERO_Y);
		initMonsters();
		timer = new Timer(DELAY, this);
		timer.restart();
	}
	
	public void initMonsters() {
		
		monsters = new ArrayList<>();
		
		for(int[] p : pos) {
			monsters.add(new Monster(p[0], p[1]));
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		doDrawing(g);
		Toolkit.getDefaultToolkit().sync();
	}
	
	private void doDrawing(Graphics g) {
	
		Graphics2D g2d = (Graphics2D) g;
		
		if(hero.isVisible()) {
			g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
		}
		
		List<Missile> missiles = hero.getMissiles();
		
		for(Missile missile : missiles) {
			if(missile.isVisible()) {
				g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
			}
		}
		
		for(Monster monster : monsters) {
			if(monster.isVisible() ) {
				g2d.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
			}
		}
		g2d.setColor(Color.BLACK);
		g2d.drawString("Monsters left: " + monsters.size(), 5, 15);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		updateMissiles();
		updateHero();
		updateMonsters();
		
		checkCollisions();
		repaint();
	}
	
	private void updateMissiles () {
		List<Missile> missiles = hero.getMissiles();
		
		for(int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			
			if (missile.isVisible()) {
				
				missile.move(BOARD_WIDTH, BOARD_HEIGHT);
			} else {
				
				missiles.remove(i);
			}
		}
	}
	
	private void updateHero() {
		
		hero.move(BOARD_WIDTH, BOARD_HEIGHT);
	}
	
	private void updateMonsters() {
		for(int i = 0; i < monsters.size(); i++) {
			
			Monster m = monsters.get(i);
			
			if(m.isVisible()) {
				m.move();
			} else {
				monsters.remove(i);
			}
		}
	}
	
	public void checkCollisions() {

        Rectangle r3 = hero.getBounds();

        for (Monster monster : monsters) {
            
            Rectangle r2 = monster.getBounds();

            if (r3.intersects(r2)) {
                
                hero.setVisible(false);
                monster.setVisible(false);
                //ingame = false;
            }
        }

        List<Missile> ms = hero.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Monster monster : monsters) {

                Rectangle r2 = monster.getBounds();

                if (r1.intersects(r2)) {
                    
                    m.setVisible(false);
                    monster.setVisible(false);
                }
            }
        }
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