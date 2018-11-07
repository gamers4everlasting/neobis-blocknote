package com.neobis.olenburg.blanknote.Date

import java.text.SimpleDateFormat
import java.util.*

class myDate {
    companion object {
        var date: String ? = null


        fun getCurDate(): String{
            update();
            return date!!
        }

        fun update(){
            val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
            val currentDate = sdf.format(Date())
            this.date = currentDate
        }
    }




}