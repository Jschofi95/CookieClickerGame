package com.example.cookieclicker.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.cookieclicker.R
import com.example.cookieclicker.databinding.GameFragmentBinding
import timber.log.Timber

class GameFragment: Fragment() {

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        Timber.i("onCreateView called")

        // Inflate view and obtain instance of binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.game_fragment,
            container,
            false
        )

        GameViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = "$$newScore"
        })

        GameViewModel.rhLevel.observe(viewLifecycleOwner, Observer { newLevel ->
            binding.statsRobotHandsLevelTextView.text = newLevel.toString()
        })

        GameViewModel.automationLevel.observe(viewLifecycleOwner, Observer { newLevel ->
            binding.statsAutomationLevelTextView.text = newLevel.toString()
        })

        binding.robotButton.setOnClickListener { onClicked() }

        binding.upgradesButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameFragment_to_upgradeFragment)
        }

        return binding.root
    }

    fun onClicked(){
        GameViewModel.onClick()
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

//private lateinit var viewModel: GameViewModel

// Get the viewModel
//viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

// Attach observer to the "score" variable in viewModel
//viewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
    //binding.scoreText.text = "$" + newScore.toString()
//})

