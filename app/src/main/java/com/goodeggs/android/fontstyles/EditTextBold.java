package com.goodeggs.android.fontstyles;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;

import com.goodeggs.android.utils.Utility;


/**
 * Created by Uday Kumay Vurukonda on 1/8/2019.
 * <p>
 * Mr.Psycho
 */
public class EditTextBold extends androidx.appcompat.widget.AppCompatEditText {

    public EditTextBold(Context context) {
        super(context);

        applyCustomFont(context);
    }

    public EditTextBold(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context);
    }

    public EditTextBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context);
    }

    private void applyCustomFont(Context context) {
        /*Typeface customFont = FontCache.getTypeface("helveticaneue-light-webfont.ttf", context);
        setTypeface(customFont);*/
        setTypeface(Utility.INSTANCE.getTypeface(12,getContext()));
    }

    //don't remove this method
    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }
}

