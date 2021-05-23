package com.d3if0028.spacenet.home.dashbord

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.d3if0028.spacenet.Learn.TataSurya
import com.d3if0028.spacenet.Model.Berita
import com.d3if0028.spacenet.Model.Solarsystem
import com.d3if0028.spacenet.R

class TataSuryaAdapter(private var data: ArrayList<Solarsystem>,
                        private val listener: (Solarsystem) -> Unit) :
    RecyclerView.Adapter<TataSuryaAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    class ViewHolder (view : View): RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)

        fun bindItem(data:Solarsystem, listener: (Solarsystem) -> Unit, context: Context){
            tvTitle.setText(data.judul)

            Glide.with(context)
                .load(data.poster)
                .into(tvImage)

            itemView.setOnClickListener {
                listener(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView : View = layoutInflater.inflate(R.layout.row_item_tatasurya,parent,false)
        return  ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter)
    }

    override fun getItemCount(): Int = data.size


}