package com.flighthotelapplication.utils

import android.annotation.SuppressLint
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper

object ThreadUtils {

    private var HANDLER_THREAD: HandlerThread? = null
    private val backgroundLooper: Looper
        get() {
            if (HANDLER_THREAD == null) {
                HANDLER_THREAD = HandlerThread("Background")
                HANDLER_THREAD!!.start()
            }
            return HANDLER_THREAD!!.looper
        }

    fun runOnMainThread(task: Runnable) {
        Handler(Looper.getMainLooper()).post(task)
    }

    @SuppressLint("NewApi")
    fun runOnBackgroundThread(task: Runnable): Handler {
        val handler = Handler(backgroundLooper)
        handler.post(task)
        HANDLER_THREAD!!.quitSafely()
        HANDLER_THREAD = null
        return handler
    }
}
