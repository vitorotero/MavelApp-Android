package br.com.tecapp.mavelapp.ui.base

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.tecapp.mavelapp.R

abstract class BaseActivity : AppCompatActivity(), BaseView {

    abstract var layoutRes: Int
    abstract fun initialize()
    abstract fun resume()
    abstract fun destroy()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutRes)
        initialize()
    }

    override fun onResume() {
        super.onResume()
        resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroy()
    }

    override fun showMessage(messageRes: Int, titleRes: Int?) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        titleRes?.let {
            alertDialogBuilder.setTitle(titleRes)
        } ?: run {
            alertDialogBuilder.setTitle(getString(R.string.alert_dialog_title))
        }

        alertDialogBuilder
            .setMessage(messageRes)
            .setCancelable(false)
            .setPositiveButton(android.R.string.ok) { dialog, _ ->
                dialog.cancel()
            }
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}