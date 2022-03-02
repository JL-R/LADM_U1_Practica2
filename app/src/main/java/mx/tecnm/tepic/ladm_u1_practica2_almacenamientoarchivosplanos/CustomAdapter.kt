package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CustomAdapter(private val data:List<Data>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>(){
        /*val titles= arrayOf("codelia",
            "suscribete",
            "videos",
            "youtube")


        val details= arrayOf("muchos videos nuevos",
            "kotlin",
            "mas videos",
            "gran cantidad de videos")
        */
    /*
        val images = intArrayOf(R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
            R.drawable.ic_launcher_foreground,
        )*/

        override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
            val v= LayoutInflater.from(viewGroup.context).inflate(R.layout.card_layout, viewGroup, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
            val item= data[i]
            viewHolder.render(item)
        }

        override fun getItemCount(): Int {
            return data.size
        }
        inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var itemImage = itemView.findViewById<ImageView>(R.id.item_image)
            var itemNombre = itemView.findViewById<TextView>(R.id.item_nombre)
            var itemCliente = itemView.findViewById<TextView>(R.id.item_cliente)
            var itemPrecio = itemView.findViewById<TextView>(R.id.item_precio)
            var itemIngredientes = itemView.findViewById<TextView>(R.id.item_ingredientes)

            fun render(textos: Data) {
                itemNombre.text = textos.nombre
                itemCliente.text = textos.cliente
                itemIngredientes.text = textos.ingredientes
                itemPrecio.text = textos.precio.toString()
            }
        }
}
