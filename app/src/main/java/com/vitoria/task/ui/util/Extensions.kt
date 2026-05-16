package com.vitoria.task.ui.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
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
    message: String,
    onClick: () -> Unit={}
    ){
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
        val binding: BottomSheetBinding =
            BottomSheetBinding.inflate(layoutInflater, null, false)
        binding.textviewTitle.text =getText(titleDialog ?: R.string.text_title_warning)
        binding.textviewMessage.text = message
        binding.buttonOk.text =getText(titleButton ?: R.string.text_button_warning)
        binding.buttonOk.setOnClickListener {
            onClick()
            bottomSheetDialog.dismiss()
        }
    bottomSheetDialog.setContentView(binding.root)
    bottomSheetDialog.show()

}

fun Fragment.hideKeyboard() {
    val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(requireView().windowToken, 0)
}
