package com.example.mula.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mula.R
import com.example.mula.data.DataManager


class MainActivity : AppCompatActivity() {
    var dm: DataManager? = null
    var progressDialog: ProgressDialog? = null

    private lateinit var enterBalanceEditText: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Database and UI code goes here
        dm = DataManager(this)

        //Initialize UI Components
        enterBalanceEditText = findViewById(R.id.enterBalanceId)
        submitButton = findViewById(R.id.submit_button)

        //Saving alert dialog
        progressDialog = ProgressDialog(this)

        // Submit enterBalance via keyboard submit
//        enterBalanceEditText.onSubmit { // Get text from EditText
//            // com.example.mula.insert balance into database
//            if (!TextUtils.isEmpty(enterBalanceEditText.text.toString())) {
//                // com.example.mula.insert balance into database
//                //dm?.insert(enterBalance.text.toString())
//
//                startActivity(Intent(this, AllocationListActivity::class.java))
//
//            } else {
//                Toast.makeText(this, "Please enter starting balance", Toast.LENGTH_LONG).show()
//            }
//        }
        //Submit input via submit_button // todo: Decide which submit method to use
        submitButton.setOnClickListener() {

//            if (!TextUtils.isEmpty(enterBalanceEditText.text.toString())) {
//                progressDialog!!.setMessage("Saving...")
//
//                dm?.insert(enterBalance.text.toString())
//
//                progressDialog!!.show()
//
//                progressDialog!!.cancel()
//
//                startActivity(Intent(this, AllocationListActivity::class.java))
//
//            } else {
//                Toast.makeText(this, "Please enter starting balance", Toast.LENGTH_LONG).show()
//            }

        }


    }

//    // Function for keyboard submit
//    private fun EditText.onSubmit(func: () -> Unit) {
//        setOnEditorActionListener { _, actionId, _ ->
//
//            if (actionId == EditorInfo.IME_ACTION_DONE) {
//                func()
//            }
//            true
//
//        }
//    }

}