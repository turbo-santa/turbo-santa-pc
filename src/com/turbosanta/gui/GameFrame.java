package com.turbosanta.gui;

import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame {
	private static final long serialVersionUID = 6041544462933292415L;

	public GameFrame() {
		super("Turbo Santa");
		setBackground(Color.WHITE);
		setSize(160 * 3, 144 * 3);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
