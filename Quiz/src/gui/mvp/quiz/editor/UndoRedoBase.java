package gui.mvp.quiz.editor;

import gui.mvp.quiz.model.Model;
import gui.mvp.quiz.model.Question;

public abstract class UndoRedoBase implements UndoRedoableAction
{
    protected Model model;

    protected EditorView view;

    public UndoRedoBase(Model model, EditorView view)
    {
        this.model = model;
        this.view = view;
    }
}

class UndoRedoDelete extends UndoRedoBase
{
    private Question oldQuestion;

    private int indexOfOldQuestion;

    public UndoRedoDelete(Model model, EditorView view, Question oldQuestion)
    {
        super(model, view);
        this.oldQuestion = oldQuestion;
        this.indexOfOldQuestion = model.getIndexOf(oldQuestion);
    }

    public void undo()
    {
        model.addQuestion(indexOfOldQuestion, oldQuestion);
    }

    public void redo()
    {
        model.deleteQuestion(oldQuestion);
    }

}

class UndoRedoAdd extends UndoRedoBase
{
    private Question newQuestion;

    public UndoRedoAdd(Model model, EditorView view, Question newQuestion)
    {
        super(model, view);
        this.newQuestion = newQuestion;
    }

    public void undo()
    {
        model.deleteQuestion(newQuestion);
    }

    public void redo()
    {
        model.addQuestion(newQuestion);
    }
}

class UndoRedoUpdate extends UndoRedoBase
{
    private Question oldQuestion;

    private Question newQuestion;

    public UndoRedoUpdate(Model model, EditorView view, Question oldQuestion, Question newQuestion)
    {
        super(model, view);
        this.oldQuestion = oldQuestion;
        this.newQuestion = newQuestion;
    }

    public void undo()
    {
        model.updateQuestion(newQuestion, oldQuestion);
    }

    public void redo()
    {
        model.updateQuestion(oldQuestion, newQuestion);
    }
}
