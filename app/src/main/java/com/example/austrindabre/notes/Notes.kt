package com.example.austrindabre.notes

import android.support.v7.widget.DialogTitle
import java.io.Serializable

class Notes(val id:String,val title:String,val description:String):Serializable{
   constructor():this("","",""){}
}