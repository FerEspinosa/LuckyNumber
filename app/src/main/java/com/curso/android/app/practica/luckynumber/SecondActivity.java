package com.curso.android.app.practica.luckynumber;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {

    TextView tv_luckyNumber;
    String userName, luckyNumber, message;
    Button shareButton;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv_luckyNumber = findViewById(R.id.luckyNumber);
        shareButton = findViewById(R.id.shareButton);

        setLuckyNumber();

        shareButton.setOnClickListener(v -> shareText());
    }

    private void shareText() {
        userName = getIntent().getStringExtra("name");
        message = userName + "'s lucky number is " + luckyNumber;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    private void setLuckyNumber() {

        // la siguiente linea genera un numero entero aleatorio entre 1 y 1000
        luckyNumber = String.valueOf((int) (Math.random() * 1000));

        tv_luckyNumber.setText(luckyNumber);
    }
}