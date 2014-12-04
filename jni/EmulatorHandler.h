#include <jni.h>
/* Header for class EmulatorHandler */

#ifndef _Included_com_turbosanta_EmulatorHandler
#define _Included_com_turbosanta_EmulatorHandler
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    init
 * Signature: ([B)V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_init
  (JNIEnv *env, jclass, jbyteArray romBytes);

/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    launch
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_launch
  (JNIEnv *env, jclass);

/*
 * Class:     com_turbosanta_EmulatorHandler
 * Method:    handleInput
 * Signature: (B)V
 */
JNIEXPORT void JNICALL Java_com_turbosanta_EmulatorHandler_handleInput
  (JNIEnv *env, jclass, jbyte inputMap);

#ifdef __cplusplus
}
#endif
#endif
