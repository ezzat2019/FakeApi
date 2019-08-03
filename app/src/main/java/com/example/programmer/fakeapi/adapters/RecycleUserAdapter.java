package com.example.programmer.fakeapi.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.programmer.fakeapi.R;
import com.example.programmer.fakeapi.models.Users;

import java.util.List;

public class RecycleUserAdapter extends RecyclerView.Adapter<RecycleUserAdapter.VH> {
    private static Got clickInterface;
    private List<Users> list;

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("steeeeep", "step on create view holder 1");

        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_no_detiel, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        Log.d("steeeeep", "step on bind  2 ");

        holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        Log.d("steeeeep", "step on acount item 3");

        return list.size();
    }

    public void setList(List<Users> list) {
        Log.d("steeeeep", "step on set list  4");

        this.list = list;

        notifyDataSetChanged();
    }

    public void setOnItem(Got itemClickInterface) {
        clickInterface = itemClickInterface;
    }

    public interface Got {
        void posss(int poss);
    }

    public static class VH extends RecyclerView.ViewHolder {
        private TextView txtUserId, txtName, txtuserName, txtEmail;

        public VH(@NonNull View itemView) {
            super(itemView);

            txtUserId = itemView.findViewById(R.id.txt_u_id_n);

            txtName = itemView.findViewById(R.id.txt_u_name_n);

            txtuserName = itemView.findViewById(R.id.txt_u_user_name_n);

            txtEmail = itemView.findViewById(R.id.txt_u_user_email_n);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickInterface.posss(getAdapterPosition());
                }
            });
        }

        public void bind(Users users) {
            Log.d("steeeeep", "step bind objects 11");

            txtUserId.setText(users.getId() + "");
            txtEmail.setText(users.getEmail());
            txtuserName.setText(users.getUsername());
            txtName.setText(users.getName());
        }
    }
}
