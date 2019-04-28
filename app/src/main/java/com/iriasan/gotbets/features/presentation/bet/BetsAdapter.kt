package com.iriasan.gotbets.features.presentation.bet

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iriasan.gotbets.R
import com.iriasan.gotbets.core.extension.inflate
import com.iriasan.gotbets.features.domain.models.BetModel
import kotlinx.android.synthetic.main.row_character_bet.view.*
import javax.inject.Inject


/**
 * Created by Iria Sanchez on 28/04/2019.
 */
class BetsAdapter @Inject constructor( val collection: MutableList<BetModel>, var clickListener: (BetModel, Int) -> Unit)
: RecyclerView.Adapter<BetsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder  =
        ViewHolder(parent.inflate(R.layout.row_character_bet))


    override fun getItemCount(): Int = collection.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(collection[position])

        holder.itemView.tvSelectAlive.setOnClickListener {
            collection[position].stateId = 1
            clickListener(collection[position], position)
            notifyDataSetChanged()
        }
        holder.itemView.tvSelectWhiteWalker.setOnClickListener {
            collection[position].stateId = 3
            clickListener(collection[position], position)
            notifyDataSetChanged()
        }
        holder.itemView.tvSelectDeath.setOnClickListener {
            collection[position].stateId = 2
            clickListener(collection[position], position)
            notifyDataSetChanged()
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(betModel: BetModel) {
            itemView.tvCharacterName.text = betModel.characterName

            when {
                betModel.stateId == 1 -> {
                    itemView.ivSelectorAlive.visibility = View.VISIBLE
                    itemView.ivSelectorWhiteWalker.visibility = View.GONE
                    itemView.ivSelectorDeath.visibility = View.GONE

                }
                betModel.stateId == 3-> {
                    itemView.ivSelectorAlive.visibility = View.GONE
                    itemView.ivSelectorWhiteWalker.visibility = View.VISIBLE
                    itemView.ivSelectorDeath.visibility = View.GONE

                }
                betModel.stateId == 2 ->{
                    itemView.ivSelectorAlive.visibility = View.GONE
                    itemView.ivSelectorWhiteWalker.visibility = View.GONE
                    itemView.ivSelectorDeath.visibility = View.VISIBLE

                }
            }
        }
    }

}