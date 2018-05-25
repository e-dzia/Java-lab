#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <math.h>
#include "Main.h"

/*
 * Class:     Main
 * Method:    helloWorld
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Main_helloWorld(JNIEnv *env, jobject thisObj){
    printf("Hello World!\n");
    return;
}

/*
 * Class:     Main
 * Method:    isPrime
 * Signature: (I)Z
 */
JNIEXPORT jboolean JNICALL Java_Main_isPrime(JNIEnv *env, jobject thisObj, jint num){
    jboolean result = JNI_FALSE;
    if (num < 2) return result;
    result = JNI_TRUE;
    for (jint i = 2; i <= sqrt(num); i++){
        if (num % i == 0) {
            result = JNI_FALSE;
            return result;
        }
    }
    return result;
}

/*
 * Class:     Main
 * Method:    forEachElement
 * Signature: ([FFLjava/lang/String;)[F
 */
JNIEXPORT jfloatArray JNICALL Java_Main_forEachElement(JNIEnv *env, jobject thisObj, jfloatArray array, jfloat val, jstring op){
    // Convert the JNI String (jstring) into C-String (char*)
    const char *inCOp = (*env)->GetStringUTFChars(env, op, NULL);
    if (NULL == inCOp) return NULL;

    // Convert the incoming JNI jfloatarray to C's jfloat[]
    jfloat *inCArray = (*env)->GetFloatArrayElements(env, array, NULL);
    if (NULL == inCArray) return NULL;
    jsize length = (*env)->GetArrayLength(env, array);

    // New array so the original one remains untouched
    jfloat returnCArray[length];

    for (int i = 0; i < length; i++) {
        if (strcmp ("add", inCOp) == 0){
            returnCArray[i] = inCArray[i] + val;
        }
        else if (strcmp ("subtract", inCOp) == 0){
            returnCArray[i] = inCArray[i] - val;
        }
        else if (strcmp ("multiply", inCOp) == 0){
            returnCArray[i] = inCArray[i] * val;
        }
        else if (strcmp ("divide", inCOp) == 0){
            returnCArray[i] = inCArray[i] / val;
        }
    }

    (*env)->ReleaseStringUTFChars(env, op, inCOp);  // release resources

    // Return JfloatArray
    jfloatArray outJNIArray = (*env)->NewFloatArray(env, length);  // allocate
    if (NULL == outJNIArray) return NULL;
    (*env)->SetFloatArrayRegion(env, outJNIArray, 0 , length, returnCArray);  // copy

    (*env)->ReleaseFloatArrayElements(env, array, inCArray, 0);
    return outJNIArray;
}
