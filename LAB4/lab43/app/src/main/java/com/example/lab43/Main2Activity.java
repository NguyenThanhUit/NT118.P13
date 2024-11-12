package com.example.lab43;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.main2_activity);

        Button btn = findViewById(R.id.btnBack);

       
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iNewActivity = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(iNewActivity);


                overridePendingTransition(R.anim.anim_slide_lefttoright, R.anim.anim_slide_righttoleft);
            }
        });
    }

}
