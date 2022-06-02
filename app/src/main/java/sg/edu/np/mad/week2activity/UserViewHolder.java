package sg.edu.np.mad.week2activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView nameTxt;
    TextView descTxt;
    CardView cardView;
    CardView sevenCardView;

    public UserViewHolder(View itemView)
    {
        super(itemView);
        nameTxt = itemView.findViewById(R.id.nameTxt);
        descTxt = itemView.findViewById(R.id.descText);
        cardView = itemView.findViewById(R.id.imgCardView);
        sevenCardView = itemView.findViewById(R.id.sevenCardView);
    }
}
