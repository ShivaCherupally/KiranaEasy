package com.goodeggs.android.fontstyles;

import android.content.Context;
import android.util.AttributeSet;

import com.goodeggs.android.utils.Utility;


/**
 * Created by Uday Kumay Vurukonda on 1/8/2019.
 * <p>
 * Mr.Psycho
 */
public class ButtonSemiBold extends androidx.appcompat.widget.AppCompatButton {

    public ButtonSemiBold(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public ButtonSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public ButtonSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        /*Typeface customFont = FontCache.getTypeface("helveticaneue-light-webfont.ttf", context);
        setTypeface(customFont);*/
        setTypeface(Utility.INSTANCE.getTypeface(11,getContext()));

    }
}
