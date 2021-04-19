package com.example.homework4

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class DreamListAdapter: ListAdapter<Dream, DreamListAdapter.DreamViewHolder>(DreamComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {

        val currentDream=getItem(position)

        holder.bindText((position+1).toString()+ " " + currentDream.title , holder.dreamTextView)
        holder.dreamTextView.setOnClickListener {
        val context=holder.dreamTextView.context

            val intent =Intent(context,DisplayDream::class.java)

            intent.putExtra("id", currentDream.id)
//            intent.putExtra("title", currentDream.title)
//            intent.putExtra("content", currentDream.content)
//            intent.putExtra("reflection", currentDream.reflection)
//            intent.putExtra("emotion", currentDream.emotion)

            context.startActivity(intent)
        }
    }
    class DreamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val dreamTextView: TextView = itemView.findViewById(R.id.textView_item)

        fun bindText(text:String?,textView: TextView){
            textView.text=text;
        }
        companion object{
            fun create(parent:ViewGroup):DreamViewHolder{
                val view=
                    LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
                return DreamViewHolder(view);
            }
        }
    }

    class DreamComparator: DiffUtil.ItemCallback<Dream>(){
        override fun areContentsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem.title==newItem.title
        }

        override fun areItemsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem===newItem
        }
    }
}