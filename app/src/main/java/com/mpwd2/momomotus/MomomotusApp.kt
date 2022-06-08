package com.mpwd2.momomotus

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.GlobalScope

@HiltAndroidApp
class MomomotusApp:Application(){
    val applicationScope = GlobalScope
}