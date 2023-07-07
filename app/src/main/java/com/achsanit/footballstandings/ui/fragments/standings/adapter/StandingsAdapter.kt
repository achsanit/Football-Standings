package com.achsanit.footballstandings.ui.fragments.standings.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achsanit.footballstandings.data.ClubEntity
import com.achsanit.footballstandings.databinding.ItemRowStandingsBinding

class StandingsAdapter : RecyclerView.Adapter<StandingsAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRowStandingsBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: ClubEntity, position: Int) {
                with(binding) {
                    tvTitleNo.text = position.toString()
                    data.apply {
                        tvTitleClubName.text = clubName
                        tvMatch.text = match.toString()
                        tvWin.text = win.toString()
                        tvDraw.text = draw.toString()
                        tvLose.text = lose.toString()
                        tvGoalFor.text = gm.toString()
                        tvGoalAgainst.text = gk.toString()
                        tvPoint.text = point.toString()
                    }
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRowStandingsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(view)
    }

    private val listClub = ArrayList<ClubEntity>()

    fun submitData(data: List<ClubEntity>) {
        listClub.clear()
        listClub.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listClub.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listClub[position], position+1)
    }
}