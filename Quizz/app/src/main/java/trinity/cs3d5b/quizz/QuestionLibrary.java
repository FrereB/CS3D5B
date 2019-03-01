package trinity.cs3d5b.quizz;

import android.content.Context;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class QuestionLibrary {

    private final Context context;
    private String questions[] = {"","","","","","","","","",""};

    private String choices[][] = {
            {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""},
            {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""},{"", "", "", ""}
    };

    private String correctAnswers[]= {"a","a","a","a","a","a","a","a","a","a"};

    public QuestionLibrary(Context current){
        this.context = current;
    }


    public void setQuestionLibrary(String Qlibrary){

        String tempStrings[];
        BufferedReader br = null;
        String line = "";
        String csvSplitBy = ",";
        InputStream istream;

        int numberOfQsInDB;


        if(Qlibrary.equals("General Knowledge")){
            istream = context.getResources().openRawResource(R.raw.general_knowledge);
            numberOfQsInDB = 22;
        }

        else if(Qlibrary.equals("21st Century")){
            istream = context.getResources().openRawResource(R.raw.the_21st_century);
            numberOfQsInDB = 10;
        }

        else if(Qlibrary.equals("Crazy Laws")){
            istream = context.getResources().openRawResource(R.raw.crazy_laws);
            numberOfQsInDB = 10;
        }

        else{
            istream = context.getResources().openRawResource(R.raw.general_knowledge);
            numberOfQsInDB = 10;
        }

        try {
            int i = 0, j = 0;
            br = new BufferedReader(new InputStreamReader(istream));
            int[] questionOrder = randomNumberArray(numberOfQsInDB);


            while ((line = br.readLine()) != null && j < 10) {

                for(int k = 0; k < 10; k++){
                    if(questionOrder[k] == i){
                        tempStrings = line.split(csvSplitBy);

                        questions[k] = tempStrings[0];
                        choices[k][0] = tempStrings[1];
                        choices[k][1] = tempStrings[2];
                        choices[k][2] = tempStrings[3];
                        choices[k][3] = tempStrings[4];
                        correctAnswers[k] = tempStrings[5];
                        j++;
                    }
                }
                i++;
            }


        }catch (FileNotFoundException e) {
            questions[0] = "File not found exception";
            e.printStackTrace();
        } catch (IOException e) {
            questions[0] = "IO exception";
            e.printStackTrace();
        }finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private int numberOfQuestions = correctAnswers.length;

    public int getNumberOfQuestions(){ return numberOfQuestions; }

    public String getQuestion(int a) { return questions[a]; }

    public String getChoice1(int a) { return choices[a][0]; }

    public String getChoice2(int a) { return choices[a][1]; }

    public String getChoice3(int a) { return choices[a][2]; }

    public String getChoice4(int a) { return choices[a][3]; }

    public String getCorrectAnswer(int a) { return correctAnswers[a]; }

    private int[] randomNumberArray(int size){

        int temp;
        Random rand = new Random();

        int arr[] =  {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        for(int j = 0; j < 10; j++){
            temp = rand.nextInt(size);

            for(int k = 0; k < j; k++){
                if(arr[k] == temp){
                    temp = rand.nextInt(size);
                    k = -1;
                }
            }
            arr[j] = temp;
        }
        return arr;
    }

}
