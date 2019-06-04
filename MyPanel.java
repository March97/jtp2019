package jtp2019;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int x = 0;
	private final int y = 0;
	private final int HERO_X = 450;
	private final int HERO_Y = 250;
	private final int NPC_X = 770;
	private final int NPC_Y = 170;
	private final int BOARD_WIDTH = 1040;
	private final int BOARD_HEIGHT = 720;
	private CityMap cityMap;
	private Timer timer;
	private Hero hero;
	private Npc npc;
	private Image background;
	private Image leftPanel;
	private List<Monster> monsters;
	private boolean inGame;
	private final int DELAY = 15; //opoznienie animacji

	
	private final int[][] pos = {
			{1200, 200}, {450, 600}, {800, 300}
	};
	
	
	public MyPanel() {
		
		initPanel();
	}
	
	private void initPanel() {
	
		addKeyListener(new TAdapter());
		setBounds(x, y, BOARD_WIDTH, BOARD_HEIGHT);
		setFocusable(true);
		cityMap = new CityMap();
		loadBackground("src/resources/city/ithan1.png");
		loadLeftPanel("src/resources/wood/wood2.jpg");
		hero = new Hero(HERO_X, HERO_Y, "src/resources/paladyn/pal1-0-0.png",
										"src/resources/paladyn/pal1-1-0.png",
										"src/resources/paladyn/pal1-2-0.png",
										"src/resources/paladyn/pal1-3-0.png",
										"src/resources/paladyn/pal1-0-1.png",
										"src/resources/paladyn/pal1-1-1.png",
										"src/resources/paladyn/pal1-2-1.png",
										"src/resources/paladyn/pal1-3-1.png",
										"src/resources/paladyn/pal1-0-2.png",
										"src/resources/paladyn/pal1-1-2.png",
										"src/resources/paladyn/pal1-2-2.png",
										"src/resources/paladyn/pal1-3-2.png",
										"src/resources/paladyn/pal1-0-3.png",
										"src/resources/paladyn/pal1-1-3.png",
										"src/resources/paladyn/pal1-2-3.png",
										"src/resources/paladyn/pal1-3-3.png");
		initMonsters();
		npc = new Npc(NPC_X, NPC_Y);
		inGame = true;
		timer = new Timer(DELAY, this);
		timer.restart();
	}
	
	public void initMonsters() {
		
		monsters = new ArrayList<>();
		
		for(int[] p : pos) {
			monsters.add(new Monster(p[0], p[1], 	"src/resources/monster/monster-0-0.png",
													"src/resources/monster/monster-1-0.png",
													"src/resources/monster/monster-2-0.png",
													"src/resources/monster/monster-3-0.png",
													"src/resources/monster/monster-0-1.png",
													"src/resources/monster/monster-1-1.png",
													"src/resources/monster/monster-2-1.png",
													"src/resources/monster/monster-3-1.png",
													"src/resources/monster/monster-0-2.png",
													"src/resources/monster/monster-1-2.png",
													"src/resources/monster/monster-2-2.png",
													"src/resources/monster/monster-3-2.png",
													"src/resources/monster/monster-0-3.png",
													"src/resources/monster/monster-1-3.png",
													"src/resources/monster/monster-2-3.png",
													"src/resources/monster/monster-3-3.png"));
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
		g2d.drawImage(background, 0, 0, this);
		
		if(hero.isVisible()) {
			g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
		}
		
		if(npc.isVisible()) { 
			g2d.drawImage(npc.getImage(), npc.getX(), npc.getY(), this);
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
		
		//rysowanie lewego panelu
		g2d.drawImage(leftPanel, 1040, 0, this);
		g2d.setColor(Color.WHITE);
		Font small = new Font("Helvetica", Font.BOLD, 18);
		g2d.setFont(small);
		g2d.drawString("Monsters: " + monsters.size(), 1080, 50);
		g2d.drawString("Potions: " + hero.getPotions(), 1080, 100);
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
		if(hero.getHealth() <= 0)
			inGame = false;
	}
	
	private void updateMonsters() {
		
		if(monsters.isEmpty()) {
			
			//inGame = false;
			return;
		}
		
		for(int i = 0; i < monsters.size(); i++) {
			
			Monster m = monsters.get(i);
			
			if(m.isVisible()) {
				m.animation(hero.getX(), hero.getY());
				m.move(hero.getX(), hero.getY(), BOARD_WIDTH, BOARD_HEIGHT);
			} else {
				monsters.remove(i);
			}
		}
		
		//leftPanel.setMonsters(monsters.size());
	}
	
	public void checkCollisions() {

        Rectangle r3 = hero.getBounds();
        Rectangle r4 = npc.getBounds();

        if(cityMap.tableActions(r3) == 1)
        	hero.stop();
        
        if (r3.intersects(r4)) {
            
        	hero.stop();
        	hero.addPotions();
        	//JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.");
        }
        
        for (Monster monster : monsters) {
            
            Rectangle r2 = monster.getBounds();

            if (r3.intersects(r2)) {
                
            	hero.getDamage(monster.getAttack());
                monster.setVisible(false);
            }
        }

        List<Missile> ms = hero.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Monster monster : monsters) {

                Rectangle r2 = monster.getBounds();

                //kolizja z potworem
                if (r1.intersects(r2)) {
                    
                	monster.getDamage(hero.getAttack());
                	if(monster.getHealth() <= 0) {
                		monster.setVisible(false);
                	}
                    m.setVisible(false);
                }
            }
            //kolizja z npc
            if (r1.intersects(r4)) {

                m.setVisible(false); 
            }
        }  
    }
	
	public int getMonsters() {
		
		return monsters.size();
	}
	
	protected void loadBackground(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		background = ii.getImage();	
	}
	
	protected void loadLeftPanel(String imageName) {
		
		ImageIcon ii = new ImageIcon(imageName);
		leftPanel = ii.getImage();	
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