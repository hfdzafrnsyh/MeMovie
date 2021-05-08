package com.example.memovie.utils

import android.os.Handler
import android.os.Looper
import androidx.annotation.VisibleForTesting
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppExecutors @VisibleForTesting constructor(
        private var diskIO : Executor,
        private var networkIO : Executor,
        private var mainThread: Executor
) {

    companion object{
        private const val THREAD_COUNT  = 3
    }


    constructor() : this (
            Executors.newSingleThreadExecutor(),
            Executors.newFixedThreadPool(THREAD_COUNT),
            MainThreadExecutor()
            )

    fun diskIO() : Executor = diskIO

    fun networkIO() : Executor = networkIO

    fun mainThread() : Executor = mainThread

    private class MainThreadExecutor : Executor {
        private val mainThreadHanlder = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable) {
            mainThreadHanlder.post(command)
        }
    }

    class TestExecutor : Executor {
        override fun execute(command: Runnable) {
            command.run()
        }
    }


}