package com.vitoria.task.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.vitoria.task.ui.R
import com.vitoria.task.ui.databinding.FragmentHomeBinding
import com.vitoria.task.ui.ui.adapter.ViewPagerAdapter
import androidx.navigation.fragment.findNavController
import com.vitoria.task.ui.util.FirebaseHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniTabs()
        initListeners()
    }
    private fun initListeners() {
        binding.btnLogout.setOnClickListener {
            FirebaseHelper.getAuth().signOut()
            findNavController().navigate(R.id.action_global_loginFragment)
        }
    }

    private fun iniTabs() {
        val pageAdapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = pageAdapter
        pageAdapter.addFragment(TodoFragment(), R.string.status_task_todo)
        pageAdapter.addFragment(DoingFragment(), R.string.status_task_doing)
        pageAdapter.addFragment(DoneFragment(), R.string.status_task_done)

        binding.viewPager.offscreenPageLimit= pageAdapter.itemCount

        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = getString(pageAdapter.getTitle(position))
        }.attach()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
