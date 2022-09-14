import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class Rune3 extends JPanel {

    private ImageIcon rune3Icon = new ImageIcon("Resource/rune3.png");
    private Image rune3Img =new ImageIcon("Resource/rune3.png").getImage();
    private ItemListPanel itemList;

    public Rune3(ItemListPanel itemList){
        this.setLayout(null);
        this.setBounds(0,0,1000,600);
        this.addMouseListener(new PreviousHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(rune3Img,0,0,this.getWidth(),564,null);
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
                GamePanel.changePreviousPanel();
        }
    }
}
