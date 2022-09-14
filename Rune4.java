import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class Rune4 extends JPanel {

    private ImageIcon rune4Icon = new ImageIcon("Resource/rune4.png");
    private Image rune4Img =new ImageIcon("Resource/rune4.png").getImage();
    private ItemListPanel itemList;

    public Rune4(ItemListPanel itemList){
        this.setLayout(null);
        this.setBounds(0,0,1000,600);
        this.addMouseListener(new PreviousHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(rune4Img,0,0,this.getWidth(),564,null);
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
                GamePanel.changePreviousPanel();
        }
    }
}