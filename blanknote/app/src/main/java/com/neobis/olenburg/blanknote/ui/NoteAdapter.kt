package com.neobis.olenburg.blanknote.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.neobis.olenburg.blanknote.R
import com.neobis.olenburg.blanknote.model.DBdata
import kotlinx.android.synthetic.main.cell_data.view.*

class NoteAdapter(con: Context, arrayList: ArrayList<DBdata>): BaseAdapter() {
    var arrayList = ArrayList<DBdata>()
    var context: Context? = null
    var myInflater: LayoutInflater? = null

    init {
        this.context    = con
        this.myInflater = LayoutInflater.from(context)
        this.arrayList  = arrayList
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var myView = myInflater!!.inflate(R.layout.cell_data,null)
        var ConObj = arrayList[position]
        var title : String = ConObj.title.toString()
        val date : String = ConObj.date.toString()
        myView.list_d.text = title
        myView.date.text = date
        return myView
    }

    override fun getItem(position: Int): Any {
            return  arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return arrayList.size
    }

}