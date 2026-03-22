import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Lissajous extends JPanel {
    JTextField aText, bText, deltaText;
    double a = 0, b = 0, delta = 0;
    int size;
    
    public Lissajous(int size) {
        setPreferredSize(new Dimension(size, size));
        this.size = size;
        aText = new JTextField(5);
        aText.setText("5");
        bText = new JTextField(5);
        bText.setText("2");
        deltaText = new JTextField(5);
        deltaText.setText("0.7");
        setLayout(new FlowLayout());
        setBackground(Color.WHITE);
        add(new JLabel("a: "));
        add(aText);
        add(new JLabel("b: "));
        add(bText);
        add(new JLabel("delta: "));
        add(deltaText);
        
        aText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });
        bText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });
        deltaText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                repaint();
            }
        });
    }
    
    @Override
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        a = Double.parseDouble(aText.getText());
        b = Double.parseDouble(bText.getText());
        delta = Double.parseDouble(deltaText.getText());
            
        double X = size / 2 + 2 * size / 5 * Math.sin(delta);
        double Y = size / 2 + 2 * size / 5 * Math.cos(0);
        
        for (double t = 0; t <= (a + b) * Math.PI; t += 0.001) {
            double nextX = size / 2 + 2 * size / 5 * Math.sin(a * t + delta);
            double nextY = size / 2 + 2 * size / 5 * Math.cos(b * t);
            int x1 = (int) X;
            int y1 = (int) Y;  
            int x2 = (int) nextX;
            int y2 = (int) nextY;
            g.drawLine(x1, y1, x2, y2);
            X = nextX;
            Y = nextY;
        }
    }
}