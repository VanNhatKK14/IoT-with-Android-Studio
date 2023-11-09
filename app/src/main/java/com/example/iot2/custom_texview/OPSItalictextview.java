package com.example.iot2.custom_texview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

public class OPSItalictextview extends AppCompatTextView {
    public OPSItalictextview(@NonNull Context context) {
        super(context);
        setFontsTextView();
    }

    public OPSItalictextview(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setFontsTextView();
    }

    public OPSItalictextview(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFontsTextView();
    }
    private void setFontsTextView(){
        Typeface typeface=Utils.getOpenSansItalicTypeface(getContext());
        setTypeface(typeface);
    }
}
