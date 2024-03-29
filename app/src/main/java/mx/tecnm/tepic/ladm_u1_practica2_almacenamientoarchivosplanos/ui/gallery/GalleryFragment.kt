package mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.ui.gallery

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import mx.tecnm.tepic.ladm_u1_practica2_almacenamientoarchivosplanos.databinding.FragmentGalleryBinding
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.registrar.setOnClickListener {
            registrarEnArchivo()
        }

        binding.leer.setOnClickListener {
            leerDesdeArchivo()
        }


        return root
    }

    private fun leerDesdeArchivo() {
        try {
            val archivo= InputStreamReader(requireActivity().openFileInput("archivo.txt"))
            var listaContenido = archivo.readLines()

            binding.pedido.setText(listaContenido.get(0))
            binding.ingrediente.setText(listaContenido.get(1))
            binding.nombrecliente.setText(listaContenido.get(2))
            binding.precio.setText(listaContenido.get(3))

        }catch (e: Exception){
            AlertDialog.Builder(requireContext())
                .setMessage(e.message).show()
        }
    }

    private fun registrarEnArchivo() {
        try {
            val archivo= OutputStreamWriter(requireActivity().openFileOutput("archivo.txt",0))
            var cadena = binding.pedido.text.toString()+"\n"+
                        binding.ingrediente.text.toString()+"\n"+
                        binding.nombrecliente.text.toString()+"\n"+
                        binding.precio.text.toString()
            archivo.write(cadena)
            archivo.flush()
            archivo.close()
            binding.pedido.setText("")
            binding.ingrediente.setText("")
            binding.nombrecliente.setText("")
            binding.precio.setText("")

            AlertDialog.Builder(requireContext())
                .setMessage("Se registró correctamente").show()


        }catch (e:Exception){
            AlertDialog.Builder(requireContext())
                .setMessage(e.message).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}