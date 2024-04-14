package com.example.a22it343_nguynnhanhv

import android.os.Bundle
import android.text.Editable
import android.text.Html
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class Activity1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_activity1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val editText = view.findViewById<EditText>(R.id.editTextNumber)
        val textView = view.findViewById<TextView>(R.id.textView)

        fun isPrime(n: Int): Boolean {
            if (n <= 1) {
                return false
            }
            for (i in 2 until n) {
                if (n % i == 0) {
                    return false
                }
            }
            return true
        }

        if (editText.text.toString().isEmpty()) {
            textView.text = ""
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textView.text = ""
            }

            override fun afterTextChanged(s: Editable) {

            }
        }

        editText.addTextChangedListener(textWatcher)

        editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                if (editText.text.toString().isEmpty()) {
                    Toast.makeText(context, "Vui lòng nhập số!", Toast.LENGTH_LONG).show()
                    textView.text = ""
                } else {
                    val number = editText.text.toString().toInt()
                    val primeNumber = isPrime(number)
                    if (primeNumber) {
                        textView.text = Html.fromHtml("<font color='#008000'>Là số nguyên tố</font>", Html.FROM_HTML_MODE_LEGACY)
                    } else {
                        textView.text = Html.fromHtml("<font color='#FF0000'>Không phải số nguyên tố</font>", Html.FROM_HTML_MODE_LEGACY)
                    }
                }
                return@setOnEditorActionListener true
            }
            false
        }
    }
}
