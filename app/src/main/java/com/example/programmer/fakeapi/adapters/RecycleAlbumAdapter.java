package com.example.programmer.fakeapi.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.ui.ItemClickInterface;

import java.util.ArrayList;
import java.util.List;

public class RecycleAlbumAdapter extends RecyclerView.Adapter<RecycleAlbumAdapter.VH> implements Filterable {
    private static ItemClickInterface clickInterface;
    private List<Album> list;
    private List<Album> listAll;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("steeeeep", "step on create view holder 1");

        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Log.d("steeeeep", "step on bind  2 ");

        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        Log.d("steeeeep", "step on acount item 3");

        return listAll.size();
    }

    public void setList(List<Album> list) {
        Log.d("steeeeep", "step on set list  4");

        this.list = list;
        this.listAll = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public void setOnItem(ItemClickInterface itemClickInterface) {
        clickInterface = itemClickInterface;
    }


    @Override
    public Filter getFilter() {
        Log.d("steeeeep", "step on get filter start 5");

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("steeeeep", "step on filter  check  6");


                if (charSequence.toString().isEmpty()) {
                    listAll.addAll(list);
                    Log.d("steeeeep", "step list is empty 7");


                } else {
                    List<Album> filter = new ArrayList<>();
                    Log.d("steeeeep", "step on  lis is full 8");


                    for (Album album : list) {
                        if (album.getTitle().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            filter.add(album);

                            Log.d("ccccc", album.getTitle());

                        }
                    }

                    listAll = filter;

                }

                Log.d("steeeeep", "step on on finsh rescheck 8");

                FilterResults results = new FilterResults();
                results.values = listAll;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Log.d("steeeeep", "step ennnnnnnd 9");
                if (listAll != null) {

                    listAll = (List<Album>) filterResults.values;
                    notifyDataSetChanged();

                }


            }
        };
    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView txtUserId, txtId, txtName;

        public VH(@NonNull View itemView) {
            super(itemView);

            txtId = itemView.findViewById(R.id.txt_id);
            txtUserId = itemView.findViewById(R.id.txt_user_id);
            txtName = itemView.findViewById(R.id.txt_body_album);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onClick(getAdapterPosition());
                }
            });
        }

        public void bind(Album album) {
            Log.d("steeeeep", "step bind objects 11");

            txtName.setText(album.getTitle());
            txtUserId.setText(album.getUserId() + "");
            txtId.setText(album.getId() + "");
        }
    }
}
