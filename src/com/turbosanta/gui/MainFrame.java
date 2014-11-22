package com.turbosanta.gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;

import com.turbosanta.ROMReader;
import com.turbosanta.TurboSanta;
import com.turbosanta.input.KeyboardHandler;
import com.turbosanta.settings.PreferencesManager;
import com.turbosanta.settings.PreferencesManager.Preferences;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 8458080011516988934L;
	private final int width = 300;
	private final int height = 400;
	private final Preferences preferences = PreferencesManager.getPreferences();
	private final JButton launchGame = new JButton("Launch Game");
	private final JComboBox<String> romSelector = new JComboBox<String>();
	private String fileName;
	private TurboSanta turbo;
	
	public MainFrame(TurboSanta turbo) {
		super("Turbo Santa");
		this.turbo = turbo;
	}
	
	public void build() {
		setSize(width, height);
		
		launchGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO: Catch errors here
				Byte[] rom = ROMReader.readRom((String)romSelector.getSelectedItem());
				turbo.setRom(rom);
				turbo.launch();
			}
		});
		
		for (String rom : preferences.getRomFiles()) {
			romSelector.addItem(rom);
		}
		
		if (preferences.getRomFiles().isEmpty()) {
			launchGame.setEnabled(false);
		} else {
			launchGame.setEnabled(true);
		}
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		Box romBox = Box.createHorizontalBox();
		romBox.add(Box.createHorizontalGlue());
		romBox.add(romSelector);
		romBox.add(Box.createHorizontalGlue());
		
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(Box.createHorizontalGlue());
		buttonBox.add(launchGame);
		buttonBox.add(Box.createHorizontalGlue());


		mainPanel.add(Box.createVerticalStrut(10));
		mainPanel.add(romBox);
		mainPanel.add(Box.createVerticalStrut(100));
		mainPanel.add(buttonBox);
		mainPanel.add(Box.createVerticalGlue());

		getContentPane().add(mainPanel);
		
		setJMenuBar(createMenuBar());
		
		addKeyListener(new KeyboardHandler());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		menuBar.add(createFileMenu());
		menuBar.add(createOptionsMenu());
		menuBar.add(createHelpMenu());
		return menuBar;
	}
	
	private JMenu createHelpMenu() {
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem aboutItem = new JMenuItem("About");
		helpMenu.add(aboutItem);
		
		return helpMenu;
	}
	
	private JMenu createOptionsMenu() {
		JMenu optionsMenu = new JMenu("Options");
		JMenu speedMenu = new JMenu("Speed");
		
		// TODO: Make this actually do something
		JRadioButtonMenuItem speed1 = new JRadioButtonMenuItem("1x");
		JRadioButtonMenuItem speed2 = new JRadioButtonMenuItem("2x");
		JRadioButtonMenuItem speed3 = new JRadioButtonMenuItem("3x");
		JRadioButtonMenuItem speed4 = new JRadioButtonMenuItem("4x");
		ButtonGroup speedGroup = new ButtonGroup();
		speedGroup.add(speed1);
		speedGroup.add(speed2);
		speedGroup.add(speed3);
		speedGroup.add(speed4);
		speedMenu.add(speed1);
		speedMenu.add(speed2);
		speedMenu.add(speed3);
		speedMenu.add(speed4);
		speed1.setSelected(true);
		
		optionsMenu.add(speedMenu);
		
		return optionsMenu;
	}
	
	private JMenu createFileMenu() {
		JMenu fileMenu = new JMenu("File");

		JMenuItem addROM = new JMenuItem("Select new ROM");
		fileMenu.add(addROM);
		addROM.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileName = ROMReader.selectROM(MainFrame.this);
				if (fileName != null) {
					launchGame.setEnabled(true);
					preferences.addRomFile(fileName);
					romSelector.addItem(fileName);
					romSelector.setSelectedItem(fileName);;
				}
			}
		});
		
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exitItem);
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				// TODO: Eventually this will contain some clean up/confirmations
			}
		});

		return fileMenu;
	}
}