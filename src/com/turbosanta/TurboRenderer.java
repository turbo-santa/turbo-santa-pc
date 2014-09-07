package com.turbosanta;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class TurboRenderer {
	private JFrame frame;
	private BufferedImage image;
	private int width;
	private int height;

	public void setup(JFrame renderTarget, int width, int height) {
		frame = renderTarget;
		this.width = width;
		this.height = height;
	}

	// TODO: make this a background task
	public void submitForRender(int[][] bitmap) {
		render(convertBitmap(bitmap), bitmap.length);
	}
	
	// TODO: ask cameron how jank this is
	private void render(int[] bitmap, int scanSize) {
		frame.createBufferStrategy(1);
		BufferStrategy strategy = frame.getBufferStrategy();
		image.setRGB(0, 0, width, height, bitmap, 0, scanSize);
		do {
			do {
				Graphics g = strategy.getDrawGraphics();
				g.drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
				g.dispose();
			} while (strategy.contentsRestored());
			strategy.show();
		} while (strategy.contentsLost());
	}

	private int[] convertBitmap(int[][] bitMap) {
		int[] newBitmap = new int[bitMap.length * bitMap[0].length];
		for (int j = 0; j < bitMap[0].length; j++) {
			for (int i = 0; i < bitMap.length; i++) {
				newBitmap[bitMap.length * j + i] = bitMap[j][i];
			}
		}
		return newBitmap;
	}
}
