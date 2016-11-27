package client.views;

import client.controllers.Controller;
import common.classes.Order;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderView extends Frame{
    private JList listViewOrder;
    private JButton buttonView;
    private JButton buttonDelete;
    private JButton buttonBack;
    private DefaultListModel<String> jlistModel;
    private JPanel panel;
    private JTextPane textPane;
    private List<Order> ordersList;
    public OrderView(){
        panel.setPreferredSize(new Dimension(700, 500));
        Controller controller = new Controller(this, panel);

        buttonView.addActionListener(controller);
        buttonView.setActionCommand("viewSelectedOrder");

        buttonDelete.addActionListener(controller);
        buttonDelete.setActionCommand("DeleteSelectOrder");

        buttonBack.addActionListener(controller);
        buttonBack.setActionCommand("viewProfile");
    }
    public DefaultListModel<String> getJlistModel() {
        return jlistModel;
    }

    public JList getListViewOrder() {
        return listViewOrder;
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }

    public JTextPane getTextPane() {
        return textPane;
    }


    public void setData(Serializable obj, List list) {
        this.ordersList = (ArrayList<Order>) list;

        for (int i = 0; i< ordersList.size(); i++){
            jlistModel.addElement(ordersList.get(i).getTour().getName());
        }
    }

    private void createUIComponents() {
        jlistModel = new DefaultListModel<>();
        listViewOrder = new JList(jlistModel);
    }
}
