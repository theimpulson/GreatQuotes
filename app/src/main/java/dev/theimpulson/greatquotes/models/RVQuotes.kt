package dev.theimpulson.greatquotes.models

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
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
        val currentText = quotes[position].text

        holder.binding.tvQuote.text = currentText

        holder.binding.btCopy.setOnClickListener {
            val clipboard = it.context.getSystemService(ClipboardManager::class.java)
            val clip = ClipData.newPlainText(quotes[position].toString(), currentText)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(it.context, "Successfully Copied!", Toast.LENGTH_SHORT).show()
        }

        holder.binding.btShare.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, currentText)
                type = "text/plain"
            }
            it.context.startActivity(Intent.createChooser(sendIntent, "Quote"))
        }
    }

    override fun getItemCount(): Int {
        return quotes.size
    }
}