package com.sadikahmetozdemir.notezz.core.ui

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Searches for a given parameterized class type in the receivers type hierachy and returns it if it was found.
 * Returns `null` otherwise.
 */
inline fun <reified T> Any.findGenericSuperclass(): ParameterizedType? {
    return javaClass.findGenericSuperclass(T::class.java)
}

/**
 * Searches for a given parameterized class type in the receivers hierachy and returns it if it was found.
 * Returns `null` otherwise.
 */
tailrec fun <T> Type.findGenericSuperclass(targetType: Class<T>): ParameterizedType? {
    val genericSuperClass = ((this as? Class<*>)?.genericSuperclass) ?: return null

    if ((genericSuperClass as? ParameterizedType)?.rawType == targetType) {
        return genericSuperClass
    }

    return genericSuperClass.findGenericSuperclass(targetType)
}
