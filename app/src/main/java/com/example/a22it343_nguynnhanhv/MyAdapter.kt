package com.example.a22it343_nguynnhanhv

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val context: Context,
                private var myDataList: List<Class>,
                private val longClickListener: OnItemLongClickListener) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int): Boolean
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recName: TextView = itemView.findViewById(R.id.recName)
        var recNum: TextView = itemView.findViewById(R.id.recNum)
        var recID: TextView = itemView.findViewById(R.id.recID)
        var recDep: TextView = itemView.findViewById(R.id.recDep)

        init {
            itemView.setOnLongClickListener {
                longClickListener.onItemLongClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = myDataList[position]
        holder.recName.text = currentItem.name
        holder.recNum.text = currentItem.num
        holder.recID.text = currentItem.id
        holder.recDep.text = currentItem.dep

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Detail::class.java)
            intent.putExtra("ID", currentItem.id)
            intent.putExtra("Name", currentItem.name)
            intent.putExtra("Num", currentItem.num)
            intent.putExtra("Dep", currentItem.dep)
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = myDataList.size

    @SuppressLint("NotifyDataSetChanged")
    fun searchDataList(searchList: List<Class>) {
        myDataList = searchList
        notifyDataSetChanged()
    }
}
