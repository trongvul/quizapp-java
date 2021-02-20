package gui.mvp.quiz.editor;

import gui.mvp.quiz.main.MainPresenter;
import gui.mvp.quiz.main.UndoRedoManager;
import gui.mvp.quiz.model.Model;
import gui.mvp.quiz.model.Question;

public class EditorPresenter
{
    private EditorView view;

    private Model model;

    private Question selectedQuestion;

    private UndoRedoManager undoRedoManager;

    private MainPresenter mainPresenter;

    public EditorPresenter()
    {

    }

    public void setView(EditorView view)
    {
        this.view = view;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public EditorView getView()
    {
        return view;
    }

    public void setMainPresenter(MainPresenter mainPresenter)
    {
        this.mainPresenter = mainPresenter;
    }

    public void setUndoRedoManager(UndoRedoManager undoRedoManager)
    {
        this.undoRedoManager = undoRedoManager;
    }

    public void setList()
    {
        view.setList(model.getQuestions());
    }

    public void deleteQuestion()
    {
        if (selectedQuestion == null)
        {
            view.showInfo();
        }
        else
        {
            if (view.showDeleteConfirmation())
            {
                UndoRedoDelete action = new UndoRedoDelete(model, view, selectedQuestion);
                undoRedoManager.addAction(action);
                model.deleteQuestion(selectedQuestion);
                setList();
                mainPresenter.changeUndoRedoButton();
            }

        }
    }

    public void selectedQuestion(Question newValue)
    {
        selectedQuestion = newValue;
    }

    public void editQuestion()
    {
        if (selectedQuestion == null)
        {
            view.showInfo();
        }
        else
        {
            Question editedQuestion = view.showEditDialog(selectedQuestion);
            if (editedQuestion != null)
            {
                model.updateQuestion(selectedQuestion, editedQuestion);
                model.deleteHistory();
                UndoRedoUpdate action = new UndoRedoUpdate(model, view, selectedQuestion, editedQuestion);
                undoRedoManager.addAction(action);
                setList();
                mainPresenter.changeUndoRedoButton();
            }

        }
    }

    public void addQuestion()
    {
        Question newQuestion = view.showAddQuestionDialog();
        if (newQuestion != null)
        {
            model.addQuestion(newQuestion);
            model.deleteHistory();
            UndoRedoAdd action = new UndoRedoAdd(model, view, newQuestion);
            undoRedoManager.addAction(action);
            setList();
            mainPresenter.changeUndoRedoButton();
        }

    }

}
