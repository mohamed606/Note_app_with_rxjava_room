package com.example.note_app_with_rxjava_room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.note_app_with_rxjava_room.databinding.ActivityNoteDetailBinding;
import com.example.note_app_with_rxjava_room.entity.Note;
import com.example.note_app_with_rxjava_room.viewModel.NoteViewModel;

public class NoteDetailActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;
    private ActivityNoteDetailBinding binding;
    private boolean isEditNote = false;
    private static final String TAG = "NoteDetailActivity";
    private int currentNoteId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_note_detail);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        setPriorityPicker();
        Intent intent = getIntent();
        if (intent != null) {
            Note note = intent.getParcelableExtra("note");
            if (note != null) {
                isEditNote = true;
                currentNoteId = note.getId();
                setViewsData(note);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.saveNote:
                saveNote();
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setPriorityPicker() {
        binding.priorityPicker.setMaxValue(1);
        binding.priorityPicker.setMaxValue(10);
    }

    private Note getEnteredNote() {
        String title = binding.noteTitleEx.getText().toString();
        String description = binding.noteDescriptionEx.getText().toString();
        int priority = binding.priorityPicker.getValue();
        if (!title.trim().isEmpty() && !description.trim().isEmpty()) {
            Note note = new Note(priority, title, description);
            if (isEditNote) {
                note.setId(currentNoteId);
            }
            return note;
        }
        return null;
    }

    private void saveNote() {
        Note note = getEnteredNote();
        if (!isEditNote) {
            saveNewNote(note);
        } else {
            Log.d(TAG, String.valueOf(note.getId()));
            updateNote(note);
        }
    }

    private void saveNewNote(Note note) {
        if (note != null) {
            noteViewModel.insertNote(note);
        }
    }

    private void updateNote(Note note) {
        noteViewModel.updateNote(note);
    }

    private void setViewsData(Note note) {
        binding.noteTitleEx.setText(note.getTitle());
        binding.noteDescriptionEx.setText(note.getBody());
        binding.priorityPicker.setValue(note.getPriority());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}