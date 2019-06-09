package jtp2019;

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
