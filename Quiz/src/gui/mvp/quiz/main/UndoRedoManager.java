package gui.mvp.quiz.main;

import java.util.ArrayList;
import java.util.List;

import gui.mvp.quiz.editor.UndoRedoableAction;

public class UndoRedoManager
{
    private List<UndoRedoableAction> actions;

    private int aktPosition;

    public UndoRedoManager()
    {
        actions = new ArrayList<>();
        aktPosition = 0;
    }

    public void addAction(UndoRedoableAction action)
    {
        for (int i = actions.size() - 1; i >= aktPosition; i--)
        {
            actions.remove(i);
        }
        actions.add(action);
        aktPosition++;
    }

    public void undo()
    {
        if (aktPosition > 0)
        {
            aktPosition--;
            actions.get(aktPosition).undo();
        }
    }

    public void redo()
    {
        if (aktPosition < actions.size())
        {
            actions.get(aktPosition).redo();
            aktPosition++;
        }
    }

    public boolean canUndo()
    {
        return aktPosition > 0;
    }

    public boolean canRedo()
    {
        return aktPosition < actions.size();
    }
}
