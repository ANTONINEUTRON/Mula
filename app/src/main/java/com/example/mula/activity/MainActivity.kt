package com.example.mula.activity

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.example.mula.R
import com.example.mula.data.DataManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var dm: DataManager? = null
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Database and UI code goes here
        dm = DataManager(this)

        //Saving alert dialog
        progressDialog = ProgressDialog(this)

        val enterBalance = findViewById<EditText>(R.id.enterBalanceId)

        // Submit enterBalance via keyboard submit
        enterBalanceId.onSubmit { // Get text from EditText
            // com.example.mula.insert balance into database
            if (!TextUtils.isEmpty(enterBalanceId.text.toString())) {
                // com.example.mula.insert balance into database
                dm?.insert(enterBalance.text.toString())

                startActivity(Intent(this, AllocationListActivity::class.java))

            } else {
                Toast.makeText(this, "Please enter starting balance", Toast.LENGTH_LONG).show()
            }
        }
        //Submit input via submit_button // todo: Decide which submit method to use
        submit_button.setOnClickListener() {

            if (!TextUtils.isEmpty(enterBalanceId.text.toString())) {
                progressDialog!!.setMessage("Saving...")

                dm?.insert(enterBalance.text.toString())

                progressDialog!!.show()

                progressDialog!!.cancel()

                startActivity(Intent(this, AllocationListActivity::class.java))

            } else {
                Toast.makeText(this, "Please enter starting balance", Toast.LENGTH_LONG).show()
            }
        }


    }

    // Function for keyboard submit
    private fun EditText.onSubmit(func: () -> Unit) {
        setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_DONE) {
                func()
            }
            true

        }
    }

}