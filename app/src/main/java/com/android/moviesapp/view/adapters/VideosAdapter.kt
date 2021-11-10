package com.android.moviesapp.view.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.android.moviesapp.R
import com.android.moviesapp.models.Video
import com.google.android.youtube.player.YouTubePlayerView


class VideosAdapter(
    private val videos: List<Video>?,
    var onItemClicked: (Video, YouTubePlayerView) -> Unit
)
    : RecyclerView.Adapter<VideosAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_video, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.videoTitleTV.text = videos?.get(position)?.name
        holder.itemCL.setOnClickListener {
            onItemClicked.invoke(videos!![position],holder.videoVV) }
    }

    override fun getItemCount(): Int {
        if(videos?.size==null)
            return 0
        return videos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCL:ConstraintLayout = itemView.findViewById(R.id.item_video)
        val videoTitleTV: TextView = itemView.findViewById(R.id.item_video_titleTV)
        val videoVV: YouTubePlayerView = itemView.findViewById(R.id.item_videoVV)

    }
}
