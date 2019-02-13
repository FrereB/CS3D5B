package trinity.cs3d5b.quizz;

public class QuestionLibrary {

    private String questions[] = {
            "What are the odds of you guessing this question correctly?",
            "Which part of the plant holds it in the soil?",
            "This part of the plant absorbs energy from the sun.",
            "This part of the plant attracts bees, butterflies and hummingbirds.",
            "The _______ holds the plant upright."

    };


    private String choices[][] = {
            {"25%", "50%", "75%", "25%"},
            {"Roots", "Stem", "Flower", "Potato"},
            {"Fruit", "Leaves", "Seeds", "Potato"},
            {"Bark", "Flower", "Roots", "Potato"},
            {"Flower", "Leaves", "Stem", "Potato"}
    };


    private String correctAnswers[] = {"","Roots", "Leaves", "Flower", "Stem"};

    private int numberOfQuestions = correctAnswers.length;

    public int getNumberOfQuestions(){
        return numberOfQuestions;
    }

    public String getQuestion(int a) {
        return questions[a];
    }


    public String getChoice1(int a) {
        return choices[a][0];
    }


    public String getChoice2(int a) {
        return choices[a][1];
    }

    public String getChoice3(int a) {
        return choices[a][2];
    }

    public String getChoice4(int a) {
        return choices[a][3];
    }

    public String getCorrectAnswer(int a) {
        return correctAnswers[a];
    }

}
