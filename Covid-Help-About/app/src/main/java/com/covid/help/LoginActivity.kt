package com.covid.help

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = "Login"
        emailEditText.setText("")
        passwordlEditText.setText("")
        //Setup
        setup()
    }

    private fun setup(){
        signUpButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordlEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailEditText.text.toString(),passwordlEditText.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            Toast.makeText(this, "Usuario creado.!", Toast.LENGTH_SHORT).show()
                            emailEditText.setText("")
                            passwordlEditText.setText("")
                        }else{
                            showAlert()
                        }
                    }
            }
        }
        loginButton.setOnClickListener{
            if (emailEditText.text.isNotEmpty() && passwordlEditText.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailEditText.text.toString(),passwordlEditText.text.toString())
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            goHome()
                            Toast.makeText(this, "Bienvenido.!", Toast.LENGTH_SHORT).show()
                            emailEditText.setText("")
                            passwordlEditText.setText("")
                        }else{
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val  dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private  fun goHome(){
        val homeIntent = Intent(this,HomeActivity::class.java)
        startActivity(homeIntent)
    }
}