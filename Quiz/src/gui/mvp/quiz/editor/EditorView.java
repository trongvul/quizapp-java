package gui.mvp.quiz.editor;

import java.util.List;
import java.util.Optional;

import gui.mvp.quiz.model.Question;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditorView extends VBox
{
    private EditorPresenter presenter;

    private ListView<Question> editorList;

    public EditorView()
    {
        initView();
    }

    public void initView()
    {
        getChildren().add(new Label("Editor"));
        editorList = new ListView<Question>();
        editorList.setId("editorList");
        editorList.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> presenter.selectedQuestion(newValue));
        getChildren().add(editorList);

        HBox buttonBar = new HBox();
        Button addButton = new Button("Frage Hinzuf\u00fcgen");
        addButton.setId("addQuestion");
        addButton.setOnAction(e -> presenter.addQuestion());
        Button editButton = new Button("Frage editieren");
        editButton.setId("editQuestion");
        editButton.setOnAction(e -> presenter.editQuestion());
        Button deleteButton = new Button("Frage l\u00f6schen");
        deleteButton.setId("deleteQuestion");
        deleteButton.setOnAction(e -> presenter.deleteQuestion());
        buttonBar.getChildren().addAll(addButton, editButton, deleteButton);
        getChildren().add(buttonBar);
    }

    public void showInfo()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Es muss eine Frage ausgewählt werden!");
        alert.showAndWait();
    }

    public boolean showDeleteConfirmation()
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Frage");
        alert.setHeaderText(null);
        alert.setContentText("Soll diese Frage wirklich gelöscht werden?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
        {
            if (result.get() == ButtonType.OK)
            {
                return true;
            }
        }
        return false;
    }

    public Question showEditDialog(Question question)
    {
        EditorDialog editorDialog = new EditorDialog(question);
        Optional<Question> result = editorDialog.showAndWait();
        if (result.isPresent())
        {
            return result.get();
        }
        return null;

    }

    public Question showAddQuestionDialog()
    {
        EditorDialog editorDialog = new EditorDialog();
        Optional<Question> result = editorDialog.showAndWait();
        if (result.isPresent())
        {
            return result.get();
        }
        return null;
    }

    public void setPresenter(EditorPresenter presenter)
    {
        this.presenter = presenter;
    }

    public void setList(List<Question> questions)
    {
        editorList.getItems().setAll(questions);
    }
}
