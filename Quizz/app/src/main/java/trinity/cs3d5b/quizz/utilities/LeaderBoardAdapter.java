package trinity.cs3d5b.quizz.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import trinity.cs3d5b.quizz.R;

public class LeaderBoardAdapter extends ArrayAdapter<JSONObject> {

    List<JSONObject> json;
    Context context;
    private LayoutInflater mInflater;

    public LeaderBoardAdapter(Context context, List<JSONObject> json){
        super(context, 0, json);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.json = json;
    }

    @Override
    public JSONObject getItem(int position) {
        return json.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE );
        if (convertView == null ) {
            convertView = inflater.inflate(R.layout.leaderboard_line, null );
        }

        JSONObject jsonObject =getItem(position);
        try {
            ((TextView) convertView.findViewById(R.id.name)).setText(jsonObject.getString("name"));
            ((TextView) convertView.findViewById(R.id.scored)).setText(jsonObject.getString("score"));
        } catch (Exception e){}

        return convertView;
    }


}
