package com.hrithikvish.apitask2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hrithikvish.apitask2.databinding.ActivityHomeBinding
import com.hrithikvish.apitask2.db.CustomerDatabase

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding

    lateinit var database: CustomerDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = CustomerDatabase.getInstance(applicationContext)

        val customerid = intent.getIntExtra("customerid", -1)
        val data = database.customerDao().getData(customerid)

        binding.nameText.text = data.customername
        binding.dataText.text = data.toString()

        binding.logoutButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

    }
}