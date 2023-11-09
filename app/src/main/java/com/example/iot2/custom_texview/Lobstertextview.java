package com.example.iot2.custom_texview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class Lobstertextview extends AppCompatTextView {
    public Lobstertextview(@NonNull Context context) {
        super(context);
        setFontsTextView();
    }

    public Lobstertextview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontsTextView();
    }

    public Lobstertextview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontsTextView();
    }
    private void setFontsTextView(){
        Typeface typeface=Utils.getLobsterTypeface(getContext());
        setTypeface(typeface);
    }
}
