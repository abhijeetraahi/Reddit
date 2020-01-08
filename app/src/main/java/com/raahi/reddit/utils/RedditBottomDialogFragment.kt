package com.raahi.reddit.utils

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.raahi.reddit.R
import com.raahi.reddit.databinding.BottomSheetBinding


class RedditBottomDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    private var mListener: ItemClickListener? = null

    private lateinit var mBiding: BottomSheetBinding

    companion object {

        const val TAG = "RedditBottomDialogFragment"

        fun newInstance(): RedditBottomDialogFragment? {
            return RedditBottomDialogFragment()
        }
    }


    @Nullable
    override fun onCreateView(
        @NonNull inflater: LayoutInflater, @Nullable container: ViewGroup?,
        @Nullable savedInstanceState: Bundle?
    ): View? {
        mBiding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet, container, false)
        return mBiding.root
    }

    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBiding.tvBest.setOnClickListener(this)
        mBiding.tvHot.setOnClickListener(this)
        mBiding.tvNew.setOnClickListener(this)
        mBiding.tvTop.setOnClickListener(this)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mListener = if (context is ItemClickListener) {
            context
        } else {
            throw RuntimeException(
                "$context must implement ItemClickListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onClick(view: View) {
        val tvSelected = view as TextView
        mListener!!.onItemClick(tvSelected.text.toString())
        dismiss()
    }

    interface ItemClickListener {
        fun onItemClick(item: String?)
    }
}