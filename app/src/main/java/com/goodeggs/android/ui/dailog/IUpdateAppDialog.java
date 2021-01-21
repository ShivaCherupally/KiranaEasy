package com.goodeggs.android.ui.dailog;

/**
 * Created by User on 17-07-2018.
 **/

public interface IUpdateAppDialog {
    public void updateNow();

    public void doLater();

    public void reasonReturn(String reason, Integer pos);
}
