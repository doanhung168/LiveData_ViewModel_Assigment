package com.example.baseproject.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baseproject.data.model.Student;
import com.example.baseproject.databinding.StudentItemBinding;

public class StudentAdapter extends ListAdapter<Student, StudentAdapter.StudentViewHolder> {

    private final OnClickStudentItem mOnClickStudentItem;

    public StudentAdapter(@NonNull DiffUtil.ItemCallback<Student> diffCallback, OnClickStudentItem onClickStudentItem) {
        super(diffCallback);
        mOnClickStudentItem = onClickStudentItem;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StudentItemBinding binding = StudentItemBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new StudentViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        Student student = getItem(position);
        if (student != null) {
            holder.mBinding.setStudent(student);
            holder.mBinding.setOnClickItem(mOnClickStudentItem);
            holder.mBinding.executePendingBindings();
        }
    }

    public static class StudentViewHolder extends RecyclerView.ViewHolder {

        private final StudentItemBinding mBinding;

        public StudentViewHolder(@NonNull StudentItemBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }

    public interface OnClickStudentItem {
        void onClickItem(Student student);
    }
}
