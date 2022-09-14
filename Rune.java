import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
public class Rune extends JPanel {

    private ImageIcon runeIcon = new ImageIcon("Resource/rune1.png");
    private Image runeImg =new ImageIcon("Resource/rune1.png").getImage();
    private ItemListPanel itemList;

    public Rune(ItemListPanel itemList){
        this.setLayout(null);
        this.setBounds(0,0,1000,600);
        this.addMouseListener(new PreviousHandler());
    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(runeImg,0,0,this.getWidth(),564,null);
    }
    private class PreviousHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
                GamePanel.changePreviousPanel();
        }
    }
}
