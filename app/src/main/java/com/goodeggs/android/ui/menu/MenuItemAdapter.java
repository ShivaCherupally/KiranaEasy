package com.goodeggs.android.ui.menu;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.goodeggs.android.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class MenuItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;
    private ArrayList<MenuItems> dataSet;
    private Activity activity;
    private Context mContext;
    private IMenuItemAdapter iMenuItemAdapter;
    static String Divider = "Divider";
    CircleImageView mUserProfileImage;


    public MenuItemAdapter(Activity activity, ArrayList<MenuItems> strings, IMenuItemAdapter iMenuItemAdapter, Context mContext) {
        this.activity = activity;
        this.dataSet = strings;
        this.iMenuItemAdapter = iMenuItemAdapter;
        this.mContext = mContext;
    }

    public MenuItemAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_homemenufragment, parent, false);
            return new ItemViewHolder(itemView);
        } else {
            return null;
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ItemViewHolder) {
            MenuItems menuItems = dataSet.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.textViewName.setText(dataSet.get(position).itemName);
            itemViewHolder.imageViewIcon.setImageResource(menuItems.getImage());
            itemViewHolder.listitemLayout.setOnClickListener(view -> {
                iMenuItemAdapter.onClick(dataSet.get(position), position);
            });

            try {
                switch (menuItems.getItemName()) {
                    case "My Profile":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_credit));
                        break;
                    case "My Orders":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_preferences));
                        break;
                    case "Invite a friend":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_celebrities));
                        break;
                    case "Change Password":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_transaction));
                        break;
                    case "Terms & conditions":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_fan_follows));
                        break;
                    case "About us":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_invite_friend));
                        break;
                    case "Contact us":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.menu_bg_auditions));
                        break;
                    case "Logout":
                        itemViewHolder.ivBackground.setBackgroundTintList(ContextCompat.getColorStateList(mContext, R.color.progress_color));
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }


    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


    private class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        ImageView imageViewIcon, ivBackground;
        LinearLayout listitemLayout, divideview;
        View viewLine;

        public ItemViewHolder(View itemView) {
            super(itemView);
            this.textViewName = (TextView) itemView.findViewById(R.id.textView_item_menu);
            this.imageViewIcon = (ImageView) itemView.findViewById(R.id.imageView_item_menu);
            this.ivBackground = (ImageView) itemView.findViewById(R.id.ivBackground);
            this.listitemLayout = (LinearLayout) itemView.findViewById(R.id.listitemLayout);
            this.divideview = (LinearLayout) itemView.findViewById(R.id.divideview);
            this.viewLine = (View) itemView.findViewById(R.id.viewLine);
        }
    }


}
