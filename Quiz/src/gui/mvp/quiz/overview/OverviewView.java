package gui.mvp.quiz.overview;

import java.util.List;

import gui.mvp.quiz.model.Question;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;

public class OverviewView extends VBox
{
    private OverviewPresenter presenter;

    private TableView<Question> overviewTable;

    public OverviewView()
    {
        initView();
    }

    public void initView()
    {
        getChildren().add(new Label("Übersicht"));
        overviewTable = new TableView<>();
        overviewTable.setId("overviewTable");

        TableColumn<Question, String> questionCol = new TableColumn<>("Frage");
        questionCol.setCellValueFactory(item -> new SimpleStringProperty(item.getValue().getQuestion()));
        questionCol.setPrefWidth(200);
        questionCol.setId("questionCol");
        overviewTable.getColumns().add(questionCol);

        TableColumn<Question, Integer> totalAnswerCol = new TableColumn<>("Antworten");
        totalAnswerCol.setCellValueFactory(item -> new SimpleIntegerProperty(item.getValue().getNumOfAnswers()).asObject());
        // totalAnswerCol.setCellValueFactory(new
        // PropertyValueFactory<>("numOfAnswers"));
        totalAnswerCol.setPrefWidth(260);
        totalAnswerCol.setId("totalAnswerCol");
        overviewTable.getColumns().add(totalAnswerCol);

        TableColumn<Question, Integer> correctAnswerCol = new TableColumn<>("davon richtig");
        correctAnswerCol.setCellValueFactory(item -> new SimpleIntegerProperty(item.getValue().getNumOfCorrectAnswers()).asObject());
        // correctAnswerCol.setCellValueFactory(new
        // PropertyValueFactory<>("numOfCorrectAnswers"));
        correctAnswerCol.setPrefWidth(260);
        correctAnswerCol.setId("correctAnswerCol");
        overviewTable.getColumns().add(correctAnswerCol);

        getChildren().add(overviewTable);

        Button deleteButton = new Button("Ergebnis löschen");
        deleteButton.setOnAction(e -> presenter.delete());
        deleteButton.setId("deleteHistory");
        getChildren().add(deleteButton);
    }

    public void setPresenter(OverviewPresenter presenter)
    {
        this.presenter = presenter;
    }

    public void setList(List<Question> questions)
    {
        overviewTable.getItems().setAll(questions);
    }
}
