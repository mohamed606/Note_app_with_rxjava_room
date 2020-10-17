package com.example.note_app_with_rxjava_room.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.note_app_with_rxjava_room.entity.Note;
import com.example.note_app_with_rxjava_room.reprisitory.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository noteRepository;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
    }
    public LiveData<List<Note>> getNotes() {
        return noteRepository.getNotes();
    }

    public void insertNote(Note note) {
        noteRepository.insertNote(note);
    }

    public void deleteNote(Note note) {
       noteRepository.deleteNote(note);
    }

    public void updateNote(Note note) {
        noteRepository.updateNote(note);
    }

    public void deleteAllNotes() {
        noteRepository.deleteAllNotes();
    }
}
