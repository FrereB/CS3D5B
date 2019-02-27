package trinity.cs3d5b.quizz;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> answers;
    private String correctAnswer;

    Question(String question, String correctAnswer, List<String> answers){
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.answers = new ArrayList<>();
        this.answers.addAll(answers);
    }

    public String getQuestion(){
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
