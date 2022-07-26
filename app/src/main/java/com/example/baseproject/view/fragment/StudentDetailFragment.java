package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.baseproject.R;
import com.example.baseproject.viewmodel.StudentViewModel;
import com.example.baseproject.databinding.FragmentStudentDetailBinding;

public class StudentDetailFragment extends _BaseFragment<FragmentStudentDetailBinding, StudentViewModel> {

    @Override
    public int getFragmentView() {
        return R.layout.fragment_student_detail;
    }

    @Override
    public Class getViewModelClass() {
        return StudentViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(R.string.detain_student_frag);
        mBinding.setStudent(mViewModel.getCurrentStudent().getValue());

    }
}