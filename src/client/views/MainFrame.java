package client.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame {
    private Component currentContent;

    public MainFrame(Component Content) {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
//                Action.logOut();
            }
        });

        setContent(Content);
        setSize(Content.getPreferredSize());
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("OlkaBenchukTour");
        setLocationRelativeTo(null);
    }

    public void setContent(Component component) {
        Container contentPane = getContentPane();
        if (currentContent != null) {
            contentPane.remove(currentContent);
        }
        contentPane.add(component, BorderLayout.CENTER);
        currentContent = component;
        contentPane.doLayout();
        repaint();
    }
}
