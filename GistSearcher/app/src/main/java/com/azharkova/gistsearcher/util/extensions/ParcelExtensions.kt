package com.azharkova.gistsearcher.util.extensions

import android.os.Parcel
import java.util.LinkedHashMap

fun<K,V> Parcel.readMap(map: MutableMap<K,V>) : MutableMap<K,V>{

    val tempMap = LinkedHashMap<Any?,Any?>()
    readMap(tempMap, map.javaClass.classLoader)

    tempMap.forEach {
        map[it.key as K] = it.value as V
    }
    return map
}

fun<K,V> Parcel.readMapLoading(map: MutableMap<K,V>,loader: ClassLoader) : MutableMap<K,V>{

    val tempMap = LinkedHashMap<Any?,Any?>()
    readMap(tempMap, loader)

    tempMap.forEach {
        map[it.key as K] = it.value as V
    }
    return map
}
