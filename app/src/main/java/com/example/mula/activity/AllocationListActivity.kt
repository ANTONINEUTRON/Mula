package com.example.mula.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mula.R
import com.example.mula.data.AllocationAdapter
import com.example.mula.data.DataManager
import com.example.mula.model.Allocation
import com.example.mula.ui.NewAllocationDialog
import com.example.mula.ui.ShowAllocationDialog
import kotlinx.android.synthetic.main.activity_allocation_list.*


class AllocationListActivity: AppCompatActivity() {

    private var allocationList = ArrayList<Allocation>()
    private var recyclerView:RecyclerView? = null
    private var adapter: AllocationAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allocation_list)


        // Database and UI code goes here todo: Convert to DataManager function
        val dm = DataManager(this)
        val remainingBalance = findViewById<EditText>(R.id.txtRemainingBalance) as TextView

        // Create and initialize a Cursor with all the results in
        val c = dm.selectAll()

        //A String to hold all the text
        var list = ""

        //Loop through the results in the Cursor
        while (c.moveToNext()) {
            //Add results to String
            //with a formatting
            list += c.getString(1)
        }

        //Display the string in the TextView
        remainingBalance.text = "$" + list //<- FIX WARNING

        // Add button function
        fab.setOnClickListener { view ->
            val dialog = NewAllocationDialog()
            dialog.show(supportFragmentManager, "")

        }

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView

        adapter = AllocationAdapter(this, allocationList)
        val layoutManager = LinearLayoutManager(applicationContext)

        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()

        // Add a neat dividing line between items in the list
        recyclerView!!.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

        // set the adapter
        recyclerView!!.adapter = adapter

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



    fun createNewAllocation(n: Allocation) {
        // Temporary code
        //tempNote = n
        allocationList.add(n)
        adapter!!.notifyDataSetChanged()
    }

    fun showAllocation(allocationToShow: Int) {
        val dialog = ShowAllocationDialog()
        dialog.sendAllocationSelected(allocationList[allocationToShow])
        dialog.show(supportFragmentManager, "")
    }

}


