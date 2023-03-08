package com.example.tokenretro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tokenretro.databinding.ActivityName2Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NameActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityName2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityName2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val id=intent.getStringExtra("id")

        binding.textView222.setOnClickListener {
            startActivity(Intent(applicationContext,SearchActivity::class.java))
            finish()
        }



        binding.button2.setOnClickListener {
            val api=Retrofit.Builder().baseUrl("https://xn--h1aafpog.xn--90ahbflhjgobv0ae.xn--p1ai/").addConverterFactory(GsonConverterFactory.create()).build()
            val service=api.create(ApiService::class.java)
            CoroutineScope(Dispatchers.IO).launch {
                val text=service.getAuth(
                    Auth(
                        id.toString(),
                    binding.editTextTextMultiLine.text.toString(),
                    binding.editTextTextMultiLine2232323.text.toString())
                )

                runOnUiThread{
                    if (text.status==true){
                        Toast.makeText(applicationContext,"Successs",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext,"Error",Toast.LENGTH_SHORT).show()
                    }
                    binding.textView.text=text.text
                }
            }
        }
    }
}