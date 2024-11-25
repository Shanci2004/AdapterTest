package com.example.adaptertest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlertTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_test);

        Button sd = (Button)findViewById(R.id.sd);
        sd.setOnClickListener((view -> {
            showAlert();
        }));

    }

    private void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AlertTest.this)
                .setTitle("ANDROID APP")
                .setView(R.layout.alert_content_view)
                .setPositiveButton("Sign in", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertTest.this, "登录", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(AlertTest.this, "取消", Toast.LENGTH_LONG).show();
                    }
                });
        Dialog dialog = builder.create();
        dialog.show();
    }
}