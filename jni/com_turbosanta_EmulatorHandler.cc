#include "com_turbosanta_EmulatorHandler.h"

using back_end::clocktroller::Clocktroller;

Clocktroller* clocktroller_;

JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_init(JNIEnv *, jclass, jbyteArray) {

}

/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    launch
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_launch(JNIEnv *, jclass);

/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    handleInput
 * Signature: (B)V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_handleInput(JNIEnv *, jclass, jbyte);
