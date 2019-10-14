package com.example.androidtodolist.Database;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM Task")
    List<Task> getAll();

    @Query("SELECT * FROM Task WHERE Tid IN (:userIds)")
    List<Task> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Task WHERE task_name LIKE :first AND " +
            "detail LIKE :last LIMIT 1")
    Task findByName(String first, String last);

    @Insert
    void insertAll(Task... tasks);

    @Delete
    void delete(Task task);

    @Query("UPDATE Task SET task_name = :first_name WHERE Tid = :tid")
    void updateUser(int tid, String first_name);

    @Query("DELETE FROM Task")
    void deleteAll();

}
