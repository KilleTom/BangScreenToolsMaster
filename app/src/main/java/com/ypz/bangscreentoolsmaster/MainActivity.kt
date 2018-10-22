package com.ypz.bangscreentoolsmaster

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this,FullActivity::class.java)
        tran_status.setOnClickListener {
            startActivity(intent.putExtra("type",1))
        }
        extend_status.setOnClickListener {
            startActivity(intent.putExtra("type",2))
        }
        fullscreen.setOnClickListener {
            startActivity(intent.putExtra("type",3))
        }
        not_bang.setOnClickListener {
            startActivity(intent.putExtra("type",4))
        }
    }
}
