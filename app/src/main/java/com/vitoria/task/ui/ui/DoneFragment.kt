package com.vitoria.task.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.vitoria.task.ui.data.model.Status
import com.vitoria.task.ui.data.model.Task
import com.vitoria.task.ui.databinding.FragmentDoneBinding
import com.vitoria.task.ui.ui.adapter.TaskAdapter
class DoneFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewTask()
        getTask()
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
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Anterior", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
        Task(id = "0", description = "Tarefa concluída 1", Status.DONE),
        Task(id = "1", description = "Tarefa concluída 2", Status.DONE),
        Task(id = "2", description = "Tarefa concluída 3", Status.DONE),
        Task(id = "3", description = "Tarefa concluída 4", Status.DONE)
    )
        taskAdapter.submitList(taskList)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}