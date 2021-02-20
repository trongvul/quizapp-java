package gui.mvp.quiz;

import java.io.IOException;

import gui.mvp.quiz.editor.EditorPresenter;
import gui.mvp.quiz.editor.EditorView;
import gui.mvp.quiz.game.QuizPresenter;
import gui.mvp.quiz.game.QuizView;
import gui.mvp.quiz.main.MainPresenter;
import gui.mvp.quiz.main.MainView;
import gui.mvp.quiz.main.UndoRedoManager;
import gui.mvp.quiz.model.Model;
import gui.mvp.quiz.overview.OverviewPresenter;
import gui.mvp.quiz.overview.OverviewView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{

    public void start(Stage primaryStage) throws IOException
    {
        MainPresenter mainPresenter = initApplication();
        mainPresenter.startQuiz();
        Scene scene = new Scene(mainPresenter.getUI(), 750, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Quiz");
        primaryStage.show();
    }

    public MainPresenter initApplication() throws IOException
    {
        MainPresenter mainPresenter = new MainPresenter();
        MainView mainView = new MainView();
        QuizPresenter quizPresenter = new QuizPresenter();
        QuizView quizView = new QuizView();
        OverviewPresenter overviewPresenter = new OverviewPresenter();
        OverviewView overviewView = new OverviewView();
        EditorPresenter editorPresenter = new EditorPresenter();
        EditorView editorView = new EditorView();
        UndoRedoManager manager = new UndoRedoManager();
        Model model = ModelInitializer.initModel(getParameters().getUnnamed().get(0));

        mainPresenter.setMainView(mainView);
        mainPresenter.setQuizPresenter(quizPresenter);
        mainPresenter.setOverviewPresenter(overviewPresenter);
        mainPresenter.setEditorPresenter(editorPresenter);
        mainPresenter.setUndoRedoManager(manager);
        mainView.setMainPresenter(mainPresenter);

        quizPresenter.setView(quizView);
        quizPresenter.setModel(model);
        quizView.setPresenter(quizPresenter);

        overviewPresenter.setModel(model);
        overviewPresenter.setView(overviewView);
        overviewView.setPresenter(overviewPresenter);

        editorPresenter.setMainPresenter(mainPresenter);
        editorPresenter.setModel(model);
        editorPresenter.setView(editorView);
        editorPresenter.setUndoRedoManager(manager);
        editorView.setPresenter(editorPresenter);

        return mainPresenter;
    }

    public static void main(String[] args)
    {
        launch(args);
    }

}
