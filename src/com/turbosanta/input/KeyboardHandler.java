package com.turbosanta.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.turbosanta.EmulatorHandler;

public class KeyboardHandler implements KeyListener {
	private byte keyMap = 0x00;
	
	@Override
	public void keyPressed(KeyEvent e) {
		byte binding = KeyBindings.getBinding(e);
		keyMap |= binding;
		EmulatorHandler.handleInput(keyMap);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		byte binding = KeyBindings.getBinding(e);
		keyMap &= ~(binding);
		EmulatorHandler.handleInput(keyMap);
	}
	
	// Unused
	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}
