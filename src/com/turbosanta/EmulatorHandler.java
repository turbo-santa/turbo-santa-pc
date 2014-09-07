package com.turbosanta;

public class EmulatorHandler {
	private static TurboRenderer renderer;
	
	public static native void init(byte[] rom);
	public static native void launch();
	public static native void handleInput(byte keyMap);
	
	public static void graphicsCallback(int[][] bitMap) {
		renderer.submitForRender(bitMap);
	}
	
	public static void registerRenderer(TurboRenderer renderer) {
		EmulatorHandler.renderer = renderer;
	}
	

}
