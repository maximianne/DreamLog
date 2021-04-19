package com.example.homework4

import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

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
        title=findViewById(R.id.editText_Title)
        whatHappened=findViewById(R.id.editText_whatHappened)
        interpretation=findViewById(R.id.editText_interpretation)

        val emotions = resources.getStringArray(R.array.emotion_array)

        feeling = findViewById(R.id.chooseFeeling)
        if (feeling != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, emotions)
            feeling.adapter = adapter

        button =findViewById(R.id.button1)

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
    private fun ToastError(text:String){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}