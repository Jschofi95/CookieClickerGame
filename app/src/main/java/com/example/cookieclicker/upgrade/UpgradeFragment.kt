package com.example.cookieclicker.upgrade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.cookieclicker.R
import com.example.cookieclicker.databinding.UpgradeFragmentBinding
import com.example.cookieclicker.game.GameViewModel

class UpgradeFragment(): Fragment() {

    private lateinit var binding: UpgradeFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.upgrade_fragment,
            container,
            false
        )

        GameViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
            binding.scoreText.text = "$" + newScore.toString()
        })

        // Attach observer to rhUpgrade price and then display price
        GameViewModel.rhUpgradePrice.observe(viewLifecycleOwner, Observer { newPrice ->
            binding.robotHandUpgradePriceTextView.text = "$" + newPrice.toString()
        })

        GameViewModel.automationUpgradePrice.observe(viewLifecycleOwner, Observer { newPrice ->
            binding.automationUpgradePriceTextView.text = "$" + newPrice.toString()
        })

        binding.upgradeHandsButton.setOnClickListener { upgradeRobotHands() }

        binding.automationUpgradeButton.setOnClickListener { upgradeAutomation() }

        return binding.root
    }

    fun upgradeRobotHands(){
        if(GameViewModel.score.value!! >= GameViewModel.rhUpgradePrice.value!!){
            var num = GameViewModel.rhUpgradePrice.value

            GameViewModel.onClickUpgrade()
            if (num != null) {
                GameViewModel.setScore(num)
            }
            GameViewModel.increaseRHUpgradePrice()
        }
    }

    private fun upgradeAutomation(){
       if(GameViewModel.score.value!! >= GameViewModel.automationUpgradePrice.value!!){
           var num = GameViewModel.automationUpgradePrice.value

           GameViewModel.startTimer()
           GameViewModel.automationUpgrade()
           GameViewModel.increaseAutomationUpgradePrice()

           if (num != null) {
               GameViewModel.setScore(num)
           }
        }
    }

}