package id.refactory.fakeuser.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import id.refactory.fakeuser.R
import id.refactory.fakeuser.databinding.FragmentRegisterBinding
import id.refactory.fakeuser.extensions.isEmail
import id.refactory.fakeuser.extensions.isName
import id.refactory.fakeuser.extensions.isPassword
import id.refactory.fakeuser.localstorage.LocalStorage

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentRegisterBinding
    private val localStorage by lazy { LocalStorage(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false).apply {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
            }

            btnRegister.setOnClickListener {
                validateData()
            }
        }

        return binding.root
    }

    private fun validateData() {
        val name = binding.tieName.text.toString()
        val email = binding.tieEmail.text.toString()
        val password = binding.tiePassword.text.toString()

        when {
            name.isName() && email.isEmail() && password.isPassword() -> {
                localStorage.saveName(name)
                localStorage.saveEmail(email)
                localStorage.savePassword(password)
                Toast.makeText(requireContext(), "Berhasil mendaftar", Toast.LENGTH_SHORT).show()

                findNavController().navigate(R.id.action_registerFragment_to_loginFragment2)
            }
            else -> {
                Toast.makeText(requireContext(), "Semua kolom wajib diisi", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}