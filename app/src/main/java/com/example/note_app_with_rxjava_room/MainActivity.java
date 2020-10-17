package com.example.note_app_with_rxjava_room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.note_app_with_rxjava_room.adapter.NoteAdapter;
import com.example.note_app_with_rxjava_room.clickListeners.OnNoteClickListener;
import com.example.note_app_with_rxjava_room.databinding.ActivityMainBinding;
import com.example.note_app_with_rxjava_room.entity.Note;
import com.example.note_app_with_rxjava_room.viewModel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnNoteClickListener {
    private ActivityMainBinding binding;
    private NoteViewModel noteViewModel;
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        noteViewModel = new ViewModelProvider(this).get(NoteViewModel.class);
        setAddNoteFab();
        setNoteAdapter();
        setNoteRecyclerView();
    }

    private void setNoteAdapter() {
        noteAdapter = new NoteAdapter(this);
        noteViewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                noteAdapter.submitList(notes);
            }
        });
    }

    private void setNoteRecyclerView() {
        binding.notesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.notesRecyclerView.setHasFixedSize(true);
        binding.notesRecyclerView.setAdapter(noteAdapter);
    }

    private void setAddNoteFab() {
        binding.addNoteFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, NoteDetailActivity.class));
            }
        });
    }

    @Override
    public void onClick(Note note) {
        Intent intent = new Intent(this, NoteDetailActivity.class);
        intent.putExtra("note", note);
        startActivity(intent);
    }
}