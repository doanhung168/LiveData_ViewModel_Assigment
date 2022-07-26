package com.example.baseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.baseproject.data.database.AppDatabase;
import com.example.baseproject.data.model.Student;

import java.util.List;

public class StudentViewModel extends AndroidViewModel {

    private final MutableLiveData<Student> mCurrentStudent = new MutableLiveData<>();
    private final Application mApplication;

    public StudentViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public LiveData<Student> getCurrentStudent() {
        return mCurrentStudent;
    }

    public void setCurrentStudent(Student student) {
        mCurrentStudent.setValue(student);
    }

    public LiveData<List<Student>> loadStudentList() {
        return AppDatabase.getInstance(mApplication.getApplicationContext())
                .getDao()
                .getAllStudent();
    }

}
