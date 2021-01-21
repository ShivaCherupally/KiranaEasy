package com.goodeggs.android.ui.menu;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.goodeggs.android.utils.Constants;
import com.goodeggs.android.R;
import com.goodeggs.android.bottommenu.HelperActivity;
import com.goodeggs.android.ui.login.LoginActivity;
import com.goodeggs.android.utils.Utility;

import java.util.ArrayList;


public class HomeMenuFragment extends Fragment implements IMenuItemAdapter {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static RecyclerView home_menuRecyclerView;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<MenuItems> celeb_List, member_List, manager_List, celebManager_List, switch_own_account_List;
    LinearLayout linearLayout;
    String loginType;
    static String Divider = "Divider";
    private static HomeMenuFragment instance = null;
    IMenuItemAdapter iMenuItemAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;
    }

    public static HomeMenuFragment getInstance() {
        return instance;
    }


    public static HomeMenuFragment newInstance(String param1, String param2) {
        HomeMenuFragment fragment = new HomeMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_menu_fragment, container, false);
        iMenuItemAdapter = this;
        initializeActions(root);
        return root;
    }

    private void initializeActions(View root) {
        linearLayout = root.findViewById(R.id.linearLayout);
        home_menuRecyclerView = root.findViewById(R.id.home_menuRecyclerView);
        home_menuRecyclerView.setHasFixedSize(true);
        home_menuRecyclerView.setNestedScrollingEnabled(true);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        home_menuRecyclerView.setLayoutManager(layoutManager);
        home_menuRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public void loadData() {
        class asyncTaskLoadData extends AsyncTask<String, Void, String> {

            public asyncTaskLoadData() {
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected String doInBackground(String... params) {

                //Member Menu Data
                loginType = "MEMBER";
                member_List = new ArrayList<MenuItems>();
                for (int i = 0; i < MenuItemDataMember.celebMenuData.length; i++) {
                    member_List.add(new MenuItems(MenuItemDataMember.celebMenuIconsData[i], MenuItemDataMember.celebMenuData[i]
                    ));
                }

                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                switch (loginType) {
                    case "MEMBER":
                        adapter = new MenuItemAdapter(getActivity(), member_List, iMenuItemAdapter, getContext());
                        home_menuRecyclerView.setAdapter(adapter);
                        break;
                }
            }
        }
        new asyncTaskLoadData().execute();
    }


    @Override
    public void onClick(MenuItems menuItems, int position) {
        Intent intent;
        if (menuItems == null) return;
        switch (menuItems.getItemName()) {
            case "My Profile":
//                navigateToMenuItemPage("My Profile", 9000);
                Utility.INSTANCE.navigateScreens(getContext(), "Profile", 9000);
                break;
            case "My Orders":
                navigateToMenuItemPage("My Orders", 9001);
                break;
            /*case "Invite a friend":
                Toast.makeText(requireContext(), "Please wait", Toast.LENGTH_SHORT).show();
//                if (Utility.isNetworkAvailable(Utility.getContext())) {
                Utility.INSTANCE.shareSocialNetwork(getContext(), "INVITE_FRIEND");
//                } else {
//                    Toast.makeText(getContext(), Constants.PLEASE_CHECK_INTERNET, Toast.LENGTH_LONG).show();
//                }
                break;*/
            case "Change Password":
//                navigateToMenuItemPage("Change Password", 9003);
                Utility.INSTANCE.navigateScreens(getContext(), "Change Password", 9003);
                break;
        /*    case "Terms & conditions":
                navigateToMenuItemPage("Terms & conditions", 9004);
                break;*/
/*            case "About us":
                navigateToMenuItemPage("About us", 9005);
                break;
            case "Contact us":
                navigateToMenuItemPage("Contact us", 9006);
                break;*/
            case "Logout":
                navigateToLogin();
                break;
        }
    }

    private void navigateToMenuItemPage(String title, int pageNo) {
        Intent intent = new Intent(getActivity(), HelperActivity.class);
        intent.putExtra(Constants.FRAGMENT_TITLE, title);
        intent.putExtra(Constants.FRAGMENT_KEY, pageNo);
        startActivity(intent);
    }

    private void navigateToLogin() {
        Utility.INSTANCE.clearLoginData();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        getActivity().finish();
        startActivity(intent);
    }

    /*public static class MenuItemDataMember {
        static String[] celebMenuData = {"My Profile", "My Orders",
                "Invite a friend", "Change Password", "Terms & conditions"
                , "About us", "Contact us", "Logout"
        };
        static Integer[] celebMenuIconsData = {
                R.drawable.ic_baseline_person_24,
                R.drawable.ic_transactions_new,
                R.drawable.ic_app_promotions_new,
                R.drawable.ic_auditions_new, R.drawable.ic_about_new, R.drawable.ic_baseline_pageview_24,
                R.drawable.ic_contact_us_new, R.drawable.ic_logout_new
        };



    }*/
    public static class MenuItemDataMember {
        static String[] celebMenuData = {"My Profile", "My Orders",
                "Change Password", "Logout"
        };
        static Integer[] celebMenuIconsData = {
                R.drawable.ic_baseline_person_24,
                R.drawable.ic_transactions_new,
                R.drawable.ic_auditions_new,
                R.drawable.ic_logout_new
        };


    }


    @Override
    public void setMenuVisibility(boolean isVisibleToUser) {
        super.setMenuVisibility(isVisibleToUser);
        if (isVisibleToUser) {
            loadData();
        }
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

}

