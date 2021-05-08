package com.example.memovie

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import kotlinx.android.synthetic.main.activity_set_mode.*

class SetModeActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_mode)

        btnSetMode.setOnClickListener(this)

        val sharedPrefrences: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val nightMode: Boolean = sharedPrefrences.getBoolean("NightMode", false)


        if (nightMode) {
            btnSetMode.setText("Light")
            btnSetMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sun_24 , 0 , 0 , 0)

        } else {
            btnSetMode.setText("Dark")
            btnSetMode.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_moon_24 , 0 , 0 , 0)

        }


    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnSetMode -> {
             setThemeMode()
            }
        }

    }


    fun setThemeMode(){
        val sharedPrefrences: SharedPreferences = getSharedPreferences("AppSettingPrefs", 0)
        val nightMode: Boolean = sharedPrefrences.getBoolean("NightMode", false)
        val sharedPrefEdit: SharedPreferences.Editor = sharedPrefrences.edit()

        if (nightMode) {

            Toast.makeText(this, "Mode Dark:Off", Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            sharedPrefEdit.putBoolean("NightMode" , false)
            sharedPrefEdit.apply()

        } else {

            Toast.makeText(this, "Mode Dark:On", Toast.LENGTH_SHORT).show()
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            sharedPrefEdit.putBoolean("NightMode" , true)
            sharedPrefEdit.apply()

        }
    }

}






