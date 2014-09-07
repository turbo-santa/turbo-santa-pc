#include <jni.h>

#ifndef TURBO_SANTA_PC_EMULATOR_HANDLER_H_
#define TURBO_SANTA_PC_EMULATOR_HANDLER_H_

#ifdef __cplusplus
extern "C" {
#endif

static jclass emulatorHandlerClass;
static jmethodID jgraphicsCallback;

static void invokeJavaCallback(jmethodID method, ...) {
  JNIEnv *env = getThreadEnv();
  va_list args;
  va_start(args, method);
  (*env)->CallStaticVoidMethodV(env, emulatorHandlerClass, method, args);
  va_end(args);
}

static void postGraphics(int[][] bitMap) {
  invokeJavaCallback(jgraphicsCallback, bitMap, 
}

JNIEXPORT void JNICALL 
Java_com_turbosanta_EmulatorHandler_init(JNIEnv *, jobject this, jbyteArray rom) {
  // TODO: add interface to backend  
}

JNIEXPORT void JNICALL
Java_com_turbosanta_EmulatorHandler_launch() {
  // TODO: start emulation
}

JNIEXPORT void JNICALL
Java_com_turbosanta_EmulatorHandler_handleInput(JNIEnv *, jobject this, jbyte key_map) {
  // TODO: write key_map to memory address 0xFF00 
}


#ifdef __cplusplus  
}
#endif
#endif
