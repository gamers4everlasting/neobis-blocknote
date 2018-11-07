package com.neobis.olenburg.blanknote.dbHelper
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.Context
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log
import com.neobis.olenburg.blanknote.model.DBdata
import java.util.*
import java.util.Collections.reverse
import kotlin.collections.ArrayList

class DatabaseHandler : SQLiteOpenHelper {
    companion object {
        val Tag = "DatabaseHandler"
        val DBName = "neobisBlanknote.db"
        val DBVersion = 1
        val tableName = "noteTable"
        val id = "id"
        val title = "title"
        val data = "data"
        val date = "date"

    }
    var context: Context? = null
    var sqlObj: SQLiteDatabase
    constructor(context: Context) : super(context,
        DBName, null,
        DBVersion
    ) {
        this.context = context;
        sqlObj = this.writableDatabase;
    }
    override fun onCreate(db: SQLiteDatabase?) {
//SQL for creating table
        var sql1: String = "CREATE TABLE IF NOT EXISTS " + tableName + " " +
                "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT," + title + " TEXT,"+
                    data + " TEXT," + date +" TEXT);"
        db!!.execSQL(sql1);
    }
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("Drop table IF EXISTS " + tableName)
        onCreate(p0)
    }
    fun AddData(values: ContentValues): String {
        val db:SQLiteDatabase = this.writableDatabase

        val ID = db.insert(tableName, null, values)
        if (ID > 0) {
            return "ok"
        }
        return "error"

    }
    fun FetchData(): ArrayList<DBdata> {
        val arraylist = ArrayList<DBdata>()

//        val selectQuery = "SELECT * FROM $tableName"
//        val db:SQLiteDatabase = this.writableDatabase
//        val cur:Cursor = db.rawQuery(selectQuery, null, null, date)
        val sqb = SQLiteQueryBuilder()
        sqb.tables = tableName
        val cols = arrayOf("id", "title", "data", "date")
        val rowSelArg = arrayOf("%")

        val cur = sqb.query(sqlObj, cols, "title like ?", rowSelArg, null, null, "date")
        if (cur.moveToFirst()) {
            do {
                val dId = cur.getInt(cur.getColumnIndex("id"))
                val dTitle = cur.getString(cur.getColumnIndex("title"))
                val dData = cur.getString(cur.getColumnIndex("data"))
                val dDate = cur.getString(cur.getColumnIndex("date"))
                arraylist.add(DBdata(dId, dTitle, dData, dDate))
            } while (cur.moveToNext())
        }
        var count: Int = arraylist.size

        val tempElements = ArrayList<DBdata>(arraylist)
        Collections.reverse(tempElements)
        return tempElements
    }
    fun UpdateData(data: ContentValues, id: Int): String {
        var selectionArs = arrayOf(id.toString())
        val db:SQLiteDatabase = this.writableDatabase
        val i = db.update(tableName, data, "id=?", selectionArs)
        if (i > 0) {
            return "ok";
        } else {
            return "error";
        }
    }
    fun RemoveData(id: Int): String {
        val selectionArs = arrayOf(id.toString())
        val i = sqlObj.delete(tableName, "id=?", selectionArs)
        if (i > 0) {
            return "ok";
        } else {
            return "error";
        }
    }
}