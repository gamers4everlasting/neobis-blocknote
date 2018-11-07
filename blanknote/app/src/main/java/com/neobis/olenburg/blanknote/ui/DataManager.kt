package com.neobis.olenburg.blanknote.ui

import android.content.ContentValues
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.neobis.olenburg.blanknote.Date.myDate
import com.neobis.olenburg.blanknote.MainActivity
import com.neobis.olenburg.blanknote.R
import com.neobis.olenburg.blanknote.dbHelper.DatabaseHandler
import kotlinx.android.synthetic.main.activity_data_manager.*
import java.util.*

class DataManager : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_manager)


        var record_id = intent.getIntExtra("id", 0)

        if (record_id == 0) { //Add Data

            save_btn.text = "save note"

        } else { //Update Data

            save_btn.text = "update note"
            var _fdata = intent.getStringExtra("data")
            var _ftitle = intent.getStringExtra("title")

            add_title.setText(_ftitle)
            add_note.setText(_fdata)


        }

        save_btn.setOnClickListener() {
            val random = Random()
            val t = add_title.text.toString();
            val d = add_note.text.toString();


            val values = ContentValues()
            values.put("id", random.nextInt(1000) )
            values.put("title", t)
            values.put("data", d)
            values.put("date", myDate.getCurDate())


            //Adding data
            if (record_id == 0) {

                var DB: DatabaseHandler = DatabaseHandler(this);

                var response = DB.AddData(values);
                Log.e("_____value:", values.toString())
                if (response == "ok") {

                    Toast.makeText(
                        this, "Note Added",
                        Toast.LENGTH_SHORT
                    ).show()

                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(
                        this, "Not Added..Try again",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else { //update note

                var DB: DatabaseHandler = DatabaseHandler(this);

                var res: String = DB.UpdateData(values, record_id)

                if (res == "ok") {
                    Toast.makeText(
                        this, "Note Updated",
                        Toast.LENGTH_SHORT
                    ).show()

                    var intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    Toast.makeText(
                        this, "Error..Try Again",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }

        }


        delete_btn.setOnClickListener() {

            var DB: DatabaseHandler = DatabaseHandler(this);
            var res: String = DB.RemoveData(record_id)

            if(res=="ok") {

                Toast.makeText(this, "Note Deleted",
                    Toast.LENGTH_SHORT).show()
            }else{

                Toast.makeText(this, "Error..Try Again",
                    Toast.LENGTH_SHORT).show()
            }

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}