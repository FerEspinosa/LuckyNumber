package com.curso.android.app.practica.luckynumber;

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

import java.util.Random;

public class SecondActivity extends AppCompatActivity {


    TextView tv_luckyNumber, tv_welcomeText;
    String userName, luckyNumber, message;
    Button share_btn;
    int luckyNumberInt;
    final int MAX = 1000;

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

        // initialize UI widgets
        tv_welcomeText = findViewById(R.id.welcome_user);
        tv_luckyNumber = findViewById(R.id.luckyNumber);
        share_btn = findViewById(R.id.shareButton);

        getDataFromAct1();
        setWelcomeText(userName);
        luckyNumber = generateLuckyNumber()+"";
        setLuckyNumber(luckyNumber);
        share_btn.setOnClickListener(v -> shareData(userName, luckyNumber));
    }

    private void getDataFromAct1() {
        userName = getIntent().getStringExtra("name");
    }

    private void setWelcomeText(String userName) {
        String welcomeText = "Welcome " + userName;
        tv_welcomeText.setText(welcomeText);
    }

    private int generateLuckyNumber() {
        Random random = new Random();
        luckyNumberInt = random.nextInt(MAX);
        return luckyNumberInt;
    }

    private void setLuckyNumber(String luckyNumber) {
        tv_luckyNumber.setText(luckyNumber);
    }

    private void shareData(String userName, String luckyNumber) {

        message = userName + "'s lucky number is " + luckyNumber;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(sendIntent, "Share via"));
    }
}