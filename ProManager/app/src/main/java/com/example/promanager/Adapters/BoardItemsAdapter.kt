package com.example.promanager.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.promanager.R
import com.example.promanager.module.Board

class BoardItemsAdapter(private var context: Context,
                        private var List:ArrayList<Board>
                        ):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onClickListener:OnClickListener?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_board,parent,false))
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = List[position]
        if(holder is MyViewHolder)
        {
            Glide
                .with(context)
                .load(model.image)
                .circleCrop()
                .placeholder(R.drawable.ic_board_place_holder)
                .into(holder.itemView.findViewById(R.id.iv_board_image))

            holder.itemView.findViewById<TextView>(R.id.tv_name).text=model.name
            holder.itemView.findViewById<TextView>(R.id.tv_created_by).text= "Created By: ${model.createdBy}"

            holder.itemView.setOnClickListener{
                if(onClickListener!=null)
                {
                    onClickListener!!.onClick(position,model)
                }
            }
        }
    }

    interface OnClickListener{
        fun onClick(position: Int,model:Board)
    }
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener=onClickListener
    }
    private class MyViewHolder(view: View):RecyclerView.ViewHolder(view){

    }
}




