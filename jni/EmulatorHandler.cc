#include "EmulatorHandler.h"
#include "TurboSanta.h"

static jclass emulatorHandlerClass;
static jmethodID jgraphicsCallback;
static JavaVM* jvm;

std::unique_ptr<TurboSanta> turbo(new TurboSanta());

static void invokeJavaCallback(jmethodID method, ...) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	va_list args;
	va_start(args, method);
	env->CallStaticVoidMethodV(emulatorHandlerClass, method, args);
	va_end(args);
}


void videoCallback(const signed char* bitmap, int length) {
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	jbyteArray bitmapArray = env->NewByteArray(length);
	env->SetByteArrayRegion(bitmapArray, 0, length, bitmap);
	invokeJavaCallback(jgraphicsCallback, bitmapArray, length);
	env->DeleteLocalRef(bitmapArray);
}

JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_init(JNIEnv *env, jclass, jbyteArray romBytes) {
	fprintf(stderr, "NATIVE: init\n");

	jboolean isCopy;
	jbyte* rom = env->GetByteArrayElements(romBytes, &isCopy);

	turbo->init((unsigned char*)rom, env->GetArrayLength(romBytes), &videoCallback);
}

/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    launch
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_launch(JNIEnv *env, jclass) {
	turbo->launch();
}

JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_stop (JNIEnv *env, jclass) {
	turbo->stop();
}

/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    handleInput
 * Signature: (B)V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_handleInput(JNIEnv *env, jclass, jbyte inputMap) {

}

// This is called when the library is first loaded
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *pjvm, void *reserved) {
	jvm = pjvm;  // store the jvm for use later (this is safe)
	JNIEnv *env;
	jvm->AttachCurrentThread((void**)&env, NULL);
	emulatorHandlerClass = env->FindClass("com/turbosanta/EmulatorHandler");
	jgraphicsCallback = env->GetStaticMethodID(emulatorHandlerClass, "graphicsCallback", "([BI)V");

	return JNI_VERSION_1_8;
}
