package com.example.a22it343_nguynnhanhv

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class Activity2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText1 = view.findViewById<EditText>(R.id.editTextNumber1)
        val editText2 = view.findViewById<EditText>(R.id.editTextNumber2)
        val textView = view.findViewById<TextView>(R.id.textView)

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textView.text = ""
            }

            override fun afterTextChanged(s: Editable) {
            }
        }

        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)

        editText2.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                val a = editText1.text.toString().toDoubleOrNull()
                val b = editText2.text.toString().toDoubleOrNull()

                if (a == null || b == null) {
                    Toast.makeText(context, "Vui lòng nhập số!", Toast.LENGTH_LONG).show()
                    textView.text = ""
                } else if (a == 0.0 && b != 0.0) {
                    textView.text = "Vô nghiệm"
                } else if (a == 0.0 && b == 0.0) {
                    textView.text = "Vô số nghiệm"
                } else {
                    val result = -b / a
                    textView.text = if (result % 1 == 0.0) {
                        Html.fromHtml("<font color='#525357'>${result.toInt()}</font>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        Html.fromHtml("<font color='#525357'>$result</font>", Html.FROM_HTML_MODE_LEGACY)
                    }
                }
                return@setOnEditorActionListener true
            }
            false
        }
    }
}


