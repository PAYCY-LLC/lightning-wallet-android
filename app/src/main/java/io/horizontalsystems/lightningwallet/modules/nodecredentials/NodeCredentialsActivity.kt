package io.horizontalsystems.lightningwallet.modules.nodecredentials

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import io.horizontalsystems.lightningwallet.QrScanActivity
import io.horizontalsystems.lightningwallet.R
import io.horizontalsystems.lightningwallet.modules.nodeconnect.NodeConnectModule


class NodeCredentialsActivity : QrScanActivity() {

    private lateinit var presenter: NodeCredentialsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = ViewModelProvider(this, NodeCredentialsModule.Factory()).get(NodeCredentialsPresenter::class.java)
        presenter.viewDidLoad()

        observeEvents()
    }

    override fun onScan(text: String) {
        presenter.onScan(text)
    }

    override fun onPaste() {
        presenter.onPaste()
    }

    override fun resetInput() {
        presenter.resetInput()
    }

    private fun observeEvents() {
        val view = presenter.view as NodeCredentialsView
        val router = presenter.router as NodeCredentialsRouter

        view.startScanner.observe(this, Observer {
            openCameraWithPermission()
        })

        view.showDescription.observe(this, Observer {
            showDescription(R.string.NodeCredentials_Description)
        })

        view.emptyClipboardError.observe(this, Observer {
            showError(R.string.NodeCredentials_EmptyClipboardError)
        })

        view.invalidAddressError.observe(this, Observer {
            showError(R.string.NodeCredentials_InvalidAddressError)
        })

        router.openConnectNode.observe(this, Observer { credentials ->
            NodeConnectModule.start(this, credentials)
        })
    }

}
