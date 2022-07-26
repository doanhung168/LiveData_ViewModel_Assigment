package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.example.baseproject.R;
import com.example.baseproject.databinding.FragmentAddStudentBinding;
import com.example.baseproject.util.CommonUtil;
import com.example.baseproject.viewmodel.AddStudentViewModel;


public class AddStudentFragment extends _BaseFragment<FragmentAddStudentBinding, AddStudentViewModel> {

    @Override
    public int getFragmentView() {
        return R.layout.fragment_add_student;
    }

    @Override
    public Class getViewModelClass() {
        return AddStudentViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(R.string.add_student_frag);

        mViewModel.resetInput();
        mBinding.setViewModel(mViewModel);


        mBinding.btnCancel.setOnClickListener(v -> {
            Navigation.findNavController(view).popBackStack();
        });


        mViewModel.getEvent().observe(getViewLifecycleOwner(), event -> {
            CommonUtil.hideKeyboard(requireActivity());
            switch (event) {
                case ADD_STUDENT_FAIL:
                    Toast.makeText(requireContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
                    break;
                case ADD_STUDENT_SUCCESS:
                    Navigation.findNavController(view).popBackStack();
                    break;
                case INVALIDATE_NAME_INPUT:
                    Toast.makeText(requireContext(), getString(R.string.invalidate_name_message), Toast.LENGTH_SHORT).show();
                    break;
                case INVALIDATE_AGE_INPUT:
                    Toast.makeText(requireContext(), getString(R.string.invalidate_age_message), Toast.LENGTH_SHORT).show();
                    break;
            }
        });

    }
}