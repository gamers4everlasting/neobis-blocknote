package com.neobis.olenburg.blanknote.model

class DBdata {

    var id:Int?=null
    var data:String?=null
    var title:String?=null
    var date:String?=null

    constructor(fid: Int, ftitle: String, fdata:String, fdate: String){
        this.id = fid
        this.title = ftitle
        this.data = fdata
        this.date = fdate

    }

}