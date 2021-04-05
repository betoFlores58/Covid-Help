package com.covid.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        title = "Inicio"
        setUp()
    }

    private fun setUp(){
        logOutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }

        btnNoticias.setOnClickListener {
            val newsIntent = Intent(this,NoticiasActivity::class.java)
            startActivity(newsIntent)
        }
    }
}