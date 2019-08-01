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
import com.example.programmer.fakeapi.models.Album;
import com.example.programmer.fakeapi.models.Photo;
import com.example.programmer.fakeapi.models.Posts;
import com.example.programmer.fakeapi.ui.ItemClickInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RecycleAlbumAdapter extends RecyclerView.Adapter <RecycleAlbumAdapter.VH> implements Filterable {
    private List<Album>list;
    private List<Album> listAll;
    private static ItemClickInterface clickInterface;

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                if (charSequence.toString().isEmpty()) {
                    listAll.addAll(list);

                } else {
                    List<Album> filter = new ArrayList<>();

                    for (Album album : listAll) {
                        if (album.getTitle().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            filter.add(album);

                            Log.d("ccccc",album.getTitle());

                        }
                    }
                   
                    listAll=filter;

                }
                FilterResults results=new FilterResults();
                results.values=listAll;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
              listAll= (List<Album>) filterResults.values;
               notifyDataSetChanged();



                Log.d("ccccc",listAll.size()+"");

            }
        };
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return listAll.size();
    }
    public void setList(List<Album> list)
    {
        this.list=list;
        this.listAll = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public void setOnItem(ItemClickInterface itemClickInterface) {
        clickInterface=itemClickInterface;
    }

    public static class VH extends RecyclerView.ViewHolder
   {
       private TextView txtUserId,txtId,txtName;

       public VH(@NonNull View itemView) {
           super(itemView);

           txtId=itemView.findViewById(R.id.txt_id);
           txtUserId=itemView.findViewById(R.id.txt_user_id);
           txtName=itemView.findViewById(R.id.txt_body_album);
           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   clickInterface.onClick(getPosition());
               }
           });
       }

       public void bind(Album album)
       {
           txtName.setText(album.getTitle());
           txtUserId.setText(album.getUserId()+"");
           txtId.setText(album.getId()+"");
       }
   }
}
