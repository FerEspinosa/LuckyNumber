package com.curso.android.app.practica.luckynumber;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText et_username;
    Button btn_submit;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        et_username = findViewById(R.id.editText);
        btn_submit = findViewById(R.id.button);
        btn_submit.setOnClickListener(this::goToSecondActivity);

    }

    public String getUserName() {
        return et_username.getText().toString();
    }

    public void goToSecondActivity (View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        name = getUserName();
        Toast.makeText(this, "Hello " + name, Toast.LENGTH_SHORT).show();
        intent.putExtra("name", name);
        startActivity(intent);
    }

}