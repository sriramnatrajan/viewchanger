package com.example.aravind.viewchanger;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Space;
import android.widget.TextView;

import java.util.ArrayList;

public class FourColumn_ListAdapter  extends ArrayAdapter<User>
{
    private LayoutInflater layoutInflater;
    private ArrayList<User> users;
    private  int mViewResourceId;
    public int currentPage=0;
Context mContext;
    public  FourColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<User> users)
    {
        super(context,textViewResourceId,users);
        this.mContext=context;
        this.users=users;
        layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
      convertView=layoutInflater.inflate(mViewResourceId,null);
        try {
            User user = users.get(position + (currentPage * Constant.SHOW_OFFSET));


            if (user != null) {
                TextView userid = (TextView) convertView.findViewById(R.id.userid);
                TextView username = (TextView) convertView.findViewById(R.id.username);
                TextView password = (TextView) convertView.findViewById(R.id.passwordd);
                TextView place = (TextView) convertView.findViewById(R.id.placee);
                if (userid != null) {
                    userid.setText((user.getID()));
                }

                if (username != null) {
                    username.setText((user.getName()));
                }
                if (password != null) {
                    password.setText((user.getPassword()));

                }
                if (place != null) {
                    place.setText((user.getPlace()));
                }
            }

            return convertView;
        }catch (Exception e){
            convertView=new Space(mContext);
            convertView.setVisibility(View.GONE);
            return convertView;
        }

    }
      @Override
    public int getCount() {
    return Constant.SHOW_OFFSET;
    }

}
