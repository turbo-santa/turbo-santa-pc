package com.turbosanta;

import java.awt.Component;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ROMReader {
	public static byte[] readRom(String path) {	
		try {
			return Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String selectROM(Component parent) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Gameboy ROM", "gb", "gbc"));
		int returnVal = chooser.showOpenDialog(parent);
		return returnVal == JFileChooser.APPROVE_OPTION ?
			 chooser.getSelectedFile().getAbsolutePath() : null;
	}
}