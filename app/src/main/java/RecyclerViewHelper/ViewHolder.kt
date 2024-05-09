package RecyclerViewHelper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import rodrigo.monterrosa.crud_monterrosa_2a.R

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.txtProductoCardBueno)
    val imgEditar: ImageView = view.findViewById(R.id.imgEditar)
    val imgEliminar: ImageView = view.findViewById(R.id.imgEliminar)
}