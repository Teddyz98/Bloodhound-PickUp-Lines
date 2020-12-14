package com.example.bloodhoundpickuplines

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Field
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val soundList: MutableList<Int> = ArrayList() // list of all sounds
    var mp = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        for (i in 5..76){ // better find a smart solution (hardcoded numbers)
            val resId: Int = resources.getIdentifier("b$i", "raw", this.packageName)
            soundList.add(resId)
        }

        val slatraBtn: Button = findViewById<View>(R.id.random_quote) as Button // bind button
        slatraBtn.setOnClickListener {
            playRandomSound()
        }
    }
    private fun playRandomSound() {
        if (mp.isPlaying) { // stop current sound and play next
            mp.pause()
            mp.seekTo(0)
        }
        val randomInt: Int = Random().nextInt(soundList.size)
        val sound: Int = soundList.get(randomInt)
        val myUri = Uri.parse("android.resource://" + this.packageName + "/" + sound) //dinamically find needed res
        mp.reset() // important
        mp.setDataSource(this, myUri); // set netx audio to play
        mp.prepare();
        mp.start()
    }

}