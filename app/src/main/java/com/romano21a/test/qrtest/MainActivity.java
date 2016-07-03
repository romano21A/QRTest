package com.romano21a.test.qrtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "main";

    private ImageView qrView;
    private TextView qrText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button scan = (Button) findViewById(R.id.scan);
        Button create = (Button) findViewById(R.id.create);
        qrText = (EditText) findViewById(R.id.QRText);
        qrView = (ImageView) findViewById(R.id.QRView);


        final QRSacnningHandler qrSacnningHandler = new QRSacnningHandler(this);
        final QRCreationHandler qrCreationHandler = new QRCreationHandler();


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrSacnningHandler.scanQR();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = qrCreationHandler.createQR(qrText.getText().toString());
                    qrView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG,"Result available");
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d(TAG, "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                qrText.setText(result.getContents());
            } else {
                Log.d(TAG, "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
