package com.ankitapi.projectgithon.helper

import android.content.Context
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

fun Context.toast(msg : String?){
    Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
}
fun Context.snackHelper(msg : String){
//    Snackbar.make(,msg,Snackbar.LENGTH_LONG).show()
}