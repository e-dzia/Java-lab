/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class Main */

#ifndef _Included_Main
#define _Included_Main
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     Main
 * Method:    helloWorld
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Main_helloWorld
  (JNIEnv *, jobject);

/*
 * Class:     Main
 * Method:    isPrime
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_Main_isPrime
  (JNIEnv *, jobject, jint);

/*
 * Class:     Main
 * Method:    forEachElement
 * Signature: ([FFLjava/lang/String;)[F
 */
JNIEXPORT jfloatArray JNICALL Java_Main_forEachElement
  (JNIEnv *, jobject, jfloatArray, jfloat, jstring);

#ifdef __cplusplus
}
#endif
#endif
