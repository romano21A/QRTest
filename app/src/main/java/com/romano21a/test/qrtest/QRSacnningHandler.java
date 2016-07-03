package com.romano21a.test.qrtest;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class QRSacnningHandler extends Activity {

    private final Activity activity;
    private final String TAG = "QRScanHandler";

    public QRSacnningHandler(Activity activity) {
        this.activity = activity;
    }

    public void scanQR() {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setOrientationLocked(false);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }
}
