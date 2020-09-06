package com.kiranam.android.fontstyles;

import android.content.Context;
import android.util.AttributeSet;

import com.kiranam.android.utils.Utility;


/**
 * Created by Uday Kumay Vurukonda on 1/8/2019.
 * <p>
 * Mr.Psycho
 */
public class TextViewBold extends androidx.appcompat.widget.AppCompatTextView {

    public TextViewBold(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        /*Typeface customFont = FontCache.getTypeface("helveticaneue-light-webfont.ttf", context);
        setTypeface(customFont);*/
        setTypeface(Utility.INSTANCE.getTypeface(12,getContext()));

    }
}

