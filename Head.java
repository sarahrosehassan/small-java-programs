import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class Head extends JPanel {
    private static final long serialVersionUID = -5358188138402685043L;
    private boolean mouseInside;

public Head() {
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(500, 500));
    setBorder(new BevelBorder(BevelBorder.RAISED));
    this.addMouseListener(new MouseAdapter() {

@Override
public void mouseExited(MouseEvent e) {
    mouseInside = false;
    Head.this.repaint();
}

@Override
public void mouseEntered(MouseEvent e) {
    mouseInside = true;
    Head.this.repaint();
}
});

}

@Override
public void paintComponent(final Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.PINK);
    g.fillOval(100, 100, 200, 220);  
    
    if (mouseInside) {
        g.setColor(Color.RED);
        g.drawOval(150, 135, 30, 5);
        g.drawOval(200, 135, 30, 5);
        g.drawArc(170, 230, 40, 30, 180, 180);
    }
    else {
        g.setColor(Color.RED);
        g.drawOval(160, 139, 5, 30);
        g.drawOval(210, 139, 5, 30);
        g.drawArc(170, 230, 40, 30, 180, 180);

}
}
}

