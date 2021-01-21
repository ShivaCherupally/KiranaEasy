package com.goodeggs.android.fontstyles;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

import com.goodeggs.android.utils.Utility;


/**
 * <p>
 * Mr.Psycho
 */
public class ButtonMedium extends AppCompatButton {

    public ButtonMedium(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public ButtonMedium(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public ButtonMedium(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        /*Typeface customFont = FontCache.getTypeface("helveticaneue-light-webfont.ttf", context);
        setTypeface(customFont);*/
        setTypeface(Utility.INSTANCE.getTypeface(10,getContext()));
    }
}

