package gui.mvp.quiz.game;

import gui.mvp.quiz.model.Question;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class QuizView extends GridPane
{
    private QuizPresenter presenter;

    private Label questionLabel;

    private VBox answersBox;

    private ToggleGroup toggleGroup;

    private Button nextButton;

    public QuizView()
    {
        initView();
    }

    public void initView()
    {
        questionLabel = new Label();
        questionLabel.setFont(new Font("Arial", 22));
        questionLabel.setId("question");
        add(questionLabel, 0, 0);

        answersBox = new VBox(10);
        answersBox.setPadding(new Insets(10));
        add(answersBox, 0, 1);

        nextButton = new Button("=>");
        nextButton.setOnAction(e -> presenter.next());
        add(nextButton, 0, 5);
    }

    public void setPresenter(QuizPresenter presenter)
    {
        this.presenter = presenter;
    }

    public void setQuestion(Question question)
    {
        answersBox.getChildren().clear();
        RadioButton option;
        toggleGroup = new ToggleGroup();
        questionLabel.setText(question.getQuestion());
        for (int i = 0; i < question.getPossibleAnswers().length; i++)
        {
            option = new RadioButton(question.getPossibleAnswers()[i]);
            option.setToggleGroup(toggleGroup);
            option.setId(Integer.toString(i));
            answersBox.getChildren().add(option);
        }
        nextButton.setDisable(false);

    }

    public void showEndQuiz()
    {
        questionLabel.setText("Ende des Quiz");
        answersBox.getChildren().clear();
        nextButton.setDisable(true);
    }

    public int getIndexOfSelectedAnswer()
    {
        RadioButton b = (RadioButton) toggleGroup.getSelectedToggle();
        return b != null ? Integer.parseInt(b.getId()) : -1;
    }
}
