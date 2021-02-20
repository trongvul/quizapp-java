package gui.mvp.quiz.main;

import gui.mvp.quiz.editor.EditorPresenter;
import gui.mvp.quiz.game.QuizPresenter;
import gui.mvp.quiz.overview.OverviewPresenter;

public class MainPresenter
{
    private MainView mainView;

    private QuizPresenter quizPresenter;

    private OverviewPresenter overviewPresenter;

    private EditorPresenter editorPresenter;

    private UndoRedoManager undoRedoManager;

    public MainPresenter()
    {

    }

    public void setMainView(MainView mainView)
    {
        this.mainView = mainView;
    }

    public MainView getUI()
    {
        return mainView;
    }

    public void setUndoRedoManager(UndoRedoManager undoRedoManager)
    {
        this.undoRedoManager = undoRedoManager;
    }

    public void setQuizPresenter(QuizPresenter quizPreseneter)
    {
        this.quizPresenter = quizPreseneter;
    }

    public void setOverviewPresenter(OverviewPresenter overviewPresenter)
    {
        this.overviewPresenter = overviewPresenter;
    }

    public void setEditorPresenter(EditorPresenter editorPresenter)
    {
        this.editorPresenter = editorPresenter;
    }

    public void startQuiz()
    {
        quizPresenter.setFirstQuestion();
        mainView.setContent(quizPresenter.getView());
        mainView.disableResumeButton(false);
        changeUndoRedoButton();
    }

    public void showOverviewQuiz()
    {
        overviewPresenter.setList();
        mainView.setContent(overviewPresenter.getView());
    }

    public void resumeQuiz()
    {
        mainView.setContent(quizPresenter.getView());
    }

    public void editQuiz()
    {
        editorPresenter.setList();
        mainView.setContent(editorPresenter.getView());
        mainView.disableResumeButton(true);
    }

    public void undo()
    {
        undoRedoManager.undo();
        editQuiz();
        changeUndoRedoButton();
    }

    public void redo()
    {
        undoRedoManager.redo();
        editQuiz();
        changeUndoRedoButton();
    }

    public void changeUndoRedoButton()
    {
        mainView.disableUndoUndoButton(!undoRedoManager.canUndo());
        mainView.disableRedoUndoButton(!undoRedoManager.canRedo());
    }

}
