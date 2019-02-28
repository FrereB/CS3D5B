package trinity.cs3d5b.quizz.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import trinity.cs3d5b.quizz.R;
import trinity.cs3d5b.quizz.database.UserModel;

public class LeaderBoardAdapter extends ArrayAdapter<UserModel> {

    List<UserModel> items;
    Context context;
    private LayoutInflater mInflater;

    public LeaderBoardAdapter(Context context, List<UserModel> json){
        super(context, 0, json);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.items = json;
    }

    @Override
    public UserModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        if (convertView == null ) {
            convertView = inflater.inflate(R.layout.leaderboard_line, null );
        }

        UserModel userModel = getItem(position);
        try {
            ((TextView) convertView.findViewById(R.id.name)).setText(userModel.getName());
            ((TextView) convertView.findViewById(R.id.score)).setText(Integer.toString(userModel.getHighScore()));
            int id = context.getResources().getIdentifier(userModel.getProfilePicture(), "drawable", context.getPackageName());
            ((ImageView) convertView.findViewById(R.id.picture)).setImageResource(id);
        } catch (Exception e){}

        return convertView;
    }


}
