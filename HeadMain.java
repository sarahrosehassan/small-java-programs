import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import javax.swing.JFrame;

public class HeadMain {
    public static void main(String args[]) {
        JFrame frame = new JFrame("Head");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel topPanel = new Panel(new GridLayout(2, 2));
        
        Head panel1 = new Head();
        Head panel2 = new Head();
        Head panel3 = new Head();
        Head panel4 = new Head();
        
        topPanel.add(panel1, BorderLayout.EAST);
        topPanel.add(panel2, BorderLayout.WEST);
        topPanel.add(panel3, BorderLayout.NORTH);
        topPanel.add(panel4, BorderLayout.SOUTH);
        
        frame.getContentPane().add(topPanel);
        frame.pack();

frame.setVisible(true);

}

}