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
import com.vitoria.task.ui.databinding.FragmentDoingBinding
import com.vitoria.task.ui.ui.adapter.TaskAdapter
class DoingFragment : Fragment() {

    private lateinit var taskAdapter: TaskAdapter
    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerViewTask()
        getTask()
    }

    private fun initRecyclerViewTask() {
        taskAdapter = TaskAdapter(requireContext()) { task, option -> optionSelected(task,option)}
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
            TaskAdapter.SELECT_BACK -> {
                Toast.makeText(requireContext(), "Anterior", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getTask() {
        val taskList = listOf(
        Task(id = "0", description = "Fazendo tarefa 1", Status.DOING),
        Task(id = "1", description = "Fazendo tarefa 2", Status.DOING),
        Task(id = "2", description = "Fazendo tarefa 3", Status.DOING),
        Task(id = "3", description = "Fazendo tarefa 4", Status.DOING)
    )
        taskAdapter.submitList(taskList)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}