package gui.mvp.quiz.overview;

import gui.mvp.quiz.model.Model;

public class OverviewPresenter
{
    private OverviewView view;

    private Model model;

    public OverviewPresenter()
    {

    }

    public OverviewView getView()
    {
        return view;
    }

    public void setView(OverviewView view)
    {
        this.view = view;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void delete()
    {
        model.deleteHistory();
        setList();
    }

    public void setList()
    {
        view.setList(model.getQuestions());
    }

}
