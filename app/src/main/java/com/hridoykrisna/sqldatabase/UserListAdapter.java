package com.hridoykrisna.sqldatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hridoykrisna.sqldatabase.DB.User;

import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private Context context;
    private List<User> userList;
    public UserListAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public void setUserList(List<User> userList) {

        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public UserListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.ViewHolder holder, int position) {
        holder.nameTVId.setText(userList.get(position).getFirstName()+" "+userList.get(position).getLastName());
        holder.mobileTVId.setText(userList.get(position).getMobile());
    }

    @Override
    public int getItemCount() {
       try {
           return userList.size();
       } catch (Exception e){
           e.printStackTrace();
           return 0;
       }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mobileTVId, nameTVId;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTVId = itemView.findViewById(R.id.nameTVId);
            mobileTVId = itemView.findViewById(R.id.mobileTVId);
        }
    }
}
