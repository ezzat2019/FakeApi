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
import java.util.Collection;
import java.util.List;

public class RecyclePostAdapter extends PagedListAdapter<Posts, RecyclePostAdapter.VH> implements Filterable {
    private static Context cont;
    private static ItemClickInterface clickInterface;
    private List<Posts> list = new ArrayList<>();
    private List<Posts> filetr = new ArrayList();
    private static boolean isOn = false;

    public static void setIsOn(boolean isOn) {
        RecyclePostAdapter.isOn = isOn;
    }

    public static boolean isIsOn() {
        return isOn;
    }

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

    @Override
    public void submitList(@Nullable PagedList<Posts> pagedList) {

        super.submitList(pagedList);


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

        if (list.size() == 0) {
            Log.d("sssssss", "list a");
            list.addAll(getCurrentList());


        }


        holder.bind(list.get(position));


    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override

            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("sssssss", "list b");


                filetr.clear();

                for (Posts posts : getCurrentList()) {
                    if (posts.getTitle().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {

                        filetr.add(posts);
                    }

                }


                FilterResults results = new FilterResults();
                results.values = filetr;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                list.clear();
                list.addAll((Collection<? extends Posts>) filterResults.values);
                Log.d("ssssss", list.size() + "  " + filetr.size());
                if (list.size() != 0)
                    notifyDataSetChanged();
                else {
                    list.clear();


                }


                filetr.clear();


            }
        };
    }

    @Override
    public int getItemCount() {

        if (list.size() > 0) {
            return list.size();
        }
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
