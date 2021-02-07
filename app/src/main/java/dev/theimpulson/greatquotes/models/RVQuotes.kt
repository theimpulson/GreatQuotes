package dev.theimpulson.greatquotes.models

import android.content.ClipData
import android.content.ClipboardManager
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import dev.theimpulson.greatquotes.databinding.RvQuotesBinding
import dev.theimpulson.greatquotes.network.Quotes

class RVQuotes(val quotes: ArrayList<Quotes>) : RecyclerView.Adapter<RVQuotes.ViewHolder>() {

    inner class ViewHolder(val binding: RvQuotesBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvQuotesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvQuote.text = quotes[position].text

        holder.binding.btCopy.setOnClickListener {
            val clipboard = it.context.getSystemService(ClipboardManager::class.java)
            val clip = ClipData.newPlainText(quotes[position].toString(), quotes[position].text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(it.context, "Successfully Copied!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}