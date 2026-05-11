package com.vitoria.task.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.vitoria.task.ui.R
import com.vitoria.task.ui.data.model.Status
import com.vitoria.task.ui.data.model.Task
import com.vitoria.task.ui.databinding.FragmentTodoBinding
import com.vitoria.task.ui.ui.adapter.TaskAdapter
import com.vitoria.task.ui.util.FirebaseHelper
import com.vitoria.task.ui.util.FirebaseHelper.Companion.getAuth


class TodoFragment : Fragment() {
    private var _binding: FragmentTodoBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    fun getIdUSer() = getAuth().currentUser?.uid ?: ""


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTodoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reference = Firebase.database.reference
        auth = Firebase.auth

        initListeners()
        initRecyclerViewTask()
        getTask()

    }
    private fun initListeners(){
        binding.floatingActionButton2.setOnClickListener {
            findNavController().navigate((R.id.action_homeFragment_to_formTaskFragment))
        }
    }
    private fun initRecyclerViewTask() {

        taskAdapter = TaskAdapter(requireContext(),) { task, option -> optionSelected(task,option)}

        with(binding.recyclerViewTask) {

            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)

            adapter = taskAdapter
        }
    }
    private fun optionSelected(task: Task, option: Int) {

        when (option) {

            TaskAdapter.SELECT_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECT_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECT_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECT_NEXT -> {
                Toast.makeText(requireContext(), "Próximo", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun getTask() {
        val taskList = listOf(
        Task("0", "Criar nova tela do app", Status.TODO),
        Task("1", "Validar informações na tela de login", Status.TODO),
        Task("2", "Salvar token localmente", Status.TODO),
        Task("3", "Criar funcionalidade de logout no app", Status.TODO),
        Task( "4", "Adicionar nova funcionalidade no app", Status.TODO),
    )
        taskAdapter.submitList(taskList)
    }

    private fun deleteTask( task: Task){
        FirebaseHelper.getDatabase()
            .child("task")
            .child(FirebaseHelper.getIdUSer()
            .child(task.id)
            .removeValue().addOnCompleteListener { result ->
                if (result.isSuccessful){
                    Toast.makeText(requireContext(), R.string.text_delete_sucess_task, Toast.LENGTH_SHORT).show()
                    val oldList = taskAdapter.currentList
                    val newList = oldList.toMutableList().apply { remove(task) }
                    taskAdapter.submitList(newList)
                }else{
                    Toast.makeText(requireContext(), R.string.error_generic, Toast.LENGTH_SHORT).show()

                }
            }
    }


    private fun updateTask(task: Task){

    }


    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }


}