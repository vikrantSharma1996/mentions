package com.example.vikrantsharma.knoxmentions.utils;

import android.content.Context;
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
import com.example.vikrantsharma.knoxmentions.model.SlashCommands;

import java.util.ArrayList;
import java.util.List;


public class SlashPresenter extends RecyclerViewPresenter<SlashCommands> {

    protected Adapter adapter;

    public SlashPresenter(Context context) {
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
        List<SlashCommands> all = SlashCommands.SLASH;
        if (TextUtils.isEmpty(query)) {
            adapter.setData(all);
        } else {
            query = query.toString().toLowerCase();
            List<SlashCommands> list = new ArrayList<>();
            for (SlashCommands u : all) {
                if (u.getCommand().toLowerCase().contains(query) ||
                        u.getMsg().toLowerCase().contains(query)|| u.getHint().toLowerCase().contains(query)) {
                    list.add(u);
                }
            }
            adapter.setData(list);
            Log.e("UserPresenter", "found "+list.size()+" users for query "+query);
        }
        adapter.notifyDataSetChanged();
    }

    class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private List<SlashCommands> data;

        public class Holder extends RecyclerView.ViewHolder {
            private View root;
           // private ImageView imageicon;
            private TextView commandname;
            private TextView commandhint;
            public Holder(View itemView) {
                super(itemView);
                root = itemView;
                commandname = ((TextView) itemView.findViewById(R.id.commandname));
                commandhint = ((TextView) itemView.findViewById(R.id.commandhint));
            //    imageicon=((ImageView)itemView.findViewById(R.id.imageicon));
            }
        }

        public void setData(List<SlashCommands> data) {
            this.data = data;
        }

        @Override
        public int getItemCount() {
            return (isEmpty()) ? 1 : data.size();
        }

        @Override
        public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Holder(LayoutInflater.from(getContext()).inflate(R.layout.slash, parent, false));
        }

        private boolean isEmpty() {
            return data == null || data.isEmpty();
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            if (isEmpty()) {
                holder.commandhint.setText("No user here!");
                holder.commandname.setText("Sorry!");
                holder.root.setOnClickListener(null);
                return;
            }
            final SlashCommands user = data.get(position);
            //holder.imageicon.setImageResource(R.drawable.imageview_sample_image);

            holder.commandhint.setText(user.getHint());
            holder.commandname.setText("/" + user.getCommand());
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick(user);
                }
            });
        }
    }
}
