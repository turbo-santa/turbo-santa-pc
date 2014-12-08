package com.turbosanta.input;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map.Entry;

public class KeyBindings {
	public final static byte RIGHT = (byte)0b00010000;
	public final static byte LEFT = (byte)0b00100000;
	public final static byte UP = (byte)0b01000000;
	public final static byte DOWN = (byte)0b10000000;
	public final static byte A = (byte)0b00000001;
	public final static byte B = (byte)0b00000010;
	public final static byte SELECT = (byte)0b00000100;
	public final static byte START = (byte)0b00001000;
	
	private static HashMap<Integer, Byte> keyMap = getDefaultMapping();
	
	public static void setBinding(Integer keyCode, byte keyBinding) {
		// ensure bindings are 1:1
		for (Entry<Integer, Byte> entry : keyMap.entrySet()) {
			if (entry.getValue() == keyBinding) {
				keyMap.remove(entry);
			}
		}
		keyMap.put(keyCode, keyBinding);
	}
	
	public static void setAllBindings(HashMap<Integer, Byte> bindings) {
		keyMap.clear();
		keyMap.putAll(bindings);
	}
	
	public static byte getBinding(KeyEvent event) {
		Byte binding = keyMap.get(event.getKeyCode());
		return binding == null ? 0 : binding;
	}
	
	public static HashMap<Integer, Byte> getDefaultMapping() {
		HashMap<Integer, Byte> defaultMap = new HashMap<Integer, Byte>();
		defaultMap.put(37, LEFT); // Left
		defaultMap.put(38, UP); // Up
		defaultMap.put(39, RIGHT); // Right
		defaultMap.put(40, DOWN); // Down
		defaultMap.put(65, A); // A
		defaultMap.put(90, B); // Z
		defaultMap.put(83, START); // S
		defaultMap.put(88, SELECT); // X
		return defaultMap;
	}
}
