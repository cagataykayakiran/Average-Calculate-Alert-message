package com.example.ortalamahesaplama;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    TextView textView;
    SharedPreferences sharedPreferences;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        sharedPreferences = this.getSharedPreferences("com.example.ortalamahesaplama" , Context.MODE_PRIVATE);
        int saveresult = sharedPreferences.getInt("saveresult" , 0);
        textView.setText ("Average: " +saveresult);


    }

    public void Calculation (View view) {

        AlertDialog.Builder  alert = new AlertDialog.Builder(this);
        alert.setTitle("Calculate");
        alert.setMessage("Are you sure ? ");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int number1 = Integer.parseInt(editText1.getText().toString());
                int number2 = Integer.parseInt(editText2.getText().toString());
                int result = (number1 + number2)/2;
                textView.setText("Average: " +result);
                sharedPreferences.edit().putInt("saveresult" ,result).apply();
                Toast.makeText(MainActivity.this , "Saved" , Toast.LENGTH_LONG).show();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int savedata = sharedPreferences.getInt("saveresult" , 0);
                if ( savedata != 0) {
                    sharedPreferences.edit().remove("saveresult").apply();
                    textView.setText("Average: ");
                    Toast.makeText(MainActivity.this , "Deleted" , Toast.LENGTH_LONG).show();
                }
            }
        });

        alert.show();
    }

}