package com.achsanit.footballstandings.ui.fragments.dataclub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achsanit.footballstandings.data.ClubEntity
import com.achsanit.footballstandings.databinding.ItemRowDataClubBinding

class DataClubAdapter : RecyclerView.Adapter<DataClubAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemRowDataClubBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(data: ClubEntity, position: Int) {
                with(binding) {
                    tvTitleNo.text = position.toString()
                    tvTitleClubName.text = data.clubName
                    tvTitleCity.text = data.clubCity
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemRowDataClubBinding.inflate(
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