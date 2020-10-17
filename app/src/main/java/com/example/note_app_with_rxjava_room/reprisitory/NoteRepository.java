package com.example.note_app_with_rxjava_room.reprisitory;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.note_app_with_rxjava_room.DAO.NoteDao;
import com.example.note_app_with_rxjava_room.database.NoteDatabase;
import com.example.note_app_with_rxjava_room.entity.Note;
import com.example.note_app_with_rxjava_room.util.NoteObserverUtils;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
    private NoteDatabase noteDatabase;
    private NoteDao noteDao;
    private LiveData<List<Note>> notes;

    public NoteRepository(Context context) {
        noteDatabase = NoteDatabase.getDatabaseInstance(context);
        noteDao = noteDatabase.noteDao();
        notes = noteDao.getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note note) {
        noteDao.insertNote(note).subscribeOn(Schedulers.io())
                .subscribe(NoteObserverUtils.insertNoteObserver);
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note).subscribeOn(Schedulers.io())
                .subscribe(NoteObserverUtils.deleteNoteObserver);
    }

    public void updateNote(Note note) {
        noteDao.updateNote(note).subscribeOn(Schedulers.io())
                .subscribe(NoteObserverUtils.updateNoteObserver);
    }

    public void deleteAllNotes() {
        noteDao.deleteAllNotes().subscribeOn(Schedulers.io())
                .subscribe(NoteObserverUtils.deleteAllNoteObserver);
    }
}
