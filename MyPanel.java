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
	private final int BOARD_WIDTH = 1040;
	private final int BOARD_HEIGHT = 720;
	private final int HERO_X = 640;
	private final int HERO_Y = 640;
	private final int NPC_X = 770;
	private final int NPC_Y = 170;
	//private boolean inMenu;
	private Menu menu;
	private List<Npc> npcs;
	private List<Monster> monsters;
	private boolean inGame;
	private Hero hero;
	private Npc monk;
	private Witch witch;
	private int mapSelector;
	private final int[][] monsterPos = {
		{414, 586}, {550, 548}, {598, 442},
		{844, 608}, {744, 336}, {624, 310},
		{480, 124}, {332, 214}, {350, 350}
	};
	private final int[][] npcPos = {
			{598, 208}, {288, 152}, {384, 152},
			{544, 640}, {750, 640}, {750, 380},
			{880, 106}, {356, 315}, {566, 466},
			{566, 552}, {598, 506}, {846, 384},
			{804, 160}, {88, 135},  {314, 315},	
	};
	private Maps maps;
	private Image background;
	private Image leftPanel;
	private Timer timer;
	private final int DELAY = 15; //opoznienie animacji
	
	
	public MyPanel() {
		
		initPanel();
	}
	
	private void initPanel() {
	
		addKeyListener(new TAdapter());
		setBounds(x, y, BOARD_WIDTH, BOARD_HEIGHT);
		setFocusable(true);
		menu = new Menu();
		maps = new Maps();
		mapSelector = 0;
		loadBackground(maps.getCityMap());
		//mapSelector = 3;
		//loadBackground(cityMap.getCaveMap());
		//cityMap.initCave();
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
		
		witch = new Witch(10000, 10000, 	"src/resources/witch/witch-0-0.png",
											"src/resources/witch/witch-1-0.png",
											"src/resources/witch/witch-2-0.png",
											"src/resources/witch/witch-3-0.png",
											"src/resources/witch/witch-0-1.png",
											"src/resources/witch/witch-1-1.png",
											"src/resources/witch/witch-2-1.png",
											"src/resources/witch/witch-3-1.png",
											"src/resources/witch/witch-0-2.png",
											"src/resources/witch/witch-1-2.png",
											"src/resources/witch/witch-2-2.png",
											"src/resources/witch/witch-3-2.png",
											"src/resources/witch/witch-0-3.png",
											"src/resources/witch/witch-1-3.png",
											"src/resources/witch/witch-2-3.png",
											"src/resources/witch/witch-3-3.png");
		witch.setVisible(false);
		monsters = new ArrayList<>();
		//initMonsters();
		initNpcs();
		monk = new Npc(NPC_X, NPC_Y, "src/resources/npc_szary/npc1-0-0.png") ;
		inGame = true;
		//inMenu = true;
		timer = new Timer(DELAY, this);
		timer.restart();
	}
	
	public void initNpcs() {
		
		npcs = new ArrayList<>();
		
		for(int i = 0; i < 7; i++) {
			npcs.add(new Npc(npcPos[i][0], npcPos[i][1], "src/resources/npc/kuf_herbrycerz-0-0.png"));
		}
		
		npcs.add(new Npc(npcPos[7][0], npcPos[7][1], "src/resources/npc/hk-0-1.png"));
		npcs.add(new Npc(npcPos[8][0], npcPos[8][1], "src/resources/npc/bm-0-0.png"));
		npcs.add(new Npc(npcPos[9][0], npcPos[9][1], "src/resources/npc/mm-0-3.png"));
		npcs.add(new Npc(npcPos[10][0], npcPos[10][1], "src/resources/npc/asceta_m-0-1.png"));
		npcs.add(new Npc(npcPos[11][0], npcPos[11][1], "src/resources/npc/mm-0-1.png"));
		npcs.add(new Npc(npcPos[12][0], npcPos[12][1], "src/resources/npc/hk-0-3.png"));
		npcs.add(new Npc(npcPos[13][0], npcPos[13][1], "src/resources/npc/asceta_m-0-0.png"));
		npcs.add(new Npc(npcPos[14][0], npcPos[14][1], "src/resources/npc/asceta_m-0-2.png"));
	}
	
	public void removeNpcs() {
		
		npcs.clear();
	}
	
	public void initMonsters() {
		
		for(int[] p : monsterPos) {
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
	
	public void removeMonsters() {
		
		monsters.clear();
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
		
		if(menu.isInMenu()) {
			setBackground(Color.BLACK);
			menu.draw(g2d);
		}
		else {
		
			g2d.drawImage(background, 0, 0, this);
			
			if(hero.isVisible()) {
				g2d.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
			}
			
			if(witch.isVisible()) {
				g2d.drawImage(witch.getImage(), witch.getX(), witch.getY(), this);
				g2d.drawImage(witch.getImage(), witch.getX(), witch.getY(), this);
				g2d.setColor(Color.RED);
				g2d.fillRect(witch.getX(), witch.getY() + 50, 33, 5);
				g2d.setColor(Color.CYAN);
				g2d.fillRect(witch.getX(), witch.getY() + 50, (int) (witch.getHealth() / 120), 5);
			}
			
			List<Missile> missiles = hero.getMissiles();
			
			for(Missile missile : missiles) {
				if(missile.isVisible()) {
					g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
				}
			}
			
			List<Missile> witchMissiles = witch.getMissiles();
			
			for(Missile missile : witchMissiles) {
				if(missile.isVisible()) {
					g2d.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
				}
			}
			
			for(Monster monster : monsters) {
				if(monster.isVisible() ) {
					g2d.drawImage(monster.getImage(), monster.getX(), monster.getY(), this);
					g2d.setColor(Color.RED);
					g2d.fillRect(monster.getX(), monster.getY() + 50, 50, 5);
					g2d.setColor(Color.GREEN);
					g2d.fillRect(monster.getX(), monster.getY() + 50, (int) (monster.getHealth() / 2), 5);
				}
			}
			
			//Rectangle r1 = hero.getBounds();
			for(Npc npc : npcs) {
				if(npc.isVisible() ) {
					g2d.drawImage(npc.getImage(), npc.getX(), npc.getY(), this);
					//Rectangle r2 = npc.getBounds();
					
					if(npc.isInterrupt()) {
						
						g2d.setColor(new Color(115, 75, 20));
						g2d.fillRoundRect(npc.getX() - 47, npc.getY() - 47, 154, 44, 10, 10);
						g2d.setColor(new Color(255, 250, 185));
						g2d.fillRoundRect(npc.getX() - 45, npc.getY() - 45, 150, 40, 10, 10);
						g2d.setColor(new Color(115, 75, 20));
						//g2d.fillRoundRect(npc.getX() - 47, npc.getY() - 47, 154, 44, 5, 5);
						Font small = new Font("Helvetica", Font.BOLD, 12);
						g2d.setFont(small);
						g2d.drawString(npc.getDialog1(), npc.getX() - 40, npc.getY() - 32);
						g2d.drawString(npc.getDialog2(), npc.getX() - 40, npc.getY() - 22);
						g2d.drawString(npc.getDialog3(), npc.getX() - 40, npc.getY() - 12);
					}
				}
			}
			
			if(monk.isVisible()) { 
				g2d.setColor(Color.YELLOW);
				//g2d.fillRoundRect(npc.getX() - 47, npc.getY() - 47, 154, 44, 5, 5);
				Font big = new Font("Helvetica", Font.BOLD, 32);
				g2d.setFont(big);
				g2d.drawString("!", monk.getX() + 11, monk.getY() - 5);
				
				g2d.drawImage(monk.getImage(), monk.getX(), monk.getY(), this);
				if(monk.isInterrupt()) {
					
					g2d.setColor(new Color(115, 75, 20));
					g2d.fillRoundRect(monk.getX() - 107, monk.getY() - 95, 324, 92, 10, 10);
					g2d.setColor(new Color(255, 250, 185));
					g2d.fillRoundRect(monk.getX() - 105, monk.getY() - 93, 320, 88, 10, 10);
					g2d.setColor(new Color(115, 75, 20));
					//g2d.fillRoundRect(npc.getX() - 47, npc.getY() - 47, 154, 44, 5, 5);
					Font small = new Font("Helvetica", Font.BOLD, 12);
					g2d.setFont(small);
					g2d.drawString("Witaj Wêdrowcze!", monk.getX() - 100, monk.getY() - 82);
					g2d.drawString("W podziemiach domu przy po³udniowym murze", monk.getX() - 100, monk.getY() - 72);
					g2d.drawString("zalêg³y siê potowry, które atakuj¹ nasze miaso w nocy.", monk.getX() - 100, monk.getY() - 62);
					g2d.drawString("Pozb¹d¿ siê ich, a ludzie bêd¹ Ci wdziêczni.", monk.getX() - 100, monk.getY() - 52);
					g2d.setColor(Color.RED);
					g2d.drawString("U mnie mo¿esz ulepszyæ swój sprzêt i kupiæ mikstury.", monk.getX() - 100, monk.getY() - 42);
					g2d.drawString("[E] - kup mikstury, koszt 1000 doœw.", monk.getX() - 100, monk.getY() - 32);
					g2d.drawString("[A] - wzmocnij atak, koszt 1500 doœw.", monk.getX() - 100, monk.getY() - 22);
					g2d.drawString("[D] - wzmocnij obronê, koszt 2000 doœw.", monk.getX() - 100, monk.getY() - 12);
				}
			}
			
			//rysowanie lewego panelu
			g2d.drawImage(leftPanel, 1040, 0, this);
			g2d.setColor(Color.WHITE);
			Font small = new Font("Helvetica", Font.BOLD, 18);
			g2d.setFont(small);
			g2d.drawString("Health: " + hero.getHealth(), 1080, 45);
			g2d.drawString("Experience: " + hero.getExp(), 1080, 95);
			g2d.drawString("x " + hero.getPotions(), 1170, 180);
			g2d.drawString("x " + hero.getAttack(), 1170, 260);
			g2d.drawString("x " + hero.getArmor(), 1170, 340);
			//g2d.drawString("Monsters: " + monsters.size(), 1080, 250);
			g2d.drawString("( " + hero.getX() + ", " + hero.getY() + " )", 1180, 680);
			g2d.setColor(Color.RED);
			g2d.fillRect(1080, 50, 160, 10);
			g2d.setColor(Color.GREEN);
			g2d.fillRect(1080, 50, (int) (hero.getHealth() * 1.6), 10);
			g2d.setColor(Color.BLACK);
			g2d.fillRect(1080, 100, 160, 10);
			g2d.setColor(Color.YELLOW);
			g2d.fillRect(1080, 100, (int) (hero.getExp() / 100), 10);
			g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/potion/potion.png"), 1120, 150, this);
			g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/potion/sword.png"), 1120, 230, this);
			g2d.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/potion/shield.png"), 1120, 310, this);
		}
	}
	
	private void drawGameOver(Graphics g) {
		
		g.drawImage(Toolkit.getDefaultToolkit().getImage("src/resources/gameover.jpg"), 0, 0, this);
		String msg = "Naciœnij dowolny klawisz, aby rozpocz¹æ od nowa.";
		Font small = new Font("Helvetica", Font.BOLD, 28);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (1280 - fm.stringWidth(msg)) / 2, BOARD_HEIGHT / 3 * 2);
	}
	
	private void inGame() {
		
		if(!inGame) {
			timer.stop();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		inGame();
		
		updateWitch();
		updateMissiles();
		updateHero();
		updateMonsters();
		
		checkCollisions();
		repaint();
	}
	
	private void updateWitch() {
		witch.move();
	}
	
	private void updateMissiles () {
		List<Missile> missiles = hero.getMissiles();
		List<Missile> witchMissiles = witch.getMissiles();
		
		for(int i = 0; i < missiles.size(); i++) {
			Missile missile = missiles.get(i);
			
			if (missile.isVisible()) {
				
				missile.move(BOARD_WIDTH, BOARD_HEIGHT);
			} else {
				
				missiles.remove(i);
			}
		}
		
		for(int i = 0; i < witchMissiles.size(); i++) {
			Missile missile = witchMissiles.get(i);
			
			if (missile.isVisible()) {
				
				missile.move(BOARD_WIDTH, BOARD_HEIGHT);
			} else {
				
				witchMissiles.remove(i);
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
	
	public void mapSelect() {
		
		if(mapSelector == 0 && hero.getY() > 550) {
			
			mapSelector = 1;
			loadBackground(maps.getIndoorMap());
			maps.initIndoor();
			monk.setVisible(false);
			monk.setX(10000);
			monk.setY(10000);
			hero.setX(364);
			hero.setY(533);
			removeNpcs();
		} else
		if(mapSelector == 0) {
			
			mapSelector = 2;
			loadBackground(maps.getTavernMap());
			maps.initTavern();
			monk.setVisible(false);
			monk.setX(10000);
			monk.setY(10000);
			hero.setX(670);
			hero.setY(490);
			removeNpcs();
		} else
		if(mapSelector == 2) {
			
			mapSelector = 0;
			loadBackground(maps.getCityMap());
			maps.initCityMap();
			monk.setVisible(true);
			monk.setX(NPC_X);
			monk.setY(NPC_Y);
			hero.setX(560);
			hero.setY(208);
			initNpcs();
		} else
		if(mapSelector == 1 && hero.getX() < 400 && hero.getY() > 450) {
			
			mapSelector = 0;
			loadBackground(maps.getCityMap());
			maps.initCityMap();
			monk.setVisible(true);
			monk.setX(NPC_X);
			monk.setY(NPC_Y);
			hero.setX(160);
			hero.setY(600);
			initNpcs();
		} else
		if(mapSelector == 1 && hero.getY() < 400) {
			
			mapSelector = 3;
			loadBackground(maps.getCaveMap());
			maps.initCave();
			monk.setVisible(false);
			monk.setX(10000);
			monk.setY(10000);
			hero.setX(844);
			hero.setY(432);
			initMonsters();
		} else
		if(mapSelector == 3 && hero.getX() > 800) {
			
			mapSelector = 1;
			loadBackground(maps.getIndoorMap());
			maps.initIndoor();
			monk.setVisible(false);
			monk.setX(10000);
			monk.setY(10000);
			hero.setX(492);
			hero.setY(225);
			removeMonsters();
		} else
		if(mapSelector == 3 && hero.getX() < 800) {
			
			mapSelector = 4;
			loadBackground(maps.getChamberMap());
			maps.initChamber();
			monk.setVisible(false);
			monk.setX(10000);
			monk.setY(10000);
			hero.setX(364);
			hero.setY(648);
			witch.setVisible(true);
			witch.setX(523);
			witch.setY(135);
			removeMonsters();
		} else
		if(mapSelector == 4) {
			
			mapSelector = 3;
			loadBackground(maps.getCaveMap());
			maps.initCave();
			monk.setVisible(false);
			monk.setX(10000);
			monk.setY(10000);
			hero.setX(364);
			hero.setY(80);
			witch.setVisible(false);
			witch.setX(10000);
			witch.setY(10000);
			initMonsters();
		}
	}
	
	public void checkCollisions() {

        Rectangle r3 = hero.getBounds();
        Rectangle r4 = monk.getBounds();
        Rectangle r5 = witch.getBounds();

        if(maps.tableActions(r3) == 1)
        	hero.stop();
        if(maps.tableActions(r3) == 2 && hero.isToEnter()) {
        	
        	int option = JOptionPane.showConfirmDialog(this,"Czy chcesz wejœæ do œrodka?", "", JOptionPane.YES_NO_OPTION);
        	if(option == 0) {
        		
        		mapSelect();
        		hero.setToEnter(false);
        	} else {
        		
        		hero.setToEnter(false);
        	}			
        }
        	
        if (r3.intersects(r4)) {
            
        	hero.stop();
        	hero.addPotions();
        	hero.addAttack();
        	hero.addDef();
        	monk.setInterrupt(true);
        } else  {
        	hero.setWantPotions(false);
        	hero.setWantAttack(false);
        	hero.setWantDef(false);
        	monk.setInterrupt(false);
        }
        	
        
        for (Monster monster : monsters) {
            
            Rectangle r2 = monster.getBounds();

            if (r3.intersects(r2)) {
                
            	hero.getDamage(monster.getAttack());
                monster.setVisible(false);
            }
        }
        
        for (Npc npc : npcs) {
            
            Rectangle r2 = npc.getBounds();

            if (r3.intersects(r2)) {
                hero.stop();
                npc.setInterrupt(true);
            } else 
            	npc.setInterrupt(false);
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
                		hero.gainExp(monster.getExp());
                	}
                    m.setVisible(false);
                }
            }
            //kolizja z npc
            if (r1.intersects(r4)) {

                m.setVisible(false); 
            }
            //kolizja z wiezdzma
            if (r1.intersects(r5)) {
            	
            	witch.getDamage(hero.getAttack());
            	if(!witch.isVisible()) {
            		hero.gainExp(witch.getExp());
            		witch.setX(10000);
            		witch.setY(10000);
            	}
            	m.setVisible(false);
            }
            if(maps.tableActions(r1) == 1)
            	 
            	m.setVisible(false); 
        }  
        
        //kolizja pocisku wiedzmy z bohaterem
        List<Missile> mw = witch.getMissiles();

        for (Missile m : mw) {

            Rectangle r1 = m.getBounds();

            //kolizja z npc
            if (r1.intersects(r3)) {

            	hero.getDamage(witch.getAttack());
                m.setVisible(false); 
            }
            if(maps.tableActions(r1) == 1)
            	 
            	m.setVisible(false); 
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
			
			if(menu.isInMenu())
				menu.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			
			hero.keyReleased(e);
		}
	}
}