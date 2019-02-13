package trinity.cs3d5b.quizz.utilities;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import trinity.cs3d5b.quizz.R;

public class JsonParser {
    private Context context;
    private InputStream is;

    public JsonParser(Context current){
        this.context = current;
        this.is = context.getResources().openRawResource(R.raw.leaderboard);
    }

    public JSONArray getLeaderBoard(){
        String result = "";
        JSONArray jsonArray = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            result = sb.toString();
        } catch (Exception e) {
        }
        try {
            JSONObject json = new JSONObject(result);
            jsonArray = json.getJSONArray("leaderboard");
        } catch (Exception e){}
        return jsonArray;
    }
}
