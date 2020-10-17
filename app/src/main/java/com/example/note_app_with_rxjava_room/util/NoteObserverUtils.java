package com.example.note_app_with_rxjava_room.util;

import android.util.Log;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class NoteObserverUtils {
    private static final String TAG = "NoteObserverUtils";
    public static final CompletableObserver insertNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Log.d(TAG, "Inserted successfully");
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d(TAG, e.getMessage());
        }
    };
    public static final CompletableObserver updateNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Log.d(TAG, "updated successfully");
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d(TAG, e.getMessage());
        }
    };
    public static final CompletableObserver deleteNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Log.d(TAG, "deleted successfully");
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d(TAG, e.getMessage());
        }
    };
    public static final CompletableObserver deleteAllNoteObserver = new CompletableObserver() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onComplete() {
            Log.d(TAG, "All notes deleted successfully");
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.d(TAG, e.getMessage());
        }
    };

}
