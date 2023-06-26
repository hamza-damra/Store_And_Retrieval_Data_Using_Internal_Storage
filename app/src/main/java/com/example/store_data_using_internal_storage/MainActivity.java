package com.example.store_data_using_internal_storage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.PrintWriterPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    Button button_store, button_restore;
    EditText et_name, et_email;

    TextView tv_result;

    public static final String FILE_NAME = "info";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button_store = findViewById(R.id.button_store);
        button_restore = findViewById(R.id.button_restore);
        et_name = findViewById(R.id.et_name);
        et_email = findViewById(R.id.et_email);
        tv_result = findViewById(R.id.tv_reader);

        button_store.setOnClickListener(v -> {
           String name = et_name.getText().toString();
           String email = et_email.getText().toString();
            try {
                FileOutputStream fileOutputStream =  openFileOutput(FILE_NAME,MODE_PRIVATE);
                PrintWriter pw = new PrintWriter(fileOutputStream);
                pw.println("username: "+name+" email: "+email);
                pw.close();
                fileOutputStream.close();
                et_name.setText("");
                et_email.setText("");
                Toast.makeText(getBaseContext(),"Done",Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });

        button_restore.setOnClickListener(v -> {
            try {
                FileInputStream fileInputStream =  openFileInput(FILE_NAME);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp = "";
                String allText = "";
                while ((temp = bufferedReader.readLine()) != null)
                {
                    allText += temp;
                    tv_result.setText(allText);
                }
                bufferedReader.close();
                inputStreamReader.close();
                fileInputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });




    }
}