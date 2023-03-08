package com.example.tokenretro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tokenretro.databinding.ActivitySearchBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var binding: ActivitySearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()
        val interceptor=HttpLoggingInterceptor()
        interceptor.level=HttpLoggingInterceptor.Level.BODY
        val client=OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
        val retrofit=Retrofit.Builder().baseUrl("https://xn--h1aafpog.xn--90ahbflhjgobv0ae.xn--p1ai/").addConverterFactory(GsonConverterFactory.create()).client(client).build()

        val api=retrofit.create(ApiService::class.java)

        CoroutineScope(Dispatchers.IO).launch {
            val car=api.getTest(token = auth.uid.toString())

            runOnUiThread {

                binding.textView2.text=car.user?.name
                binding.textView3.text=car.user?.age
                Toast.makeText(applicationContext,car.message,Toast.LENGTH_SHORT).show()
            }
        }
    }
}