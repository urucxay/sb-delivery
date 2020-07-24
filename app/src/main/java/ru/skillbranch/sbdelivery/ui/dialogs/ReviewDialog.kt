package ru.skillbranch.sbdelivery.ui.dialogs

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
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

//        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
//        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme)

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

        viewModel.rating.observe(viewLifecycleOwner, Observer { setupSendButton(it) })

        if (ratingBar.rating == 0F) {
            with(btnReviewSend) {
                isClickable = false
            }
        } else {
            with(btnReviewSend) {
                isClickable = true
            }
        }

        //запрещает закрытие диалога при клике за его пределами
        this.isCancelable = false
    }

    private fun setupSendButton(rating: Float) {
        if (rating == 0F) {
            with(btnReviewSend) {
                isClickable = false
                backgroundTintList = requireContext().getColorStateList(R.color.color_background)
            }
        } else {
            with(btnReviewSend) {
                isClickable = true
                backgroundTintList = requireContext().getColorStateList(R.color.color_secondary)
            }
        }
    }

}

class DialogReviewViewModel : ViewModel() {

    val rating = MutableLiveData<Float>(0F)


    fun handleSendButtonPress() {
        rating.value = 0F
    }

    fun handleRatingChange(rating: Float) {
        this.rating.value = rating
    }

}