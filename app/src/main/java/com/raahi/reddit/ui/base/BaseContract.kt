package com.raahi.reddit.ui.base

class BaseContract {

    interface View{
        fun showToast(message: String)
        fun showToast(message: String, length: Int)
    }

    interface Presenter<V: View>{
        fun onAttach(view: V)
        fun onDetach()
    }
}