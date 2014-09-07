package com.turbosanta.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.turbosanta.ROMReader;
import com.turbosanta.TurboSanta;
import com.turbosanta.input.KeyboardHandler;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 8458080011516988934L;
	private final int width = 300;
	private final int height = 400;
	private String fileName;
	private TurboSanta turbo;
	
	public MainFrame(TurboSanta turbo) {
		this.turbo = turbo;
	}
	
	public void build() {
		setSize(width, height);
		JButton chooseROM = new JButton("Select ROM");
		JButton launchGame = new JButton("Launch Game");
		launchGame.setEnabled(false);
		
		launchGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				turbo.launch();
			}
		});
		chooseROM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileName = ROMReader.selectROM(MainFrame.this);
				if (fileName != null) {
					Byte[] rom = ROMReader.readRom(fileName);
					turbo.setRom(rom);
					launchGame.setEnabled(true);
				}
			}
		});
		chooseROM.addKeyListener(new KeyboardHandler());
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		
		Box buttonBox = Box.createVerticalBox();
		buttonBox.add(Box.createVerticalStrut(20));
		buttonBox.add(chooseROM);
		buttonBox.add(Box.createVerticalStrut(10));
		buttonBox.add(launchGame);
		buttonBox.add(Box.createVerticalGlue());
		Box wrapperBox = Box.createHorizontalBox();
		wrapperBox.add(Box.createHorizontalGlue());
		wrapperBox.add(buttonBox);
		wrapperBox.add(Box.createHorizontalGlue());
		pane.add(wrapperBox, "Center");
		
		addKeyListener(new KeyboardHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
