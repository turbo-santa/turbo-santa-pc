package com.turbosanta;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class TurboRenderer {
	private final JFrame frame;
	private int width;
	private int height;
	private RenderThread renderThread;
	
	public TurboRenderer(JFrame renderTarget, int width, int height) {
		frame = renderTarget;
		this.width = width;
		this.height = height;
		renderThread = new RenderThread();
	}

	public void submitForRender(int[][] bitmap) {
		renderThread.render(convertBitmap(bitmap), bitmap.length);
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
	
	private class RenderThread extends Thread {
		private int[] bitmap;
		private int scanSize;
		private BufferedImage image;
		
		private RenderThread() {
			image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		}
		
		private void render(int[] bitmap, int scanSize) {
			this.bitmap = bitmap;
			this.scanSize = scanSize;
			super.start();
		}
		
		@Override
		public void run() {
			image.setRGB(0, 0, width, height, bitmap, 0, scanSize);
			Graphics g = frame.getGraphics();
			g.drawImage(image, 0, 0, width, height, 0, 0, width, height, null);
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					frame.repaint();					
				}
			});
		}
	}
}
