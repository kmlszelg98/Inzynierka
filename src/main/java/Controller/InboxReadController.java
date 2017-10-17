package Controller;

import Model.InboxModel;
import View.InboxReadView;
import View.InboxView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 11.10.2017.
 */
public class InboxReadController {

    private InboxReadView view;
    private InboxModel models;

    public InboxReadController(InboxReadView view, InboxModel model) {
        this.view = view;
        this.models = model;
        setListeners();
    }

    public void setListeners(){

        view.getNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.init();
                new InboxReadController(view,models);
            }
        });

        view.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InboxModel model = new InboxModel(models.getMessage());
                InboxView view = new InboxView(model);
                new InboxController(model,view);
            }
        });
    }


}
