package com.e.demoroomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.annotation.SuppressLint;
import android.content.LocusId;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  ToDoRoomDatabase.getInstance(this).toDoDao().insertTodo();

    }

    public void insertSingleTodo(View view){

        ToDo toDo =new ToDo("get me milk",false);
        toDo.setCompleted(true);
        InsertAsyncTask insertAsyncTask =new InsertAsyncTask();
        insertAsyncTask.execute(toDo);
    }
    public void getAllTodos(View view){

        Thread thread =new Thread(new Runnable() {
            @Override
            public void run() {
                List<ToDo> toDoList =ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .getAllToDos();
                Log.d(TAG, "run: "+toDoList.toString());
            }
        });
        thread.start();


    }
    public void deleteTodo(View view){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ToDo toDo =ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .findTodoById(3);
                Log.d(TAG, "run: "+toDo.toString());

                ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .deleteTodo(toDo);
                Log.d(TAG, "run: "+ "todo has been deleted");
            }
        }).start();
    }
    public void updateTodo(View view){


        new Thread(new Runnable() {
            @Override
            public void run() {
                ToDo toDo =ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .findTodoById(1);
                toDo.setCompleted(true);

                ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .updateTodo(toDo);
                Log.d(TAG, "run: "+"todo has been updated");
            }
        }).start();

    }
    public void insertMultipleTodos(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ToDo> toDoList =new ArrayList<>();
                toDoList.add(new ToDo("make video on kotlin",false));
                toDoList.add(new ToDo("watch black panther",true));
                toDoList.add(new ToDo("watch marvel movies series",true));
                toDoList.add(new ToDo("make a beginner video on python",false));


                ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .insertMultipleTodos(toDoList);
                Log.d(TAG, "run: "+"todos has been inserted....");

            }
        }).start();

    }
    public void findCompletedTodos(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {

                List<ToDo> toDoList =ToDoRoomDatabase.getInstance(getApplicationContext())
                        .toDoDao()
                        .getAllCompletedTodos();

                Log.d(TAG, "run: "+toDoList.toString());

            }
        }).start();
    }

    class InsertAsyncTask extends AsyncTask<ToDo,Void,Void> {
        @Override
        protected Void doInBackground(ToDo... toDos) {

            ToDoRoomDatabase.getInstance(getApplicationContext())
                    .toDoDao()
                    .insertTodo(toDos[0]);
            return null;
        }
    }
}