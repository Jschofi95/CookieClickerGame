package com.example.cookieclicker.upgrade

import android.os.Handler
import android.os.Looper
import com.example.cookieclicker.game.GameViewModel
import timber.log.Timber

class AutomationTimer {

    // The number of seconds counted since the timer started
    var seconds = 0
    var isStarted = false

    /**
     * [Handler] is a class meant to process a queue of messages (known as [android.os.Message]s)
     * or actions (known as [Runnable]s)
     */
    private var handler = Handler(Looper.getMainLooper())
    private lateinit var runnable: Runnable

    fun startTimer() {
        isStarted = true
        // Create the runnable action, which prints out a log and increments the seconds counter
        runnable = Runnable {
            seconds++
            Timber.i("Timer is at : $seconds")
            GameViewModel.automationIncreaseScore()
            // postDelayed re-adds the action to the queue of actions the Handler is cycling
            // through. The delayMillis param tells the handler to run the runnable in
            // 1 second (1000ms)
            handler.postDelayed(runnable, 1000)
        }

        // This is what initially starts the timer
        handler.postDelayed(runnable, 1000)

        // Note that the Thread the handler runs on is determined by a class called Looper.
    }

    fun stopTimer() {
        isStarted = false
        // Removes all pending posts of runnable from the handler's queue, effectively stopping the
        // timer
        handler.removeCallbacks(runnable)
    }
}