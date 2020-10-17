package com.example.note_app_with_rxjava_room.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_app_with_rxjava_room.clickListeners.OnNoteClickListener;
import com.example.note_app_with_rxjava_room.databinding.NoteItemBinding;
import com.example.note_app_with_rxjava_room.entity.Note;

import java.util.List;


public class NoteAdapter extends ListAdapter<Note, NoteAdapter.NoteViewHolder> {
    private final OnNoteClickListener onNoteClickListener;
    private static final DiffUtil.ItemCallback<Note> diffCallback = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.equals(newItem);
        }
    };

    public NoteAdapter(OnNoteClickListener onNoteClickListener) {
        super(diffCallback);
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NoteItemBinding binding = NoteItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new NoteViewHolder(binding, onNoteClickListener, getCurrentList());
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = getItem(position);
        if (note != null) {
            holder.bindData(note);
        }
    }
    public Note getNote(int position){
        return getItem(position);
    }
    public static class NoteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final NoteItemBinding binding;
        private final OnNoteClickListener onNoteClickListener;
        private final List<Note> notes;

        private NoteViewHolder(NoteItemBinding binding, OnNoteClickListener onNoteClickListener, List<Note> notes) {
            super(binding.getRoot());
            this.binding = binding;
            this.onNoteClickListener = onNoteClickListener;
            this.notes = notes;
            binding.getRoot().setOnClickListener(this);
        }

        public void bindData(Note note) {
            binding.noteDescriptionTv.setText(note.getBody());
            binding.noteTitleTv.setText(note.getTitle());
            binding.notePriorityTv.setText(String.valueOf(note.getPriority()));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                onNoteClickListener.onClick(notes.get(position));
            }
        }
    }
}
