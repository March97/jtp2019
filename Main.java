package jtp2019;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Main extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable()  {
			public void run() {
				new MyFrame();
			}
		});
	}
}