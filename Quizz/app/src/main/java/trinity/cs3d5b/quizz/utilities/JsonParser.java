package trinity.cs3d5b.quizz.utilities;

import android.content.Context;
import android.net.Uri;
import android.util.JsonWriter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;

import trinity.cs3d5b.quizz.R;

public class JsonParser {
    private Context context;
    private InputStream is;

    public JsonParser(Context current){
        this.context = current;

    }

    private JSONObject getJSon(){
        JSONObject json = null;
        try {
            try {
                is = context.openFileInput("leaderboard.json");
            } catch (FileNotFoundException e){
                createFile();
                is = context.openFileInput("leaderboard.json");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            json = new JSONObject(sb.toString());
            is.close();
        } catch (Exception e) {
        } finally {
            return json;
        }
    }

    private void createFile(){
        try {
            File file = new  File(context.getFilesDir(), "leaderboard.json");
            OutputStream out = context.openFileOutput("leaderboard.json", Context.MODE_PRIVATE);
            out.write("{ \"leaderboard\" : [ ] }".getBytes());
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JSONArray getLeaderBoard(){
        String result = "";
        JSONArray jsonArray = null;
        try {
            jsonArray = getJSon().getJSONArray("leaderboard");
        } catch (Exception e){}
        return jsonArray;
    }


    public void addToLeaderBoard(String name, String score, String picture){
        try {
            FileOutputStream outputStream;
            try {
                JSONArray array = getJSon().getJSONArray("leaderboard");
                JSONObject json = new JSONObject();
                JSONArray nArray = new JSONArray();
                JSONObject nScore = new JSONObject();
                nScore.put("name",name);
                nScore.put("score",score);
                nScore.put("picture",picture);
                for(int x=0;x<array.length();x++){
                    if(!array.getJSONObject(x).getString("name").equals(name)){
                        nArray.put(array.getJSONObject(x));
                    } else if(array.getJSONObject(x).getInt("score")>(Integer.parseInt(score))){
                        score = array.getJSONObject(x).getString("score");
                        nScore.put("score",score);
                    }
                }
                nArray.put(nScore);
                json.put("leaderboard",nArray);
                outputStream = context.openFileOutput("leaderboard.json", Context.MODE_PRIVATE);
                outputStream.write(json.toString().getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
