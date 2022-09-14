import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class AfterPanel extends JPanel{
    private ImageIcon afterPanelIcon = new ImageIcon("Resource/after.png");
    private Image afterPanelImg =new ImageIcon("Resource/after.png").getImage();
    private ItemListPanel itemList;

    public AfterPanel(){
        this.setLayout(null);
        this.setBounds(0,0,1100,600);
        this.addMouseListener(new StartHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(afterPanelImg,0,0,this.getWidth(),564,null);
    }
    private class StartHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            GamePanel.changePanel("startPanel");
        }
    }
}
