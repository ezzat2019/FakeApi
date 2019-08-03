package com.example.programmer.fakeapi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.ui.ItemClickInterface;

import java.util.ArrayList;
import java.util.List;

public class RecyclePostAdapter extends PagedListAdapter<Posts, RecyclePostAdapter.VH> implements Filterable {
    private static Context cont;
    private static ItemClickInterface clickInterface;
    private static boolean isOn = false;
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
    private List<Posts> list = new ArrayList<>();
    private List<Posts> listAll = new ArrayList<>();

    public RecyclePostAdapter(Context context) {
        super(diffCallback);
        cont = context;


    }

    public static boolean isIsOn() {
        return isOn;
    }

    public static void setIsOn(boolean isOn) {
        RecyclePostAdapter.isOn = isOn;
    }

    public void setOnItem(ItemClickInterface clickInterface) {
        this.clickInterface = clickInterface;
    }

    @Override
    public void submitList(@Nullable PagedList<Posts> pagedList) {

        super.submitList(pagedList);
        list.addAll(getCurrentList());
        listAll.addAll(getCurrentList());


    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {


        holder.bind(getCurrentList().get(position));


    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override

            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("sssssss", "list b");


                if (charSequence.toString().isEmpty()) {
                    listAll.addAll(getCurrentList());
                } else {
                    List<Posts> filetr = new ArrayList();

                    for (Posts posts : getCurrentList()) {
                        if (posts.getTitle().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {

                            filetr.add(posts);
                        }

                    }
                    submitList((PagedList<Posts>) filetr);
                }


                FilterResults results = new FilterResults();
                results.values = getCurrentList();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                if (filterResults.values != null) {


                    submitList((PagedList<Posts>) filterResults.values);


                    notifyDataSetChanged();


                }
            }
        };
    }

    @Override
    public int getItemCount() {


        return super.getItemCount();
    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView txt_title, txt_body;
        private Button btn;


        public VH(@NonNull View itemView) {
            super(itemView);
            txt_title = itemView.findViewById(R.id.txt_title_post);

            txt_body = itemView.findViewById(R.id.txt_body_post);

            btn = itemView.findViewById(R.id.btn_comment_post);
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
