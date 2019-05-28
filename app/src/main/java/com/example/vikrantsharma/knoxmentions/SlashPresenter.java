package com.example.vikrantsharma.knoxmentions;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vikrantsharma.knoxmentions.mentions.RecyclerViewPresenter;

import java.util.ArrayList;
import java.util.List;


public class SlashPresenter extends RecyclerViewPresenter<SlahsCommands> {

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
        List<SlahsCommands> all = SlahsCommands.SLASH;
        if (TextUtils.isEmpty(query)) {
            adapter.setData(all);
        } else {
            query = query.toString().toLowerCase();
            List<SlahsCommands> list = new ArrayList<>();
            for (SlahsCommands u : all) {
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

        private List<SlahsCommands> data;

        public class Holder extends RecyclerView.ViewHolder {
            private View root;
            private TextView fullname;
            private TextView username;
            public Holder(View itemView) {
                super(itemView);
                root = itemView;
                fullname = ((TextView) itemView.findViewById(R.id.fullname));
                username = ((TextView) itemView.findViewById(R.id.username));
            }
        }

        public void setData(List<SlahsCommands> data) {
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
            final SlahsCommands user = data.get(position);
            holder.fullname.setText(user.getHint());
            holder.username.setText("/" + user.getCommand());
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dispatchClick(user);
                }
            });
        }
    }
}
