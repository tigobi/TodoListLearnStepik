package com.example.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private Button buttonSaveNote;

    private RadioButton radioButtonLow;
    private RadioButton radioButtonMedium;
    private EditText editTextNoteText;

    private Database database = Database.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        buttonSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void initViews() {
        buttonSaveNote = findViewById(R.id.buttonSaveNote);
        radioButtonLow = findViewById(R.id.radioButtonLow);
        radioButtonMedium = findViewById(R.id.radioButtonMedium);
        editTextNoteText = findViewById(R.id.editTextNoteText);
    }

    private void saveNote() {
        if (editTextNoteText.getText().toString().trim().isEmpty()) {
            Toast.makeText(AddNoteActivity.this, getString(R.string.error_note_empty), Toast.LENGTH_SHORT).show();
        } else {
            String text = editTextNoteText.getText().toString().trim();
            int priority = getPriority();
            int id = database.getNotes().size();
            Note note = new Note(id, text, priority);
            database.add(note);
            finish();
        }
    }

    private int getPriority() {
        int priority;
        if (radioButtonLow.isChecked()) priority = 0;
        else if (radioButtonMedium.isChecked()) priority = 1;
        else priority = 2;
        int a = 0;//for changes
        return priority;


    }

    public static Intent newIntent(Context context) {
        return new Intent(context, AddNoteActivity.class);
    }
}