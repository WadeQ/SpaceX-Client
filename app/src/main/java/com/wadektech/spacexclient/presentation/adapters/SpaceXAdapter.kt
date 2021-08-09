package com.wadektech.spacexclient.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wadektech.spacexclient.R
import com.wadektech.spacexclient.data.local.models.SpaceXLocalItem
import com.wadektech.spacexclient.databinding.LaunchesListItemsBinding


class SpaceXAdapter(var context: Context
//                    private var singleLaunchItemClicked: OnSingleLaunchItemClicked
                    ) :
        ListAdapter<SpaceXLocalItem, SpaceXAdapter.ViewHolder>(LaunchesDiffUtil()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder.from(parent)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val launches = getItem(position)
            if (launches != null){
                holder.bind(
                    launches
//                    singleLaunchItemClicked
                )
            }

            when(launches.launchSuccess){
                true -> holder.binding.ivSuccess.setImageResource(R.drawable.success)
                false -> holder.binding.ivSuccess.setImageResource(R.drawable.cross)
            }

            Glide.with(context)
                .load(launches.linksLocal?.missionPatchSmall)
                .into(holder.binding.ivMissionPatch)
        }

        class ViewHolder private constructor(val binding: LaunchesListItemsBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(launches: SpaceXLocalItem
//                     singleLaunchItemClicked: OnSingleLaunchItemClicked
            ){
                binding.apply {
                    tvMissionName.text = launches.missionName
                    tvLaunchDate.text = launches.launchDateLocal
                    tvRocketName.text = launches
                        .rocket?.rocketName + "/"+ launches.rocket?.rocketType
                    tvDaysSince.text = launches.launchYear
                }
                itemView.setOnClickListener {
//                    singleLaunchItemClicked.onSingleEntryItemClicked(adapterPosition)
                }
            }

            companion object {
                fun from(parent: ViewGroup): ViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = LaunchesListItemsBinding
                        .inflate(layoutInflater, parent, false)
                    return ViewHolder(binding)
                }
            }
        }

    interface OnSingleLaunchItemClicked {
        fun onSingleEntryItemClicked(position: Int)
    }

        class LaunchesDiffUtil : DiffUtil.ItemCallback<SpaceXLocalItem>(){
            override fun areItemsTheSame(oldItem: SpaceXLocalItem, newItem: SpaceXLocalItem): Boolean {
                return oldItem.flightNumber == newItem.flightNumber
            }
            override fun areContentsTheSame(oldItem: SpaceXLocalItem, newItem: SpaceXLocalItem): Boolean {
                return oldItem == newItem
            }
        }
}