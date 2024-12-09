package com.example.onlinestoreapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.onlinestoreapp.R;

public class KhuyenMaiActivity extends AppCompatActivity {
    TextView noidung;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_khuyen_mai);
        initView();
        initData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initData() {
        String nd = getIntent().getStringExtra("noidung");
        String url = getIntent().getStringExtra("url");
        noidung.setText(nd);
        Glide.with(this).load(url).into(imageView);
    }

    private void initView() {
        noidung = findViewById(R.id.km_noidung);
        imageView = findViewById(R.id.km_image);
    }
}