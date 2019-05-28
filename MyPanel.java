package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

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
	private boolean inGame;
	private final int DELAY = 10; //opoznienie animacji
	
	private final int[][] pos = {
			{1200, 200}, {450, 600}, {800, 300}
	};
	
	
	public MyPanel() {
		
		initPanel();
	}
	
	private void initPanel() {
	
		addKeyListener(new TAdapter());
		//setBackground(Color.BLACK);
		setFocusable(true);
		hero = new Hero(HERO_X, HERO_Y, "src/resources/paladyn/paladyn/pal1-0-0.png",
										"src/resources/paladyn/paladyn/pal1-1-0.png",
										"src/resources/paladyn/paladyn/pal1-2-0.png",
										"src/resources/paladyn/paladyn/pal1-3-0.png",
										"src/resources/paladyn/paladyn/pal1-0-1.png",
										"src/resources/paladyn/paladyn/pal1-1-1.png",
										"src/resources/paladyn/paladyn/pal1-2-1.png",
										"src/resources/paladyn/paladyn/pal1-3-1.png",
										"src/resources/paladyn/paladyn/pal1-0-2.png",
										"src/resources/paladyn/paladyn/pal1-1-2.png",
										"src/resources/paladyn/paladyn/pal1-2-2.png",
										"src/resources/paladyn/paladyn/pal1-3-2.png",
										"src/resources/paladyn/paladyn/pal1-0-3.png",
										"src/resources/paladyn/paladyn/pal1-1-3.png",
										"src/resources/paladyn/paladyn/pal1-2-3.png",
										"src/resources/paladyn/paladyn/pal1-3-3.png");
		initMonsters();
		inGame = true;
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
		
		if(inGame) {
			doDrawing(g);
		}else {
			drawGameOver(g);
		}
		
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
	
	private void drawGameOver(Graphics g) {
		
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 28);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.black);
		g.setFont(small);
		g.drawString(msg, (BOARD_WIDTH - fm.stringWidth(msg)) / 2, BOARD_HEIGHT / 2);
	}
	
	private void inGame() {
		
		if(!inGame) {
			timer.stop();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		inGame();
		
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
		if(monsters.isEmpty()) {
			
			//inGame = false;
			return;
		}
		
		for(int i = 0; i < monsters.size(); i++) {
			
			Monster m = monsters.get(i);
			
			if(m.isVisible()) {
				m.move(hero.getX(), hero.getY(), BOARD_WIDTH, BOARD_HEIGHT);
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
                inGame = false;
            }
        }

        List<Missile> ms = hero.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Monster monster : monsters) {

                Rectangle r2 = monster.getBounds();

                if (r1.intersects(r2)) {
                    
                	monster.getDamage(60);
                	if(monster.getHealth() <= 0) {
                		monster.setVisible(false);
                	}
                    m.setVisible(false);
                    
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