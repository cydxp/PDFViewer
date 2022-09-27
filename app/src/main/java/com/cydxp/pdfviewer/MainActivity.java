package com.cydxp.pdfviewer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.shockwave.pdfium.PdfPasswordException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomPDFView customPDFView = findViewById(R.id.pdf_view);
        customPDFView.fromAsset("sample.pdf")
                .enableSwipe(true)
                .enableDoubletap(true)
                .enableAnnotationRendering(true)
                .onError(throwable -> {
                    if (throwable instanceof PdfPasswordException) {
                        //PDF文件需要密码
                        Toast.makeText(getBaseContext(), "PDF Need Password", Toast.LENGTH_SHORT).show();
                    } else {
                        //PDF文件损坏或格式错误，请重新加载
                        Toast.makeText(getBaseContext(), "Please reload PDF", Toast.LENGTH_SHORT).show();
                    }
                })
                .onPageError((i, throwable) -> {
                    //PDF文件加载失败
                    Toast.makeText(getBaseContext(), "PDF load failed", Toast.LENGTH_SHORT).show();
                })
                .load();
    }
}