package com.vitoria.task.ui.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.vitoria.task.ui.R
import com.vitoria.task.ui.databinding.BottomSheetBinding

fun Fragment.initToolbar(toolbar: Toolbar){
    (activity as AppCompatActivity).setSupportActionBar(toolbar)
    (activity as AppCompatActivity).title=""
    (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
    toolbar.setNavigationOnClickListener {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

}

fun Fragment.showBottomSheet(
    titleDialog: Int? = null,
    titleButton: Int? = null,
    message: Int,
    onClick: () -> Unit={}
    ){
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val binding: BottomSheetBinding =
            BottomSheetBinding.inflate(layoutInflater, null, false)
        binding.textviewTitle.text =getText(titleDialog ?: R.string.text_title_warning)
        binding.textviewMessage.text =getText(message)
        binding.buttonOk.text =getText(titleButton ?: R.string.text_button_warning)
        binding.buttonOk.setOnClickListener {
            onClick()
            bottomSheetDialog.dismiss()
        }

}
