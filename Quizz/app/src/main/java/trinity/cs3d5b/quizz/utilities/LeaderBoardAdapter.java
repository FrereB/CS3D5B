package trinity.cs3d5b.quizz.utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import trinity.cs3d5b.quizz.R;
import trinity.cs3d5b.quizz.database.PictureEncoder;
import trinity.cs3d5b.quizz.database.UserModel;

import static trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.PICTURE_TYPE_AVATAR;
import static trinity.cs3d5b.quizz.database.UserSchema.COLUMNS.PICTURE_TYPE_UPLOAD;

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

            String pictureType = userModel.getProfilePictureType();
            switch (pictureType) {
                case PICTURE_TYPE_AVATAR:
                    int id = context.getResources().getIdentifier(userModel.getProfilePictureData(), "drawable", context.getPackageName());
                    ((ImageView) convertView.findViewById(R.id.picture)).setImageResource(id);
                    break;
                case PICTURE_TYPE_UPLOAD:
                    String base64 = userModel.getProfilePictureData();
                    Bitmap bitmap = new PictureEncoder().decodeBase64ToBitmap(base64);
                    ((ImageView) convertView.findViewById(R.id.picture)).setImageBitmap(bitmap);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown picture type: " + pictureType);
            }

        } catch (Exception e){}

        return convertView;
    }


}
