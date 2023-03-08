package ivo.sociomile.android_sdk_sample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    private val imageId = arrayOf(
    R.drawable.ic_user,
    R.drawable.ic_user,
    R.drawable.ic_user,
    R.drawable.ic_user,
    R.drawable.ic_user
    )

    private val title = arrayOf(
    "Transfer to BRI",
    "Transfer to BRI",
    "Received from BNI",
    "Received from BRI",
    "Transfer to Mandiri",
    )

    private val subtitle = arrayOf(
    "Amira Leswana",
    "Dendi K.W",
    "Galandria",
    "Amira Leswana",
    "Budi Darmawan",
    )

    private val date = arrayOf(
    "3 March 2023",
    "3 March 2023",
    "2 March 2023",
    "28 February 2023",
    "15 January 2023",
    )

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.transaction_card, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

//        val ItemsViewModel = mList[position]

        // sets the image to the imageview from our itemHolder class
        holder.imageView.setImageResource(imageId[position])

        // sets the text to the textview from our itemHolder class
        holder.nameTransaction.text = title[position]
        holder.clientTransaction.text = subtitle[position]
        holder.dateTransaction.text = date[position]
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return title.size
    }

    // Holds the views for adding it to image and text
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView2)
        val nameTransaction: TextView = itemView.findViewById(R.id.name_transaction)
        val clientTransaction: TextView = itemView.findViewById(R.id.user_name_transaction)
        val dateTransaction: TextView = itemView.findViewById(R.id.date_transaction)
    }
}
