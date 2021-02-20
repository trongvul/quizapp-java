package gui.mvp.quiz;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import gui.mvp.quiz.model.Model;
import gui.mvp.quiz.model.Question;

public final class ModelInitializer
{

    private static final char ANSWER_STARTS_SYMBOL = '{';

    private static final char ANSWER_ENDS_SYMBOL = '}';

    private static final char ANSWER_SEPERATION_SYMBOL = ';';

    /**
     * Die Methode erzeugt das Modell und f�gt alle Fragen, falls sie aus der
     * Datei ausgelesen werden k�nnen, zum Modell hinzu.
     * 
     * @param pathName
     *            Dateipfad der Datei, in der die Quizfragen erwartet werden.
     * @return <code>Model</code> der Quizaufgabe
     * @throws IOException
     *             wird geworfen, wenn aus irgendeinem Grund das Einlesen der
     *             Datei nicht funktioniert. Hierzu geh�rt u.a die
     *             {@link FileNotFoundException}.
     * @throws @throws
     *             NumberFormatException wird geworfen, wenn eine Dateizeile
     *             nicht interpretiert werden kann, weil der Index f�r die
     *             korrekte Antwort nicht eingelesen werden konnte.
     * @throws IndexOutOfBoundsException
     *             wird geworfen, wenn eine Dateizeile nicht interpretiert
     *             werden kann, weil mind. eine der geschweiften Klammern nicht
     *             oder an falscher Position gefunden wird.
     * @throws IllegalArgumentException
     *             wird beim Erzeugen einer <code>Question</code> geworfen, wenn
     *             deren Argumente ung�ltig sind.
     */
    public static Model initModel(String pathName) throws IOException
    {
        Model m = new Model();

        try (BufferedReader in = new BufferedReader(new FileReader(pathName)))
        {
            String line;
            while ((line = in.readLine()) != null)
            {
                try
                {
                    Question q = readQuestion(line);
                    m.addQuestion(q);

                }
                catch (NumberFormatException | IndexOutOfBoundsException e)
                {
                    System.err.println("Die Zeile \"" + line + "\" konnte nicht interpretiert werden.\n");
                    throw e;
                }

            }
        }

        return m;
    }

    private static Question readQuestion(String line)
    {
        int indexAnswerStart = line.indexOf(ANSWER_STARTS_SYMBOL);
        int indexAnswerEnds = line.indexOf(ANSWER_ENDS_SYMBOL, indexAnswerStart);

        checkIndices(indexAnswerStart, indexAnswerEnds);

        String question = getQuestion(line, indexAnswerStart);
        String[] possibleAnswers = getPossibleAnswers(line.substring(indexAnswerStart + 1, indexAnswerEnds + 1));
        int indexOfCorrectAnswer = getIndexOfCorrectAnswer(line.substring(indexAnswerEnds + 1));

        return new Question(question, possibleAnswers, indexOfCorrectAnswer);
    }

    private static void checkIndices(int indexAnswerStart, int indexAnswerEnds)
    {
        if (indexAnswerStart <= 0)
        {
            throw new IndexOutOfBoundsException("Es konnte kein (oder nur an erster Stelle) Symbol \"" + ANSWER_STARTS_SYMBOL + "\" zur Kennzeichnung des Antwortenbeginns gefunden werden. " + "Demzufolge k�nnen weder Frage noch Antwortm�glichkeiten klar voneinander abgegrenzt werden.");
        }
        if (indexAnswerEnds < 0 || indexAnswerEnds <= indexAnswerStart)
        {
            throw new IndexOutOfBoundsException("Es konnte kein Symbol \"" + ANSWER_ENDS_SYMBOL + "\" zur Kennzeichnung des Endes der Antwortm�glichkeiten hinter den Antworten gefunden werden.");
        }
    }

    private static String getQuestion(String line, int questionEndsIndex)
    {
        return line.substring(0, questionEndsIndex).trim();
    }

    private static String[] getPossibleAnswers(String answerString)
    {
        int index;
        List<String> answers = new LinkedList<String>();
        while ((index = answerString.indexOf(ANSWER_SEPERATION_SYMBOL)) != -1)
        {
            answers.add(answerString.substring(0, index).trim());
            answerString = answerString.substring(index + 1);
        }
        answerString = answerString.substring(0, answerString.length() - 1);
        answers.add(answerString.trim());
        return answers.toArray(new String[answers.size()]);
    }

    private static int getIndexOfCorrectAnswer(String s)
    {
        return Integer.parseInt(s.replaceAll(",", "").trim());
    }

}
