package com.turbosanta.settings;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Manages the settings files
 * @author Aaron Neyer
 */
public class SettingsManager {
	/**
	 * Directory to which settings will be saved
	 */
	public static String SETTINGS_DIR = System.getProperty("user.home") + File.separator + "TurboSanta";
	
	private File settingsDir;
	
	private File settingsFile;
	
	private static SettingsManager manager;
	
	/*
	 * Constructs a manager that initializes the settings files
	 */
	private SettingsManager() {
		settingsFile = new File(SETTINGS_DIR + File.separator + "settings.json");
		settingsDir = new File(SETTINGS_DIR);
	}
	
	/**
	 * Gets an instance of the manager, this is a singleton
	 * @return the instance of the manager
	 */
	public static SettingsManager getInstance() {
		if (manager == null) {
			manager = new SettingsManager();
		}
		return manager;
	}
	
	/**
	 * Gets the settings file (user preferences), if the file does not exist, it is created first
	 * @return the settings file
	 */
	public File getSettingsFile() {
		if (!settingsDir.exists()) {
			settingsDir.mkdirs();
		}
		
		if (!settingsFile.exists()) {
			try {
				settingsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
		
		return settingsFile;
	}
	
	/**
	 * Reads the specified file as a settings file and returns the result.
	 * <br>A settings file must be a java serialized object
	 * @param <T>
	 * @param file the file to read in
	 * @return the settings represented in this file
	 */
	public static <T> Object readSettings(File file, Class<T> klass) {
		Gson gson = new GsonBuilder()
          .setPrettyPrinting().enableComplexMapKeySerialization().create();
		T settings = null;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			settings = gson.fromJson(br, klass);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return settings;
	}
	
	/**
	 * Writes the specified settings to the desired file
	 * @param file the file to write the settings to 
	 * @param settings the settings to be written out
	 */
	public static <T extends Serializable> void writeSettings(File file, T settings) {
		Gson gson = new GsonBuilder()
			.setPrettyPrinting().enableComplexMapKeySerialization().create();
		FileWriter writer = null;

		try {
			String json = gson.toJson(settings);
			writer = new FileWriter(file);
			writer.write(json);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();

		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}