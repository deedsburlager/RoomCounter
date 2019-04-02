package com.deedsdevelopment.roomcounter

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewCounterActivity : AppCompatActivity() {

    private lateinit var editCounterView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_counter)
        editCounterView = findViewById(R.id.edit_text)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editCounterView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val counter = editCounterView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, counter)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.counterlistsql.REPLY"
    }
}
