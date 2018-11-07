package com.neobis.olenburg.blanknote

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.content.Intent
import android.util.Log
import android.widget.AdapterView
import android.widget.Toast;
import com.neobis.olenburg.blanknote.Date.myDate
import com.neobis.olenburg.blanknote.dbHelper.DatabaseHandler
import com.neobis.olenburg.blanknote.model.DBdata
import com.neobis.olenburg.blanknote.ui.DataManager
import com.neobis.olenburg.blanknote.ui.NoteAdapter

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var data = ArrayList<DBdata>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onResume() {
        super.onResume()

        val DB: DatabaseHandler = DatabaseHandler(this);
        data = DB.FetchData();

        if(data.size>0) {

            val NoteAdapterObj = NoteAdapter(this, data)
            data_list.adapter = NoteAdapterObj

            data_list.onItemClickListener = AdapterView.OnItemClickListener {
                    adapterView, view, position, id ->

                //Passing data to DataManager activity.
                val intent = Intent(this, DataManager::class.java)
                intent.putExtra("id",   data[position].id)
                intent.putExtra("title",data[position].title)
                intent.putExtra("data", data[position].data)
                intent.putExtra("date", data[position].date)

                intent.putExtra("action", "edit")
                startActivity(intent)

            }
        }else{

            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show()
        }

        add_note_btn.setOnClickListener(){
            val intent= Intent(this, DataManager::class.java)
            startActivity(intent)
        }

    }


}
