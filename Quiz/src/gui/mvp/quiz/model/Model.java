package gui.mvp.quiz.model;

import java.util.ArrayList;
import java.util.List;

public class Model
{
    private List<Question> questions;

    private int aktIndex = 0;

    public Model()
    {
        questions = new ArrayList<>();
    }

    public void addQuestion(Question question)
    {
        questions.add(question);
    }

    public void addQuestion(int index, Question question)
    {
        questions.add(index, question);
    }

    public Question getCurrentQuestion()
    {
        return questions.get(aktIndex);
    }

    public void resetIndex()
    {
        aktIndex = 0;
    }

    public boolean next()
    {
        aktIndex++;
        return aktIndex < questions.size();
    }

    public void deleteHistory()
    {
        for (Question question : questions)
        {
            question.setNumOfAnswers(0);
            question.setNumOfCorrectAnswers(0);
        }
    }

    public void countAllAnswers()
    {
        Question question = questions.get(aktIndex);
        question.setNumOfAnswers(question.getNumOfAnswers() + 1);
    }

    public void countCorrectAnswers()
    {
        Question question = questions.get(aktIndex);
        question.setNumOfCorrectAnswers(question.getNumOfCorrectAnswers() + 1);
    }

    public List<Question> getQuestions()
    {
        return questions;
    }

    public void deleteQuestion(Question question)
    {
        questions.remove(question);
    }

    public void updateQuestion(Question oldQuestion, Question newQuestion)
    {
        int i = questions.indexOf(oldQuestion);
        questions.set(i, newQuestion);

    }

    public int getIndexOf(Question question)
    {
        return questions.indexOf(question);
    }
}
