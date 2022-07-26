package com.example.baseproject.viewmodel;

import android.app.Application;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.baseproject.data.database.AppDatabase;
import com.example.baseproject.data.model.Student;
import com.example.baseproject.util.event.Event;
import com.example.baseproject.util.event.SingleLiveEvent;

public class AddStudentViewModel extends AndroidViewModel {

    private final Application mApplication;
    public final MutableLiveData<String> mName = new MutableLiveData<>();
    public final MutableLiveData<String> mAge = new MutableLiveData<>();
    private final SingleLiveEvent<Event> mEvent = new SingleLiveEvent<>();

    public SingleLiveEvent<Event> getEvent() {
        return mEvent;
    }

    public AddStudentViewModel(@NonNull Application application) {
        super(application);
        mApplication = application;
    }

    public void addStudent() {
        if (!checkNameInput(mName.getValue())) {
            mEvent.setValue(Event.INVALIDATE_NAME_INPUT);
            return;
        }

        if (!checkAgeInput(mAge.getValue())) {
            mEvent.setValue(Event.INVALIDATE_AGE_INPUT);
            return;
        }

        Student student = new Student(mName.getValue(), mAge.getValue());

        long result = AppDatabase.getInstance(mApplication.getApplicationContext())
                .getDao()
                .insertStudent(student);

        if (result > 0) {
            mEvent.setValue(Event.ADD_STUDENT_SUCCESS);
        } else {
            mEvent.setValue(Event.ADD_STUDENT_FAIL);
        }
    }

    public void resetInput() {
        mName.setValue("");
        mAge.setValue("");
    }

    private boolean checkNameInput(String name) {
        return !TextUtils.isEmpty(name);
    }

    private boolean checkAgeInput(String age) {
        if (TextUtils.isEmpty(age)) {
            return false;
        }
        return Integer.parseInt(age) > 0 && Integer.parseInt(age) < 150;
    }


}
