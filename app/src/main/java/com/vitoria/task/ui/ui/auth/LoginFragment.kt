package com.vitoria.task.ui.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.vitoria.task.ui.R
import com.vitoria.task.ui.databinding.FragmentLoginBinding
import com.vitoria.task.ui.databinding.FragmentRegisterBinding

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() =_binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false )
        return binding.root
    }override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListener()
    }

    private fun initListener() {
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.action_global_homeFragment)
        }
        binding.btnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding.btnRecover.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_recoverAccountFragment)
        }
    }

    private fun validateData(){
        val email = binding.editTextEmail.text.toString().trim()
        val senha =binding.editTextPassword.text.toString().trim()
        if (email.isNotBlank()){
            if (senha.isNotBlank()){
                findNavController().navigate(R.id.action_global_homeFragment)
            }else{
                Toast.makeText(requireContext(),"Preencha a senha!", Toast.LENGTH_SHORT)
            }
        }else{
            Toast.makeText(requireContext(),"Preencha seu email!", Toast.LENGTH_SHORT)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}