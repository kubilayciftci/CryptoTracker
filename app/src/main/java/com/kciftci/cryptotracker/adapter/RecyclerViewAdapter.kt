package com.kciftci.cryptotracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kciftci.cryptotracker.R
import com.kciftci.cryptotracker.model.CryptoModel
import kotlinx.android.synthetic.main.row_layout.view.*

class RecyclerViewAdapter(
    private val cryptoList: ArrayList<CryptoModel>,
    private val listener: Listener
) :
    RecyclerView.Adapter<RecyclerViewAdapter.RowHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_layout, parent, false)
        return RowHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoList.count()
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(cryptoModel = cryptoList[position], listener = listener)
    }

    interface Listener {
        fun onItemClickListener(cryptoModel: CryptoModel)
    }

    class RowHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(cryptoModel: CryptoModel, listener: Listener) {
            itemView.cryptoNameTextView.text = cryptoModel.name
            itemView.cryptoPriceTextView.text = cryptoModel.price.toString()
            itemView.cryptoSymbolTextView.text = cryptoModel.symbol
            itemView.setOnClickListener {
                listener.onItemClickListener(cryptoModel)
            }
        }
    }


}