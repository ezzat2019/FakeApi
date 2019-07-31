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
import com.example.programmer.fakeapi.models.Comments;
import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.ui.ItemClickInterface;

public class RecycleCommentAdapter extends PagedListAdapter<Comments, RecycleCommentAdapter.VH> {
    private static Context cont;
    private static ItemClickInterface clickInterface;

    private static DiffUtil.ItemCallback<Comments> diffCallback = new DiffUtil.ItemCallback<Comments>() {
        @Override
        public boolean areItemsTheSame(@NonNull Comments oldItem, @NonNull Comments newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Comments oldItem, @NonNull Comments newItem) {
            return oldItem.getBody().equals(newItem.getBody());
        }
    };

    public void setOnItem(ItemClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    public RecycleCommentAdapter(Context context) {
        super(diffCallback);
        cont = context;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false));
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
            txt_title = itemView.findViewById(R.id.txt_title_comment);

            txt_body = itemView.findViewById(R.id.txt_body_comment);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onClick(getPosition());

                }
            });


        }

        public void bind(Comments comments) {
            txt_title.setText(comments.getName());
            txt_body.setText(comments.getBody());
        }
    }
}
