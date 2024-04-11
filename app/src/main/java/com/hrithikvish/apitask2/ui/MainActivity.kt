package com.hrithikvish.apitask2.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.hrithikvish.apitask2.api.RetrofitHelper
import com.hrithikvish.apitask2.databinding.ActivityMainBinding
import com.hrithikvish.apitask2.db.CustomerDatabase
import com.hrithikvish.apitask2.model.CustomerData
import com.hrithikvish.apitask2.util.Validator
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val validator = Validator()
    private lateinit var database: CustomerDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = CustomerDatabase.getInstance(applicationContext)

        binding.loginButton.setOnClickListener {
            val phone: String = binding.phoneET.text.toString()
            if (validator.validatePhone(phone)) {

                lifecycleScope.launch {

                    getCustomerDataFromApi(phone) { customerData ->
                        if(customerData != null) {
                            customerData.Data?.let {
                                lifecycleScope.launch {
                                    database.customerDao().insert(it.get(0))
                                }
                            }
                            val intent = Intent(this@MainActivity, HomeActivity::class.java)
                            intent.putExtra("customerid", customerData.Data?.get(0)?.customerid)
                            startActivity(intent)
                            finish()
                        }
                    }

                }

            } else {
                Toast.makeText(this, "Enter valid phone number", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun getCustomerDataFromApi(phone: String, callback: (CustomerData?) -> Unit) {
        RetrofitHelper.apiService.getCustomerData(phone).enqueue(object : Callback<CustomerData?> {
            override fun onResponse(call: Call<CustomerData?>, response: Response<CustomerData?>) {
                if(response.isSuccessful) {
                    callback(response.body())
                } else {
                    callback(null)
                }
            }

            override fun onFailure(call: Call<CustomerData?>, t: Throwable) {
                Log.d("onFailure", t.localizedMessage)
                callback(null)
            }
        })
    }
}