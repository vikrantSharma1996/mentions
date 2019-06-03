package com.example.vikrantsharma.knoxmentions.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vikrantsharma.knoxmentions.R;
import com.example.vikrantsharma.knoxmentions.mentions.RecyclerViewPresenter;
import com.example.vikrantsharma.knoxmentions.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class UserPresenter extends RecyclerViewPresenter<User> {

    protected Adapter adapter;


    public UserPresenter(Context context) {
        super(context);
    }

    @Override
    protected PopupDimensions getPopupDimensions() {
        PopupDimensions dims = new PopupDimensions();
        dims.width = 600;
        dims.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        return dims;
    }

    @Override
    protected RecyclerView.Adapter instantiateAdapter() {
        adapter = new Adapter();
        return adapter;
    }

    @Override
    protected void onQuery(@Nullable CharSequence query) {
        List<User> all = User.USERS;
        if (TextUtils.isEmpty(query)) {
            adapter.setData(all);
        } else {
            query = query.toString().toLowerCase();
            List<User> list = new ArrayList<>();
            for (User u : all) {
                if (u.getFullname().toLowerCase().contains(query) ||
                        u.getUsername().toLowerCase().contains(query)||u.getImageurl().contains(query)) {
                    list.add(u);
                }
            }
            adapter.setData(list);
            Log.e("UserPresenter", "found "+list.size()+" users for query "+query);
        }
        adapter.notifyDataSetChanged();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private List<User> data;

        public class Holder extends RecyclerView.ViewHolder {
            private View root;


            private ImageView imageicon;
            private TextView fullname;
            private TextView username;

            public Holder(View itemView) {
                super(itemView);
                root = itemView;
                fullname = ((TextView) itemView.findViewById(R.id.fullname));
                username = ((TextView) itemView.findViewById(R.id.username));
                imageicon=((ImageView)itemView.findViewById(R.id.imageicon));

            }
        }

        public void setData(List<User> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return (isEmpty()) ? 1 : data.size();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.user, parent, false));
        }

        private boolean isEmpty() {
            return data == null || data.isEmpty();
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            if (isEmpty()) {
                holder.fullname.setText("No user here!");
                holder.username.setText("Sorry!");
                holder.root.setOnClickListener(null);
                return;
            }
            final User user = data.get(position);


//            Bitmap bmp;
//            String imageUrl=user.getImageurl();
//            InputStream in = null;
//            try {
//                in = new URL(imageUrl).openStream();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            bmp = BitmapFactory.decodeStream(in);


            holder.imageicon.setImageResource(R.drawable.imageview_sample_image);
            //holder.imageicon.setImageURI(Uri.parse(user.getImageurl()));
            //holder.imageicon.setImageBitmap(bmp);

            holder.fullname.setText(user.getFullname());
            holder.username.setText("@" + user.getUsername());
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick(user);
                }
            });
        }
    }
}
