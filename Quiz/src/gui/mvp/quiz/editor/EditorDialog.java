package gui.mvp.quiz.editor;

import java.util.ArrayList;
import java.util.List;

import gui.mvp.quiz.model.Question;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorDialog extends Dialog<Question>
{
    private VBox answersBox;

    private ToggleGroup toggleGroup;

    private TextArea questionTextArea;

    private List<HBox> answerFieldsList;

    // Edit question
    public EditorDialog(Question editedQuestion)
    {
        answerFieldsList = new ArrayList<>();
        toggleGroup = new ToggleGroup();

        VBox content = new VBox();

        content.getChildren().add(new Label("Frage:"));

        questionTextArea = new TextArea(editedQuestion.getQuestion());
        questionTextArea.setId("dialogQuestion");
        content.getChildren().add(questionTextArea);

        Button addButton = new Button("Antwort hinzuf\u00fcgen");
        addButton.setOnAction(e -> addAnswerField());
        content.getChildren().add(addButton);

        answersBox = new VBox();
        setAnswersField(editedQuestion);
        content.getChildren().add(answersBox);

        getDialogPane().setContent(content);

        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("\u00c4ndern");
        setResultConverter((ButtonType b) -> makeQuestion(b));
    }

    // Add question
    public EditorDialog()
    {
        answerFieldsList = new ArrayList<>();
        toggleGroup = new ToggleGroup();

        VBox content = new VBox();
        content.getChildren().add(new Label("Fragen:"));

        questionTextArea = new TextArea();
        questionTextArea.setId("dialogQuestion");
        content.getChildren().add(questionTextArea);

        Button addButton = new Button("Antwort hinzuf\u00fcgen");
        addButton.setOnAction(e -> addAnswerField());
        content.getChildren().add(addButton);

        answersBox = new VBox();
        content.getChildren().add(answersBox);

        getDialogPane().setContent(content);
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        Button okButton = (Button) getDialogPane().lookupButton(ButtonType.OK);
        okButton.setText("Hinzuf\u00fcgen");
        Button cancelButton = (Button) getDialogPane().lookupButton(ButtonType.CANCEL);
        cancelButton.setText("Abbrechen");
        setResultConverter((ButtonType b) -> makeQuestion(b));
    }

    private void addAnswerField()
    {
        HBox answerField = new HBox();
        String id = Integer.toString(answerFieldsList.size());

        RadioButton rb = new RadioButton();
        rb.setId(id);
        rb.setToggleGroup(toggleGroup);
        answerField.getChildren().add(rb);

        TextField tf = new TextField();
        answerField.getChildren().add(tf);

        Button deleteButton = new Button("L\u00f6schen");
        deleteButton.setId(id);
        deleteButton.setOnAction(e -> deleteAnswerField(e));
        answerField.getChildren().add(deleteButton);

        answersBox.getChildren().add(answerField);
        answerFieldsList.add(answerField);
    }

    private Question makeQuestion(ButtonType b)
    {
        if (b == ButtonType.OK)
        {
            String question = questionTextArea.getText();
            String[] possibleAnswers = new String[answerFieldsList.size()];
            for (int i = 0; i < answerFieldsList.size(); i++)
            {
                possibleAnswers[i] = ((TextField) answerFieldsList.get(i).getChildren().get(1)).getText();

            }
            int indexOfCorrectAnswer = getSelectedIndex();
            return new Question(question, possibleAnswers, indexOfCorrectAnswer);
        }
        return null;
    }

    public void setAnswersField(Question question)
    {
        HBox answerBar;
        Button deleteButton;
        String[] possibleAnswers = question.getPossibleAnswers();
        for (int i = 0; i < possibleAnswers.length; i++)
        {
            answerBar = new HBox();
            RadioButton rb = new RadioButton();
            rb.setToggleGroup(toggleGroup);
            rb.setId(i + "");
            if (i == question.getIndexOfCorrectAnswer())
            {
                rb.setSelected(true);
            }
            TextField tf = new TextField(possibleAnswers[i]);
            deleteButton = new Button("L\u00f6schen");
            deleteButton.setId(i + "");
            deleteButton.setOnAction(e -> deleteAnswerField(e));
            answerBar.getChildren().addAll(rb, tf, deleteButton);
            answersBox.getChildren().add(answerBar);
            answerFieldsList.add(answerBar);
        }
    }

    public void deleteAnswerField(ActionEvent e)
    {
        int i = Integer.parseInt(((Button) e.getSource()).getId());
        answerFieldsList.remove(i);
        answersBox.getChildren().remove(i);
    }

    public int getSelectedIndex()
    {
        RadioButton rb = (RadioButton) toggleGroup.getSelectedToggle();
        return rb != null ? Integer.parseInt(rb.getId()) : -1;
    }
}
