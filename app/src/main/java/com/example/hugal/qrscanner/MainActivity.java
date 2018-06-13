package com.example.hugal.qrscanner;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;
    private String qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void QRScanner(View view) {
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        qrcode="";
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contenido:");
        builder.setMessage(result.getText());
        //builder.setMessage(result.getText()+"\n"+"Formato: "+result.getBarcodeFormat());
        AlertDialog alertdialog1 = builder.create();
        alertdialog1.show();
        qrcode.concat(result.getText());
        scannerView.resumeCameraPreview(this);
    }
}
