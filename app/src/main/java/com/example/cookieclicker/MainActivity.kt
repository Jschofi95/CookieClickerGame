package com.example.cookieclicker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    //TODO Start and stop timer accordingly based on app lifecycle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Timber.i("onStart called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    // *NOTE This function is called when MainActivity is either called back into focus or the app is resumed from being "minimized" or put in the background
    override fun onResume() {
        super.onResume()
        Timber.i("onResume called")
    }

    // *NOTE This function is called when MainActivity is either NOT in focus or the app is minimized (Clicking on the "share" menu takes the activity out of focus but remains visible)
    override fun onPause() {
        super.onPause()
        Timber.i("onPause called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called")
    }
}