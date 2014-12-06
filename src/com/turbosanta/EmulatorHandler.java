package com.turbosanta;

public class EmulatorHandler {
	static {
        System.loadLibrary("santa_jni");
    }
	
	private static TurboRenderer renderer;
	
	public static native void init(byte[] rom);
	public static native void launch();
	public static native void handleInput(byte keyMap);
	public static native void stop();
	
	public static void graphicsCallback(byte[] bitMapBytes, int length) {
		Integer[] bitmap = new Integer[length];
		for (int i = 0; i < length; i++) {
			bitmap[i] = ((int)~bitMapBytes[i] + 1) << 16 | ((int)~bitMapBytes[i] + 1) << 8 | ((int)~bitMapBytes[i]);
		}
		renderer.submitForRender(bitmap);
	}
	
	public static void registerRenderer(TurboRenderer renderer) {
		EmulatorHandler.renderer = renderer;
	}
}
