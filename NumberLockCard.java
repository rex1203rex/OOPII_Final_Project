import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class NumberLockCard extends JPanel {

    private ImageIcon nblcIcon = new ImageIcon("Resource/numberLockCard.jpg");
    private Image nblcImg = nblcIcon.getImage();
    private ItemListPanel itemList;

    public NumberLockCard(ItemListPanel itemList){
        this.setLayout(null);
        this.setBounds(0,0,1000,600);
        this.addMouseListener(new PreviousHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(nblcImg,0,0,this.getWidth()+2,564,null);
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            GamePanel.changePreviousPanel();
        }
    }
}
