package com.example.iot2.custom_texview;

import android.content.Context;
import android.graphics.Typeface;
import android.text.style.TypefaceSpan;

public class Utils {
    private static Typeface LobsterTypeface;
    private static Typeface OpenSansBoltTypeface;
    private static Typeface OpenSansBoltItalicTypeface;
    private static Typeface OpenSansItalicTypeface;
    private static Typeface OpenSansLightTypeface;
    private static Typeface OpenSansLightItalicTypeface;
    private static Typeface OpenSansRegularTypeface;

    public static Typeface getLobsterTypeface(Context context) {
        if(LobsterTypeface==null) {
            LobsterTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/Lobster_1.3.otf");
        }
        return LobsterTypeface;
    }

    public static Typeface getOpenSansBoltTypeface(Context context) {
        if(OpenSansBoltTypeface==null) {
            OpenSansBoltTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Bold.ttf");
        }
        return OpenSansBoltTypeface;
    }

    public static Typeface getOpenSansBoltItalicTypeface(Context context) {
        if(OpenSansBoltItalicTypeface==null) {
            OpenSansBoltItalicTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-BoldItalic.ttf");
        }
        return OpenSansBoltItalicTypeface;
    }

    public static Typeface getOpenSansItalicTypeface(Context context) {
        if(OpenSansItalicTypeface==null) {
            OpenSansItalicTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Italic.ttf");
        }
        return OpenSansItalicTypeface;
    }

    public static Typeface getOpenSansLightTypeface(Context context) {
        if(OpenSansLightTypeface==null) {
            OpenSansLightTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Light.ttf");
        }
        return OpenSansLightTypeface;
    }

    public static Typeface getOpenSansLightItalicTypeface(Context context) {
        if(OpenSansLightItalicTypeface==null) {
            OpenSansLightItalicTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-LightItalic.ttf");
        }
        return OpenSansLightItalicTypeface;
    }

    public static Typeface getOpenSansRegularTypeface(Context context) {
        if(OpenSansRegularTypeface==null) {
            OpenSansRegularTypeface= Typeface.createFromAsset(context.getAssets(), "fonts/OpenSans-Regular.ttf");
        }
        return OpenSansRegularTypeface;
    }
}
