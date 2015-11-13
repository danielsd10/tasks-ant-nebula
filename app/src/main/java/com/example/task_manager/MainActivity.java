package com.example.task_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final int NEWTASK_REQUEST = 1;

    private ArrayList<Task> allTasks = new ArrayList<Task>();
    private TaskAdapter adapter = null;

    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(dbHelper == null) {
            dbHelper = new DbHelper(this);
        }

        // iniciar adaptador
        if (adapter == null) {
            adapter = new TaskAdapter(this, 0, dbHelper.selectTasks());
        }
        // asignar adaptador a ListView
        ListView listTasks = (ListView) findViewById(R.id.listTasks);
        listTasks.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivityForResult(intent, NEWTASK_REQUEST);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // preguntar por codigo de solicitud
        if (requestCode == NEWTASK_REQUEST) {
            // revisar que fue resultado del boton guardar
            if (resultCode == RESULT_OK) {
                String tarea = data.getStringExtra("tarea");

                Task newTask = new Task();
                newTask.setName(tarea);

                dbHelper.insertTask(newTask);

                adapter.add(newTask);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
