package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.attribute.DosFileAttributes;

public class MainActivity extends AppCompatActivity {

    EditText iptxt;
    EditText porttxt;
    EditText msgtxt;
    Button sendbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iptxt = findViewById(R.id.iptxt);
        porttxt = findViewById(R.id.porttxt);
        msgtxt = findViewById(R.id.msgtxt);
        sendbtn = findViewById(R.id.sendbtn);

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {

                        Socket s = new Socket(iptxt.getText().toString(),Integer.parseInt(porttxt.getText().toString()));
                        DataOutputStream DOS = new DataOutputStream(s.getOutputStream());
                        DOS.writeUTF(msgtxt.getText().toString());
                        DOS.flush();
                        DOS.close();
                        s.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            t.start();
            }
        });
    }
}