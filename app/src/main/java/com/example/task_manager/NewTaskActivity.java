package com.example.task_manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        final EditText editText = (EditText)findViewById(R.id.editText);
        Button buttonSave = (Button)findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().length()!=0) {
                    String tarea = editText.getText().toString();
                    Intent intent = getIntent();
                    intent.putExtra("tarea", tarea);
                    setResult(RESULT_OK, intent);
                    finish(); // finalizar para volver a actividad anterior
                }
            }
        });


    }
}
