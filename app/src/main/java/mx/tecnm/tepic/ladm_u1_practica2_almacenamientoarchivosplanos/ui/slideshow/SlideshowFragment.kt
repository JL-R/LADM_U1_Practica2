package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.slideshow

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.CustomAdapter
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.Data
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.R
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentSlideshowBinding
import java.io.BufferedReader
import java.io.InputStreamReader

class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val vector = ArrayList<Data>()
    var textos = ArrayList<String>()
    val textosList = ArrayList<String>()
    var aux : List<String> = listOf("nombre","cliente","ingredientes","0")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        leer()
        val recyclerView = androidx.recyclerview.widget.RecyclerView(requireContext())
        val adapter = CustomAdapter(vector)

        recyclerView.layoutManager= LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter



        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun leer() {
        try {
            textos.clear()
            val archivo = BufferedReader(InputStreamReader(context?.openFileInput("archivo.txt")))
            val br = BufferedReader(archivo)

            var linea = br.readLine()
            val cadena = StringBuilder()
            while (linea != null) {
                cadena.append(linea + "\n")
                linea = br.readLine()
            }
            br.close()
            archivo.close()

            var separar = cadena.split("\n")
            var total = separar.size-1

            (0..total).forEach {
                textos.add(separar[it])
            }

            var index = textos.size-1
            textos.removeAt(index)
            //Hasta aqui el txt ya esta en una lista pero aun los valores no estan separados por parametro

            (0..textos.size-1).forEach {
                textosList.add(textos[it].split(",").toString())
            }

            //Filtrado de cadena

            (0..textosList.size-1).forEach {
                textosList[it] = textosList.get(it).toString().replace("[","")
                textosList[it] = textosList.get(it).toString().replace("]","")

                aux = textosList.get(it).split(",")

                var nombre = aux[0].toString().trim()
                var cliente = aux[1].toString().trim()
                var ingredientes = aux[2].toString().trim()
                var precio = aux[3].toString().trim().toInt()

                var objetoDatos = Data()
                objetoDatos.nombre = nombre
                objetoDatos.cliente = cliente
                objetoDatos.ingredientes = ingredientes
                objetoDatos.precio = precio


                vector.add(objetoDatos)
            }
        } catch (e:Exception) {
            AlertDialog.Builder(requireContext())
                .setTitle("Error")
                .setMessage(e.message)
                .setNeutralButton("ACEPTAR", {d,i->
                    d.dismiss()
                })
                .show()
        }
    }

}