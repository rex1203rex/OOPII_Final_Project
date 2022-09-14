import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class RevolutionDairy extends JPanel{
    
    public int page = 0;
    private String[] RevolutionDiaryName = {"diary1.jpeg","diary2.jpeg","diary3.jpeg","diary4.jpeg","diary5.jpeg"};
    private JButton nextPage; 

    public RevolutionDairy(){
        setLayout(null);
        setBounds(0,0,1100,600);
        nextPage = new JButton(new ImageIcon("Resource/rightarrow.png"));
        nextPage.setContentAreaFilled(false);
        nextPage.setBorderPainted(false);
        nextPage.setBounds(900,500,80,60);
        nextPage.addMouseListener(new ClickHandler());
        add(nextPage);
    }
    
    public void paintComponent(Graphics g){
        g.drawImage(getDiary(RevolutionDiaryName[page]),0,0,1000,600,this);
        if(page==4){
            nextPage.setVisible(false);
        }else{
            nextPage.setVisible(true);
        }
    }
    public Image getDiary(String photoName){
        Image photo = new ImageIcon("Resource/"+photoName).getImage();
        return photo;
    }
    private class ClickHandler extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent e){
            if(e.getSource() == nextPage){
                if(page < 4){
                    page = page + 1;

                    repaint();
                }
            }        
        }
    }    
}
