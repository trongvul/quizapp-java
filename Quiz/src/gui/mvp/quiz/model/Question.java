package gui.mvp.quiz.model;

public class Question
{
    private String question;

    private String[] possibleAnswers;

    private int indexOfCorrectAnswer, numOfAnswers, numOfCorrectAnswers;

    public Question(String question, String[] possibleAnswers, int indexOfCorrectAnswer)
    {
        if (indexOfCorrectAnswer < 0 || indexOfCorrectAnswer >= possibleAnswers.length)
        {
            throw new IllegalArgumentException();
        }
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.indexOfCorrectAnswer = indexOfCorrectAnswer;
        this.numOfAnswers = 0;
        this.numOfCorrectAnswers = 0;
    }

    public String getQuestion()
    {
        return question;
    }

    public String[] getPossibleAnswers()
    {
        return possibleAnswers;
    }

    public int getIndexOfCorrectAnswer()
    {
        return indexOfCorrectAnswer;
    }

    public void setNumOfAnswers(int numOfAnswers)
    {
        this.numOfAnswers = numOfAnswers;
    }

    public void setNumOfCorrectAnswers(int numOfCorrectAnswers)
    {
        this.numOfCorrectAnswers = numOfCorrectAnswers;
    }

    public int getNumOfAnswers()
    {
        return numOfAnswers;
    }

    public int getNumOfCorrectAnswers()
    {
        return numOfCorrectAnswers;
    }

    public String toString()
    {
        return question;
    }

}
