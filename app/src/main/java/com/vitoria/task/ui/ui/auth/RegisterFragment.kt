package com.vitoria.task.ui.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.vitoria.task.ui.R
import com.vitoria.task.ui.databinding.FragmentRegisterBinding
import com.vitoria.task.ui.util.FirebaseHelper
import com.vitoria.task.ui.util.hideKeyboard
import com.vitoria.task.ui.util.initToolbar
import com.vitoria.task.ui.util.showBottomSheet


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false )
        return binding.root
    }
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(binding.toolbar)
        initListener()
    }
    private fun initListener(){
        binding.buttonRegister.setOnClickListener{
            validateData()
        }
    }



    private fun validateData(){
        val email = binding.editTextEmail.text.toString().trim()
        val senha = binding.editTextPassword.text.toString().trim()
        if (email.isNotBlank()){
            if (senha.isNotBlank()){
                hideKeyboard()

                binding.progressBar.isVisible = true
                registerUser(email, senha)
            }else{
                showBottomSheet(message = getString(R.string.password_empty_register_fragment))
            }
        }else{
            showBottomSheet(message = getString(R.string.password_empty_register_fragment))

        }
    }

    private fun registerUser(email: String, password: String){
        try {
            val auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        //Encaminha para a tela home
                        findNavController().navigate(R.id.action_global_homeFragment)
                    }else{
                        binding.progressBar.isVisible = false
                        showBottomSheet(message = getString(FirebaseHelper.validError(task.exception?.message.toString())))
                    }

                }

        }catch (e: Exception){
            Toast.makeText(requireContext(), e.message.toString(), Toast.LENGTH_SHORT).show()
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}