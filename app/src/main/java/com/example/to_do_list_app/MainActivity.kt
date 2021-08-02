package com.example.to_do_list_app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var listView:ListView
    lateinit var input:TextView
    lateinit var users:ArrayList<String>
    lateinit var arrayAdapter: ArrayAdapter<String>
    var context=this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        listView=findViewById(R.id.View)
        input=findViewById(R.id.text)
        users=ArrayList<String>()


        button.setOnClickListener {
            inputfunc(it)
        }
        arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,users)
       // arrayAdapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,users)
        listView.adapter=arrayAdapter

        listView.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->

            val li = LayoutInflater.from(context)
            val promptsView: View = li.inflate(R.layout.dialog, null)
            val alertDialogBuilder=AlertDialog.Builder(this)

            alertDialogBuilder.setView(promptsView)
            var text=promptsView.findViewById<TextView>(R.id.editview)
            text.setText(users[position])

            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("SAVE") {
                        dialog, id -> // get user input and set it to result
                        users[position]= text.getText().toString()
                        arrayAdapter.notifyDataSetChanged()
                        Toast.makeText(this,"Item Edited",Toast.LENGTH_SHORT).show();

                    }
                    .setNegativeButton("DELETE") {
                        dialog, id -> dialog.cancel()
                        arrayAdapter.notifyDataSetChanged()
                        users.removeAt(position)
                        Toast.makeText(this,"Item Removed",Toast.LENGTH_SHORT).show();
                    }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()

        })



    }
    private fun inputfunc(view: View)
    {

        var txt=input.text;
        if(txt.toString()=="")
        {
            Toast.makeText(this,"Invalid Entry",Toast.LENGTH_SHORT).show();
        }
        else
        {
            arrayAdapter.add(txt.toString())

            Toast.makeText(this,"Item Added",Toast.LENGTH_SHORT).show();
            input.setText("")
        }


    }
}