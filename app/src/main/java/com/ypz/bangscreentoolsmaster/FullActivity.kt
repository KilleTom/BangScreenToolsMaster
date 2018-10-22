package com.ypz.bangscreentoolsmaster

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ypz.bangscreentools.BangScreenTools

class FullActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //
        /*requestWindowFeature(Window.FEATURE_NO_TITLE)*/

        setContentView(R.layout.activity_full)
        Log.i("BangScreenTools", BangScreenTools.getBangScreenTools().hasBangScreen(window).toString())
        val type = intent.extras.getInt("type", 0)
        Log.i("BangScreenTools",type.toString())
        when (type) {
            1 -> {
                BangScreenTools.getBangScreenTools().transparentStatusCutout(window, this)
            }
            2 -> {
                BangScreenTools.getBangScreenTools().extendStatusCutout(window, this)
            }
            3 -> {
                BangScreenTools.getBangScreenTools().fullscreen(window, this)
            }
            0 and 4 -> {
                BangScreenTools.getBangScreenTools().blockDisplayCutout(window)
            }
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        //    BangScreenTools.getBangScreenTools().windowChangeFullscreen(window)
        /* BangScreenTools.getBangScreenTools().windowChangeTransparentStatusCutout(window)*/
        // BangScreenTools.getBangScreenTools().windowChangeExtendStatusCutout(window)
        when (intent.extras.getInt("type", 0)) {
            1 -> {
                BangScreenTools.getBangScreenTools().windowChangeTransparentStatusCutout(window)
            }
            2 -> {
                BangScreenTools.getBangScreenTools().windowChangeExtendStatusCutout(window)
            }
            3 -> {
                BangScreenTools.getBangScreenTools().windowChangeFullscreen(window)
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}
