package com.example.mula.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mula.R
import com.example.mula.adapter.AllocationAdapter
import com.example.mula.dialog.NewAllocationDialog
import com.example.mula.view_model.AllocationListViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AllocationListActivity: AppCompatActivity() {

//    private lateinit var allocationList = ArrayList<Allocation>()
    private lateinit var recyclerView:RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var allocationListViewModel: AllocationListViewModel
    private lateinit var remainingBalTextView: TextView
    private var id: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allocation_list)

        val factory = ViewModelProvider.AndroidViewModelFactory(application)
        allocationListViewModel = ViewModelProvider(this,factory).get(AllocationListViewModel::class.java)

        //Initialize UI components
        fab = findViewById(R.id.fab)
        recyclerView = findViewById(R.id.recyclerView)
        remainingBalTextView = findViewById(R.id.txtRemainingBalance)

        //Receive id from MainActivity
        id = intent.extras?.getInt("BALANCE_ID")

        if(id != null) {
            //Display Balance
            allocationListViewModel.getIncomeById(id!!).observe(this, Observer { balance ->
                remainingBalTextView.text = "$${balance.amount}"
            })

            //Display list of Allocation
            allocationListViewModel.getAllocationById(id!!).observe(this, Observer{ list ->
                recyclerView!!.layoutManager = LinearLayoutManager(this)
                recyclerView!!.itemAnimator = DefaultItemAnimator()
                // Add a neat dividing line between items in the list
                recyclerView!!.addItemDecoration(
                    DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
                )

                // set the adapter
                recyclerView!!.adapter = AllocationAdapter(list, allocationListViewModel)

            })
        }


        // Add button function
        fab.setOnClickListener { view ->
            val dialog = NewAllocationDialog(allocationListViewModel, id!!)
            dialog.show(supportFragmentManager, "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_list_display, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }



//    fun createNewAllocation(n: Allocation) {
//        // Temporary code
//        //tempNote = n
//        allocationList.add(n)
//        adapter!!.notifyDataSetChanged()
//    }

//    fun showAllocation(allocationToShow: Int) {
//        val dialog = ShowAllocationDialog()
//        dialog.sendAllocationSelected(allocationList[allocationToShow])
//        dialog.show(supportFragmentManager, "")
//    }

}


