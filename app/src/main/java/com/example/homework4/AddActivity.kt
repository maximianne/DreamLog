package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class AddActivity : AppCompatActivity() {

    private lateinit var title:EditText
    private lateinit var whatHappened:EditText
    private lateinit var interpretation:EditText
    private lateinit var feeling:Spinner
    private lateinit var button: Button
    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)
        title=findViewById(R.id.editText_TitleUpdate)
        whatHappened=findViewById(R.id.editText_whatHappenedUpdate)
        interpretation=findViewById(R.id.editText_interpretationUpdate)
        val emotions = resources.getStringArray(R.array.emotion_array)
        button =findViewById(R.id.buttonUpdate)
        //Log.d("check: ",(intent.getIntExtra("id",0) == null).toString())

        if(!intent.hasExtra("idUpdate")){
            feeling = findViewById(R.id.chooseFeelingUpdate)
            if (feeling != null) {
                val adapter = ArrayAdapter(this,
                        android.R.layout.simple_spinner_item, emotions)
                feeling.adapter = adapter



                button.setOnClickListener{
                    if(TextUtils.isEmpty(title.text)
                            ||TextUtils.isEmpty(whatHappened.text)
                            ||TextUtils.isEmpty(interpretation.text)
                            ||TextUtils.isEmpty(feeling.selectedItem.toString())){
                        ToastError("Missing feilds")
                    }
                    else{
                        var dream=Dream(null,title.text.toString(),
                                whatHappened.text.toString(),
                                interpretation.text.toString(),
                                feeling.selectedItem.toString())

                        dreamViewModel.insert(dream)
                        finish()
                    }
                }
            }
        }
        else{
            val id=intent.getIntExtra("idUpdate",0)
            val compareValue = dreamViewModel.getDream(id).emotion;
            feeling = findViewById(R.id.chooseFeelingUpdate)
            val adapter = ArrayAdapter(this,
                    android.R.layout.simple_spinner_item, emotions)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            feeling.setAdapter(adapter)
            if (compareValue != null) {
                val spinnerPosition = adapter.getPosition(compareValue)
                feeling.setSelection(spinnerPosition)
            }
            title.setText( dreamViewModel.getDream(intent.getIntExtra("idUpdate",0)).title)
            whatHappened.setText( dreamViewModel.getDream(intent.getIntExtra("idUpdate",0)).content)
            interpretation.setText( dreamViewModel.getDream(intent.getIntExtra("idUpdate",0)).reflection)
            button.setOnClickListener {
                dreamViewModel.UpdateDream(id, title.text.toString(),whatHappened.text.toString(),interpretation.text.toString(),feeling.selectedItem.toString())
                intent= Intent(this,MainActivity::class.java)
                startActivity(intent)
            }
        }
        }
        private fun ToastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}

