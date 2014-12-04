package com.turbosanta;

import com.turbosanta.gui.GameFrame;
import com.turbosanta.gui.MainFrame;

public class TurboSanta {
	private static final int WIDTH = 160;
	private static final int HEIGHT = 144;

	private byte[] rom;
	private TurboRenderer renderer;
	private MainFrame main;
	private GameFrame gameFrame;
	public TurboSanta() {
		main = new MainFrame(this);
		main.build();
		main.setVisible(true);
		gameFrame = new GameFrame();
		renderer = new TurboRenderer(gameFrame, WIDTH, HEIGHT);
	}

	public void setRom(Byte[] rom) {
		this.rom = new byte[rom.length];
		for (int i = 0; i < rom.length; i++) {
			this.rom[i] = rom[i];
		}
	}

	public void launch() {
		System.out.println("Initializing");
		EmulatorHandler.init(rom);
		EmulatorHandler.registerRenderer(renderer);
		System.out.println("Launching!");
		main.setVisible(false);
		gameFrame.setVisible(true);
		EmulatorHandler.launch();
	}

	public static void main(String[] args) {
		new TurboSanta();

		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				EmulatorHandler.stop();
			}
		});
	}
}
