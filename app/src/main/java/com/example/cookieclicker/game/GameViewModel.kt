package com.example.cookieclicker.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cookieclicker.upgrade.AutomationTimer
import timber.log.Timber

object GameViewModel: ViewModel() {

    private var automationTimer = AutomationTimer()

    // The current score
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _rhLevel = MutableLiveData<Int>()
    val rhLevel: LiveData<Int>
        get() = _rhLevel

    private val _automationLevel = MutableLiveData<Int>()
    val automationLevel: LiveData<Int>
    get() = _automationLevel

    private val _automationMultiplier = MutableLiveData<Int>()
    val automationMultiplier: LiveData<Int>
        get() = _automationMultiplier

    // Click multiplier
    private val _clickMultiplier = MutableLiveData<Int>()
    val clickMultiplier: LiveData<Int>
        get() = _clickMultiplier

    private val _rhUpgradePrice = MutableLiveData<Int>()
    val rhUpgradePrice: LiveData<Int>
        get() = _rhUpgradePrice

    private val _automationUpgradePrice = MutableLiveData<Int>()
    val automationUpgradePrice: LiveData<Int>
        get() = _automationUpgradePrice

    init {
        Timber.i("GameViewModel Called")
        _score.value = 1000
        _clickMultiplier.value = 1
        _rhUpgradePrice.value = 100
        _automationMultiplier.value = 0
        _automationUpgradePrice.value = 500
        _rhLevel.value = 1
        _automationLevel.value = 1
    }

    fun onClick(){
        val tempMultiplier = clickMultiplier.value
        val amountToIncrement = 1 * tempMultiplier!!
        _score.value = (score.value)?.plus(amountToIncrement)
    }

    fun onClickUpgrade(){
        _clickMultiplier.value = (clickMultiplier.value)?.plus(1)
        _rhLevel.value = (rhLevel.value)?.plus(1)
    }

    fun increaseRHUpgradePrice(){
        _rhUpgradePrice.value = (rhUpgradePrice.value)?.plus(rhUpgradePrice.value!!)
    }

    fun automationUpgrade(){
        _automationMultiplier.value = (automationMultiplier.value)?.plus(1)
        _automationLevel.value = (automationLevel.value)?.plus(1)
    }

    fun increaseAutomationUpgradePrice(){
        _automationUpgradePrice.value = (automationUpgradePrice.value)?.plus(automationUpgradePrice.value!!)
    }

    fun setScore(num: Int){
        _score.value = (score.value)?.minus(num)
    }

    fun startTimer(){
        if(!automationTimer.isStarted) automationTimer.startTimer()
    }

    fun automationIncreaseScore(){
        _score.value = (score.value)?.plus(automationMultiplier.value!!)
    }



}


