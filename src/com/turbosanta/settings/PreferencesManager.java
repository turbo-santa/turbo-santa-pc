package com.turbosanta.settings;

import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Manages user preferences
 * @author Aaron Neyer
 */
public abstract class PreferencesManager {
	private static Preferences cachedPreferences = null;
	
	/**
	 * Writes the specified preferences to the preferences file and updates the cached preferences.
	 * @param prefs the preferences to be written out
	 */
	public static void writePreferences(Preferences prefs) {
		File prefFile = SettingsManager.getInstance().getSettingsFile();
		
		SettingsManager.writeSettings(prefFile, prefs);
		cachedPreferences = prefs;
	}
	
	/**
	 * Checks if the preferences file exists
	 * @return true if preferences exist
	 */
	public static boolean hasExistingPreferences() {
		File prefFile = SettingsManager.getInstance().getSettingsFile();
		return SettingsManager.readSettings(prefFile, Preferences.class) != null;
	}
	
	/**
	 * Reads the user preferences from the preferences file and caches them
	 * @return the user preferences
	 */
	public static Preferences getPreferences() {
		if (cachedPreferences == null) {
			File prefFile = SettingsManager.getInstance().getSettingsFile();
			Preferences savedPref = (Preferences)SettingsManager.readSettings(prefFile, Preferences.class);
			cachedPreferences = savedPref;
		}
		if (cachedPreferences == null) {
			cachedPreferences = new Preferences();
			writePreferences(cachedPreferences);
		}
		return cachedPreferences;
	}
	
	/**
	 * Represents a user's preferences
	 * @author Aaron Neyer
	 */
	public static class Preferences implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = -353474546084487654L;
		
		private List<String> romFiles;

		/**
		 * Creates a default Preference
		 */
		private Preferences() {
			this.romFiles = new LinkedList<String>();
		}
		
		public List<String> getRomFiles() {
			return romFiles;
		}
		
		public void addRomFile(String fileName) {
			romFiles.add(fileName);
			PreferencesManager.writePreferences(this);
		}
	}
}