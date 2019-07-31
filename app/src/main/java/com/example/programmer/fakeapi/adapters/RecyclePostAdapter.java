package com.example.programmer.fakeapi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.ui.ItemClickInterface;

public class RecyclePostAdapter extends PagedListAdapter<Posts, RecyclePostAdapter.VH> {
    private static Context cont;
    private static ItemClickInterface clickInterface;

    private static DiffUtil.ItemCallback<Posts> diffCallback = new DiffUtil.ItemCallback<Posts>() {
        @Override
        public boolean areItemsTheSame(@NonNull Posts oldItem, @NonNull Posts newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Posts oldItem, @NonNull Posts newItem) {
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    };

    public void setOnItem(ItemClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public RecyclePostAdapter(Context context) {
        super(diffCallback);
        cont = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(getItem(position));


    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView txt_title, txt_body;
        private Button btn;


        public VH(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title_post);

            txt_body = itemView.findViewById(R.id.txt_body_post);

            btn=itemView.findViewById(R.id.btn_comment_post);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onClick(getPosition());

                }
            });


        }

        public void bind(Posts posts) {
            txt_title.setText(posts.getTitle());
            txt_body.setText(posts.getBody());
        }
    }
}
