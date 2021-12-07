package com.strawberry74.sleepdiettracker;

import static android.widget.Toast.makeText;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class SleepActivity extends AppCompatActivity {
    private Button homeButton;
    private Button dietButton;
    private FloatingActionButton addDataSleep;
    int numHours;
    private ProgressBar progress;
    private TextView textViewHours;

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText hours;
    private Button add, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        homeButton = (Button) findViewById(R.id.toHome);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity();
            }
        });

        dietButton = (Button) findViewById(R.id.toDiet);
        dietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDietActivity();
            }
        });

        addDataSleep = (FloatingActionButton) findViewById(R.id.addSleepData);
        addDataSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddSleep();
            }
        });
    }
    public void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openDietActivity() {
        Intent intent = new Intent(this, DietActivity.class);
        startActivity(intent);
    }

    public void openAddSleep() {
//        Intent intent = new Intent(this, AddSleep.class);
//        startActivity(intent);
        dialogBuilder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.popup, null);
        hours = (EditText) popupView.findViewById(R.id.hours);
        add = (Button) popupView.findViewById(R.id.add);
        cancel = (Button) popupView.findViewById(R.id.cancel);

        dialogBuilder.setView(popupView);
        dialog = dialogBuilder.create();
        dialog.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("hours is " + hours.toString());
                //Toast.makeText(this, "hours is "+hours.toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(SleepActivity.this, "Hours is "+Integer.parseInt(hours.getText().toString()), Toast.LENGTH_SHORT).show();
                numHours = Integer.parseInt(hours.getText().toString());
                dialog.dismiss();
                updateProgressBar();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    public void updateProgressBar() {
        progress = (ProgressBar) findViewById(R.id.progress);
        progress.setProgress(numHours*10);
        textViewHours = (TextView) findViewById(R.id.textViewHours);
        textViewHours.setText(numHours + " hours of sleep");

    }

}