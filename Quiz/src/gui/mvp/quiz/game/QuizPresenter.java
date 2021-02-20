package gui.mvp.quiz.game;

import gui.mvp.quiz.model.Model;
import gui.mvp.quiz.model.Question;

public class QuizPresenter
{
    private QuizView view;

    private Model model;

    public QuizPresenter()
    {

    }

    public void setView(QuizView view)
    {
        this.view = view;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public QuizView getView()
    {
        return view;
    }

    public void setFirstQuestion()
    {
        model.resetIndex();
        setQuestion();
    }

    public void setQuestion()
    {
        Question question = model.getCurrentQuestion();
        view.setQuestion(question);
    }

    public void checkAnswer()
    {
        Question question = model.getCurrentQuestion();
        int i = view.getIndexOfSelectedAnswer();
        if (i != -1)
        {
            if (i == question.getIndexOfCorrectAnswer())
            {

                model.countCorrectAnswers();
            }
            model.countAllAnswers();

        }
    }

    public void next()
    {
        checkAnswer();
        if (model.next())
        {
            setQuestion();
        }
        else
        {
            view.showEndQuiz();
        }

    }
}
