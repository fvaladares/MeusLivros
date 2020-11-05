package br.com.pitagoras.meuslivros

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class AboutDialogFragment : DialogFragment() {
    // Cria um AlertDialog
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //evento do click verifica qual botão foi clicado
        val listener = DialogInterface.OnClickListener { _, i ->
            when (i) {
                DialogInterface.BUTTON_NEGATIVE -> {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pitagoras.com.br"));
                    startActivity(intent)
                }
            }
        }
        return AlertDialog.Builder(requireContext()).setTitle(R.string.about_title)
            .setMessage(R.string.about_message).setPositiveButton(android.R.string.ok, null)
            .setNegativeButton(R.string.about_button_site, listener).create()
    }
}