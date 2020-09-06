package com.kiranam.android.viewpager;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.kiranam.android.dummy.DummyFragment;
import com.kiranam.android.utils.ToastKt;


/**
 * Created by User on 28-07-2018.
 **/

public class HomeViewPagerAdapter extends FragmentPagerAdapter {
    int type = 1;
    String CelebId = null;
    Context context;
    int addPosition = 0;
    boolean isFromAdd = false;

    public HomeViewPagerAdapter(FragmentManager fm,Context mContext) {
        super(fm);
        this.context = mContext;

    }

    public HomeViewPagerAdapter(FragmentManager fm, String CelebId, Context mContext) {
        super(fm);
        this.CelebId = CelebId;
        this.context = mContext;

    }

    public HomeViewPagerAdapter(FragmentManager fm, Context mContext, int addPosition, boolean isFromAdd) {
        super(fm);
        this.context = mContext;
        this.addPosition = addPosition;
        this.isFromAdd = isFromAdd;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (type == 1) {
            switch (position) {
                case 0:
                    Toast.makeText(context,"One",Toast.LENGTH_LONG).show();
                    if (CelebId != null) {
                        fragment = DummyFragment.newInstance(null, CelebId);
                    } else {
                        fragment = DummyFragment.newInstance(null, null);
                    }
                    break;
                case 1:
                    Toast.makeText(context,"Two",Toast.LENGTH_LONG).show();
//                    fragment = CelebrityBaseFragment.newInstance();
                    //For new screens CK
                    fragment = DummyFragment.newInstance(null, null);
                    break;
                case 2:
                    Toast.makeText(context,"Three",Toast.LENGTH_LONG).show();
//                    fragment = DummyFragment.newInstance(null, null);
                    fragment = DummyFragment.newInstance(null, null);
                    Log.d("middleicon", true + "");
                    break;
                case 3:
                    Toast.makeText(context,"Four",Toast.LENGTH_LONG).show();
//                    fragment = FragmentTabsChat.newInstance(null, addPosition, isFromAdd);
                    fragment = DummyFragment.newInstance(null, null);
                    //fragment = PrefrencesExpentableListviewFragment.newInstance(false, null);
                    break;
                case 4:
                    Toast.makeText(context,"Five",Toast.LENGTH_LONG).show();
//                    fragment = HomeMenuFragment.newInstance(null, null);
                    fragment = DummyFragment.newInstance(null, null);
                    break;

            }
            return fragment;
        } else {
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return type == 1 ? CelebId == null ? 5 : 1 : 0;
    }

}
