package com.example.programmer.fakeapi.adapters.view_pager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.models.Photo;
import com.example.programmer.fakeapi.ui.ItemClickInterface;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclePhotoAdapter extends RecyclerView.Adapter<RecyclePhotoAdapter.VH> {
    private List<Photo> list;

    private static Context con;
    private static ItemClickInterface clickInterface;

    public RecyclePhotoAdapter(Context con1) {
        con = con1;
    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Photo> list) {
        this.list = list;

        notifyDataSetChanged();
    }

    public void setOnItem(ItemClickInterface itemClickInterface) {
        clickInterface = itemClickInterface;
    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView txtUserId;
        private CircleImageView circleImageView;
        private ImageView imageView;

        public VH(@NonNull View itemView) {
            super(itemView);
            txtUserId = itemView.findViewById(R.id.txt_photo_id);
            circleImageView = itemView.findViewById(R.id.photo_image);
            imageView = itemView.findViewById(R.id.big_photo);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.onClick(getPosition());
                }
            });
        }

        public void bind(Photo photo) {
            txtUserId.setText(photo.getAlbumId() + "");
            Glide.with(con.getApplicationContext()).load(photo.getThumbnailUrl()).into(circleImageView);
            Glide.with(con.getApplicationContext()).load(photo.getUrl()).into(imageView);

        }
    }
}
