package trinity.cs3d5b.quizz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuestionLibrary {

    private String questions[] = {"","","","","","","","","",""};

    private String choices[][] = {
            {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""},
            {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""}, {"", "", "", ""},{"", "", "", ""}
    };

    private String correctAnswers[]= {"","","","","","","","","",""};


    public QuestionLibrary(String Qlibrary){

        if(Qlibrary.equals("General Knowledge")){

            questions[0] = "What is the scientific name of lie detector which is sometimes used by the police for detecting lies?";
            questions[1] = "What was Margaret Thatcher's middle name?";
            questions[2] = "Which is the only letter in Scrabble to have a value of five points?";
            questions[3] = "A song by Bag Raiders became famous after being used in making memes" +
                    " of people falling over. What is this song called?";
            questions[4] = "What is the best Matt Groening TV show?";
            questions[5] = "Which sport uses terms like 'Over', 'Bowl', 'Stumps' and 'Duck'";
            questions[6] = "'Hey _____, don't make it bad. Take a sad song and make it better'. Finish the lyric";
            questions[7] = "A red coating was often used for live electrical wires. Why was this?";
            questions[8] = "Who played Agent 007 in the 2006 film 'Casino Royale'?";
            questions[9] = "Which famous band had a drummer called John Bonham?";

            choices[0][0] = "Porygon"; choices[0][1] = "Polygamist"; choices[0][2] = "Polygraph"; choices[0][3] = "Popcorn";
            choices[1][0] = "Hilda"; choices[1][1] = "Gertrude"; choices[1][2] = "Peggy"; choices[1][3] = "Davy";
            choices[2][0] = "R"; choices[2][1] = "Q"; choices[2][2] = "J"; choices[2][3] = "K";
            choices[3][0] = "Falling Slowly"; choices[3][1] = "Tubthumping"; choices[3][2] = "Shooting Star"; choices[3][3] = "Rats";
            choices[4][0] = "Disenchantment"; choices[4][1] = "Clerks"; choices[4][2] = "Futurama"; choices[4][3] = "The Simpsons";
            choices[5][0] = "Cricket"; choices[5][1] = "Quidditch"; choices[5][2] = "Croquet"; choices[5][3] = "Bowling";
            choices[6][0] = "Dude"; choices[6][1] = "Jude"; choices[6][2] = "Prude"; choices[6][3] = "Snood";

            choices[7][0] = "Electricity is actually coloured red";
            choices[7][1] = "Electricity is the blood of machines";
            choices[7][2] = "Electricity was invented by the Communist Party";
            choices[7][3] = "Electricity Standards in the US and UK used to specify this";

            choices[8][0] = "Craig David"; choices[8][1] = "Daniel Craig" ; choices[8][2] = "Craig Robinson"; choices[8][3] = "Daniel Radcliffe";
            choices[9][0] = "The Who"; choices[9][1] = "System of a Down"; choices[9][2] = "Led Zeppelin"; choices[9][3] = "Dire Straits";

            correctAnswers[0] = "Polygraph";
            correctAnswers[1] = "Hilda";
            correctAnswers[2] = "K";
            correctAnswers[3] = "Shooting Star";
            correctAnswers[4] = "Futurama";
            correctAnswers[5] = "Cricket";
            correctAnswers[6] = "Jude";
            correctAnswers[7] = "Electricity Standards in the US and UK used to specify this";
            correctAnswers[8] = "Daniel Craig";
            correctAnswers[9] = "Led Zeppelin";
        }
        else if(Qlibrary.equals("Engineering")){

        }
        else if(Qlibrary.equals("Stuff")){

        }
        else if(Qlibrary.equals("Other stuff")){

        }
        else{

        }

    }

    private int numberOfQuestions = correctAnswers.length;

    public Question getQuestion(int questionNb){
        List<String> list = new ArrayList<>();
        list.addAll(Arrays.asList(choices[questionNb]).subList(0, 4));
        return new Question(questions[questionNb],correctAnswers[questionNb],list);
    }

    public int getNumberOfQuestions(){
        return numberOfQuestions;
    }

}
