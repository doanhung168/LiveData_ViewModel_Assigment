package com.example.baseproject.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.baseproject.data.model.Student;

import java.util.List;

@Dao
public interface StudentDAO {

    @Query("SELECT * FROM Student")
    LiveData<List<Student>> getAllStudent();

    @Insert
    long insertStudent(Student student);

    @Update
    int updateStudent(Student student);


}
