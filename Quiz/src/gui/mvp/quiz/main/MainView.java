package gui.mvp.quiz.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class MainView extends BorderPane
{
    private MainPresenter mainPresenter;

    private Button resumeButton, undoButton, redoButton;

    public MainView()
    {
        initView();
    }

    public void initView()
    {
        setPadding(new Insets(10));
        HBox buttonBar = new HBox(10);

        Button startButton = new Button("Quiz starten!");
        startButton.setOnAction(e -> mainPresenter.startQuiz());
        buttonBar.getChildren().add(startButton);

        resumeButton = new Button("Quiz fortsetzen!");
        resumeButton.setOnAction(e -> mainPresenter.resumeQuiz());
        buttonBar.getChildren().add(resumeButton);

        Button overviewButton = new Button("Überblick!");
        overviewButton.setId("overview");
        overviewButton.setOnAction(e -> mainPresenter.showOverviewQuiz());
        buttonBar.getChildren().add(overviewButton);

        Button editorButton = new Button("Quiz editieren!");
        editorButton.setOnAction(e -> mainPresenter.editQuiz());
        buttonBar.getChildren().add(editorButton);

        undoButton = new Button("Rückgängig!");
        undoButton.setId("undo");
        undoButton.setOnAction(e -> mainPresenter.undo());
        buttonBar.getChildren().add(undoButton);

        redoButton = new Button("Wiederholen!");
        redoButton.setId("redo");
        redoButton.setOnAction(e -> mainPresenter.redo());
        buttonBar.getChildren().add(redoButton);

        setTop(buttonBar);
    }

    public void disableResumeButton(boolean value)
    {
        resumeButton.setDisable(value);
    }

    public void disableUndoUndoButton(boolean value)
    {
        undoButton.setDisable(value);
    }

    public void disableRedoUndoButton(boolean value)
    {
        redoButton.setDisable(value);
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public void setContent(Pane content)
    {
        setCenter(content);
        setMargin(content, new Insets(10));
    }
}
