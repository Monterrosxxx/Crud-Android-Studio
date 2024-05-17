package RecyclerViewHelper

import Modelo.ClaseConexion
import Modelo.dataClassProductos
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import rodrigo.monterrosa.crud_monterrosa_2a.R

class Adaptador(private var Datos: List<dataClassProductos>) : RecyclerView.Adapter<ViewHolder>() {

    fun actualizarLista(nuevaLista:List<dataClassProductos>){

        /////////TODO:IMPORTANTE - INDICA QUE HAY CAMBIO///////
        Datos = nuevaLista
        notifyDataSetChanged()

    }

    fun eliminarRegistro(nombreProducto: String, posicion:Int){



        //Quitar el elemento de la vista
        val listaDatos = Datos.toMutableList()
        listaDatos.removeAt(posicion)

        //Quitar de la base de datos
        GlobalScope.launch(Dispatchers.IO){

            //1- Crear un objeto de la clase conexion
            val objConexion = ClaseConexion().cadenaConexion()

            val deleteProducto = objConexion?.prepareStatement("delete tbProductos where nombreProducto = ?")!!
            deleteProducto.setString(1, nombreProducto)
            deleteProducto.executeUpdate()

            val commit = objConexion.prepareStatement("commit")!!
            commit.executeUpdate()


        }

        //Le decimos al adaptador que se eliminaron datos
        Datos = listaDatos.toList()
        notifyItemRemoved(posicion)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vista =
            LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)

        return ViewHolder(vista)
    }

    override fun getItemCount() = Datos.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = Datos[position]
        holder.textView.text = producto.nombreProductos

        val item = Datos[position]
        holder.imgEliminar.setOnClickListener {


            val context = holder.itemView.context
            //Creamos la alerta
            val builder = AlertDialog.Builder(context)
            //a mi alerta le creo un titulo
            builder.setTitle("¿Estas seguro maje?")
            //Ponerle un mensaje
            builder.setMessage("Ponete serio ¿lo vas a eliminar o no?")

            //PAso final, agregamos los botones
            builder.setPositiveButton("simon"){ dialog, wich ->

                eliminarRegistro(item.nombreProductos, position)
            }

            builder.setNegativeButton("Nel"){ dialog, wich ->

            }

            //Creamos la alerta
            val alertDialog = builder.create()
            //
            alertDialog.show()

        }

    }

}