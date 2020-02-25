package io.horizontalsystems.lightningwallet.modules.send.inputform

import androidx.lifecycle.ViewModel

class InputFormPresenter(
    val view: InputFormModule.IView,
    val router: InputFormModule.IRouter,
    private val interactor: InputFormModule.IInteractor
) : ViewModel(), InputFormModule.IViewDelegate {

    override fun viewDidLoad() {
//        view.showDescription()
    }

}
