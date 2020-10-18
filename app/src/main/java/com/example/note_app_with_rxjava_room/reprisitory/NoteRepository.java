package com.example.note_app_with_rxjava_room.reprisitory;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.example.note_app_with_rxjava_room.DAO.NoteDao;
import com.example.note_app_with_rxjava_room.database.NoteDatabase;
import com.example.note_app_with_rxjava_room.entity.Note;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class NoteRepository {
    private NoteDatabase noteDatabase;
    private NoteDao noteDao;
    private LiveData<List<Note>> notes;
    private Context context;

    public NoteRepository(Context context) {
        noteDatabase = NoteDatabase.getDatabaseInstance(context);
        noteDao = noteDatabase.noteDao();
        notes = noteDao.getAllNotes();
        this.context = context;
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note note) {
        noteDao.insertNote(note).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(insertNoteObserver);
    }

    public void deleteNote(Note note) {
        noteDao.deleteNote(note).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteNoteObserver);
    }

    public void updateNote(Note note) {
        noteDao.updateNote(note).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(updateNoteObserver);
    }

    public void deleteAllNotes() {
        noteDao.deleteAllNotes().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(deleteAllNoteObserver);
    }

    private final CompletableObserver insertNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Toast.makeText(context, "Inserted Successfully", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Toast.makeText(context, "Fail to insert due to, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    private final CompletableObserver updateNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Toast.makeText(context, "Updated successfully", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Toast.makeText(context, "Fail to update due to, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    private final CompletableObserver deleteNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Toast.makeText(context, "Deleted successfully", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Toast.makeText(context, "Fail to delete due to, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
    private final CompletableObserver deleteAllNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Toast.makeText(context, "All notes deleted successfully", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Toast.makeText(context, "Fail to delete due to, " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
