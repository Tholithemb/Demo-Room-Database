package com.e.demoroomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ToDoDao {


    @Insert
    void insertTodo(ToDo toDo);

    @Query("SELECT * FROM todo_table")
    List<ToDo> getAllToDos();

    @Query("SELECT * FROM todo_table WHERE todo_uid LIKE :uid")
    ToDo findTodoById(int uid);

    @Delete
    void deleteTodo(ToDo toDo);

    @Update
    void updateTodo(ToDo toDo);

    @Insert
     void insertMultipleTodos(List<ToDo> toDoList);

    @Query("SELECT * FROM todo_table WHERE todo_completed LIKE 1")// if we want true pass 1 or false pass 0
    List<ToDo> getAllCompletedTodos();


}
