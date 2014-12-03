package com.turbosanta;

import com.turbosanta.gui.MainFrame;

public class TurboSanta {
	private byte[] rom;
	
	public TurboSanta() {
		MainFrame main = new MainFrame(this);
		main.build();
		main.setVisible(true);
	}
	
	public void setRom(Byte[] rom) {
		this.rom = new byte[rom.length];
		for (int i = 0; i < rom.length; i++) {
			this.rom[i] = rom[i];
		}
	}
	
	public void launch() {
		System.out.println("Launching!");
		EmulatorHandler.init(rom);
		EmulatorHandler.launch();
	}
	
	public static void main(String[] args) {
		new TurboSanta();
	}
}
