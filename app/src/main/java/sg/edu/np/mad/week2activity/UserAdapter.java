package sg.edu.np.mad.week2activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {
    ArrayList<User> userList;
    SelectUserListener listener;
    public UserAdapter(ArrayList<User> userList, SelectUserListener listener) {
        this.userList = userList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int itemType)
    {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new UserViewHolder(item);
    }

    public void onBindViewHolder(UserViewHolder holder, int position)
    {
        User user = userList.get(position);
        holder.nameTxt.setText(user.getName());
        holder.descTxt.setText(user.getDescription());

        /*
        //Last Digit 7 Condition
        if (Integer.valueOf(user.getName()) % 10 == 7)
        {
            holder.sevenCardView.setVisibility(View.INVISIBLE);
        }
         */

        //Click on User Image
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(userList.get(position));
            }
        });
    }

    public int getItemCount() {
        return userList.size();
    }
}
