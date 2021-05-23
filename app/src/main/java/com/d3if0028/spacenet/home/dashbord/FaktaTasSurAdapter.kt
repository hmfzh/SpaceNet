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
import com.d3if0028.spacenet.Model.FaktaTataSurya
import com.d3if0028.spacenet.Model.Solarsystem
import com.d3if0028.spacenet.R

class FaktaTasSurAdapter(private var data: ArrayList<FaktaTataSurya>,
                       private val listener: (FaktaTataSurya) -> Unit) :
        RecyclerView.Adapter<FaktaTasSurAdapter.ViewHolder>() {

    lateinit var contextAdapter : Context

    class ViewHolder (view : View): RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_title)
        private val tvContent: TextView = view.findViewById(R.id.tv_content)


        fun bindItem(data:FaktaTataSurya, listener: (FaktaTataSurya) -> Unit, context: Context){
            tvTitle.setText(data.title)
            tvContent.setText(data.desc)

            itemView.setOnClickListener {
                listener(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView : View = layoutInflater.inflate(R.layout.row_item_fakta_tatasurya,parent,false)
        return  ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(data[position],listener,contextAdapter)
    }

    override fun getItemCount(): Int = data.size


}