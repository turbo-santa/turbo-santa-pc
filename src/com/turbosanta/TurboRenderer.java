package com.turbosanta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.concurrent.SynchronousQueue;

import javax.swing.JFrame;

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
		renderThread.start();
	}

	public void submitForRender(Integer[] bitmap) {
		renderThread.render(bitmap, bitmap.length);
	}

	private class RenderThread extends Thread {
		private BufferedImage image;
		private SynchronousQueue<Integer[]> frameQueue;
		private RenderThread() {
			image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
			frameQueue = new SynchronousQueue<Integer[]>();
		}

		private void render(Integer[] bitmap, int scanSize) {
			if (bitmap != null) {
				try {
					frameQueue.put(bitmap);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		@Override
		public void run() {
			System.out.println("RenderThread Spinning up");
			int num = 100;
			while (true) {
				Integer[] bitmap = null;
				try {
					bitmap = frameQueue.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (bitmap != null) {
					for (int y = 0; y < height; y++) {
						for (int x = 0; x < width; x++) {
							image.setRGB(x, y, bitmap[x + y * width]);
						}
					}

					Graphics g = frame.getGraphics();
					g.drawImage(image, 0, 0, frame.getWidth(), frame.getHeight(), 0, 0, width, height, null);
					if (num-- == 0 && false) {
						g.setColor(Color.WHITE);
						g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
						g.setColor(Color.BLUE);
						for (int y = 0; y < height; y++) {
							for (int x = 0; x < width; x++) {
								int pixelVal = 0x000000FF & bitmap[x+y*width];
								String pixelChar = "";
								if (pixelVal <= 62 ) {
									pixelChar = "#";
								} else if (pixelVal <= 127) {
									pixelChar = "*";
								} else if (pixelVal <= 191) {
									pixelChar = ".";
								} else {
									pixelChar = " ";
								}
								g.drawChars(pixelChar.toCharArray(), 0, 1, frame.getWidth() / width * x, frame.getHeight() / height * y);
							}
						}
						num = 100;
					}
				}
			}
		}
	}
}
