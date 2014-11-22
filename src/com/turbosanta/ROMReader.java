package com.turbosanta;

import java.awt.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Loads and reads ROMs.
 * @author David
 * @date September 6, 2014
 */
public class ROMReader {
	public static Byte[] readRom(String path) {	
		FileReader reader = null;
		ArrayList<Byte> romByteArray = new ArrayList<Byte>();
		
		try {
			reader = new FileReader(path);
			int byteRead;
			while((byteRead = reader.read()) != -1) {
				romByteArray.add((byte)byteRead);
			}
		}  catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException error) {}
			}
		}
				
		return romByteArray.toArray(new Byte[0]);
	}

	public static String selectROM(Component parent) {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(new FileNameExtensionFilter("Gameboy ROM", "gb", "gbc"));
		int returnVal = chooser.showOpenDialog(parent);
		return returnVal == JFileChooser.APPROVE_OPTION ?
			 chooser.getSelectedFile().getAbsolutePath() : null;
	}
}