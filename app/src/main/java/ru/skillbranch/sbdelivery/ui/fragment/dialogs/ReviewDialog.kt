package ru.skillbranch.sbdelivery.ui.fragment.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.android.synthetic.main.dialog_review.*
import ru.skillbranch.sbdelivery.R

class ReviewDialogFragment : DialogFragment() {

    private val viewModel: DialogReviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.dialog_review, container)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btnReviewClose.setOnClickListener {
            this.dismiss()
        }

        btnReviewSend.setOnClickListener {
            viewModel.handleSendButtonPress()
            this.dismiss()
        }

        ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            viewModel.handleRatingChange(rating)
        }

        viewModel.rating.observe(viewLifecycleOwner, { setupSendButton(it) })

        //запрещает закрытие диалога при клике за его пределами
        dialog?.setCanceledOnTouchOutside(false)
    }

    private fun setupSendButton(rating: Float) {
        if (rating == 0F) {
            with(btnReviewSend) {
                isClickable = false
                alpha = 0.6F
            }
        } else {
            with(btnReviewSend) {
                isClickable = true
                alpha = 1F
            }
        }
    }

}

class DialogReviewViewModel : ViewModel() {
    val rating = MutableLiveData(0F)

    fun handleSendButtonPress() {
        rating.value = 0F
    }

    fun handleRatingChange(rating: Float) {
        this.rating.value = rating
    }

}