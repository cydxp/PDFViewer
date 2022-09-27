package com.cydxp.pdfviewer;

import android.content.Context;
import android.os.HandlerThread;
import android.util.AttributeSet;

import com.github.barteksc.pdfviewer.PDFView;

import java.lang.reflect.Field;

public class CustomPDFView extends PDFView {

    public CustomPDFView(Context context, AttributeSet set) {
        super(context, set);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            Field field = PDFView.class.getDeclaredField("renderingHandlerThread");
            field.setAccessible(true);
            if (field.get(this) == null) {
                field.set(this, new HandlerThread("PDF renderer"));
            }
        } catch (NoSuchFieldException | IllegalAccessException exception) {
            exception.printStackTrace();
        }
    }
}
