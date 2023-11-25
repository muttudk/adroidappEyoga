package com.ntpl.eyoga.font;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewRegular extends TextView {
    Typeface mTypeFace;

    public TextViewRegular(Context context) {
        super(context);
        setTypeFace(context);
    }

    public TextViewRegular(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeFace(context);
    }

    public TextViewRegular(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeFace(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TextViewRegular(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setTypeFace(context);
    }

    public void setTypeFace(Context context) {
        mTypeFace = Typeface.createFromAsset(context.getResources().getAssets(), "fonts/sans_serif_regular.otf");
//        setTypeface(mTypeFace, Typeface.NORMAL);
        setTypeface(mTypeFace);
    }
}