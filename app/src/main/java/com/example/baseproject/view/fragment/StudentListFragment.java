package com.example.baseproject.view.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.baseproject.R;
import com.example.baseproject.data.model.Student;
import com.example.baseproject.databinding.FragmentStudentListBinding;
import com.example.baseproject.view.adapter.StudentAdapter;
import com.example.baseproject.viewmodel.StudentViewModel;


public class StudentListFragment
        extends _BaseFragment<FragmentStudentListBinding, StudentViewModel>
        implements StudentAdapter.OnClickStudentItem {

    private StudentAdapter mStudentAdapter;

    @Override
    public int getFragmentView() {
        return R.layout.fragment_student_list;
    }

    @Override
    public Class getViewModelClass() {
        return StudentViewModel.class;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        requireActivity().setTitle(R.string.list_student_frag);
        setUpRcvStudentList();
        actionClickBtnToAddFragment();
    }

    private void actionClickBtnToAddFragment() {
        mBinding.btnToAddFragment.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.addStudentFragment)
        );
    }

    private void setUpRcvStudentList() {
        mBinding.rcvStudentList.setLayoutManager(
                new LinearLayoutManager(requireContext())
        );

        mStudentAdapter = new StudentAdapter(Student.DIFF_CALLBACK, this);
        mBinding.rcvStudentList.setAdapter(mStudentAdapter);
        mViewModel.loadStudentList().observe(requireActivity(), students ->
                mStudentAdapter.submitList(students)
        );
    }

    @Override
    public void onClickItem(Student student) {
        mViewModel.setCurrentStudent(student);
        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                .navigate(R.id.action_studentListFragment_to_studentDetailFragment);
    }
}