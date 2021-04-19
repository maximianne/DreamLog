package com.example.homework4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class DisplayDream: AppCompatActivity() {
    private lateinit var title:TextView
    private lateinit var content:TextView
    private lateinit var interpretation:TextView
    private lateinit var emotion:TextView
    private lateinit var button_delete:Button
    private lateinit var button_update:Button
    private lateinit var dream:Dream
    private val dreamViewModel:DreamViewModel by viewModels{
        DreamViewModelFactory((application as DreamApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)
        title=findViewById(R.id.textView_dreamTitle)
        content=findViewById(R.id.textView_whatHappened)
        interpretation=findViewById(R.id.textView_interpretation)
        emotion=findViewById(R.id.textView_feeling)
        button_delete=findViewById(R.id.button_delete)
        button_update=findViewById(R.id.button_update)

        val id= intent.getIntExtra("id", 0)
        dream=dreamViewModel.getDream(id);

        title.setText(dream.title)
        content.setText(dream.content)
        interpretation.setText(dream.reflection)
        emotion.setText(dream.emotion)

        button_update.setOnClickListener {
            intent= Intent(this, AddActivity::class.java)
            intent.putExtra("idUpdate", dream.id)
            startActivity(intent)
        }
        ///RIGHT HERE TO DELETE THE DREAM
        button_delete.setOnClickListener{
            dreamViewModel.delete(id)
            finish()
        }
    }

}



