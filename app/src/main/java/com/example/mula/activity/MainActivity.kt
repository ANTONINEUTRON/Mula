package com.example.mula.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.mula.R
import com.example.mula.adapter.IncomeListAdapter
import com.example.mula.data.model.Income
import com.example.mula.dialog.AddIncomeDialogFragment
import com.example.mula.view_model.MainActivityViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
//    var dm: DataManager? = null
//    var progressDialog: ProgressDialog? = null

    private lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var fab: FloatingActionButton
    private lateinit var balanceRecyclerView: RecyclerView
    private lateinit var noBalanceTextView: TextView
    private lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialize view model
        val factory = ViewModelProvider.AndroidViewModelFactory(application)
        mainActivityViewModel = ViewModelProvider(this,factory).get(MainActivityViewModel::class.java)

        //Initialize UI Components
        fab = findViewById(R.id.fab)
        noBalanceTextView = findViewById(R.id.no_balance_text)
        swipeLayout = findViewById(R.id.swipe_layout)

        //initialize Recyclerview
        balanceRecyclerView = findViewById(R.id.balance_list)
        //Listen to income entity Show All Balance on the recyclerview
        mainActivityViewModel.getAllIncome().observe(this, Observer { list ->
            if(list.isNotEmpty()) {
                noBalanceTextView.visibility = View.GONE
                balanceRecyclerView.adapter = IncomeListAdapter(list as MutableList<Income>)
                balanceRecyclerView.layoutManager = LinearLayoutManager(this)
                balanceRecyclerView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))
            }else{
                noBalanceTextView.visibility = View.VISIBLE
            }
        })

        fab.setOnClickListener {
//            it.visibility = View.GONE
            //Make bottom bar visible
            AddIncomeDialogFragment.newInstance(supportFragmentManager, "ADD INCOME")
        }

        swipeLayout.setOnRefreshListener {
            Toast.makeText(this, "Refresh Called! \n ADs and promo can be shown", Toast.LENGTH_LONG).show()

            val currTime = System.currentTimeMillis()
            swipeLayout.isRefreshing = false
        }
    }

}