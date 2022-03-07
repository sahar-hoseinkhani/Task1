package ir.ayantech.task.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.RecyclerView
import ir.ayantech.task.R

class ColorAdapter(private val colorList: List<String>) :
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_color, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(colorList[position])
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(color: String) {
            val colorView = itemView.findViewById(R.id.colorIv) as View
            val unwrappedDrawable =
                AppCompatResources.getDrawable(itemView.context, R.drawable.bg_color)
            val wrappedDrawable = unwrappedDrawable?.let { DrawableCompat.wrap(it) }
            wrappedDrawable?.let { DrawableCompat.setTint(it, Color.parseColor(color)) }
            colorView.background = wrappedDrawable
        }
    }
}