import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class ItemListPanel extends JPanel{
		
	public JButton keyItem; 
	public boolean keySelected = false; // use in gamePanel
	private Image keyImg = new ImageIcon("Resource/key.png").getImage().getScaledInstance(100, 60,  java.awt.Image.SCALE_SMOOTH);;
	private ImageIcon newImgIcon = new ImageIcon(keyImg);

	// new rex
	public JButton runeBtn;
	private Image orgRuneImg = new ImageIcon("Resource/rune1.png").getImage();
	private Image runeImg = orgRuneImg.getScaledInstance(100, 60,  java.awt.Image.SCALE_SMOOTH);
	private ImageIcon runeImgIcon = new ImageIcon(runeImg);

	// gao
	public JButton numberLockCardItem;
	// 12/10 new 
	public boolean numberLockCardAppear = false;

	private Image numberLockCardImg=new ImageIcon("Resource/numberLockCard.jpg").getImage();
	private Image numberLockCard_newimg = numberLockCardImg.getScaledInstance(100, 60,  java.awt.Image.SCALE_SMOOTH);
	private ImageIcon numberLockCardImgIcon = new ImageIcon(numberLockCard_newimg);
	
	public JButton nailItem;
	// 12/10 new 
	public boolean nailAppear = false;

	public boolean nailSelect = false;
	private Image nailImg=new ImageIcon("Resource/nail.png").getImage();
	private Image nail_newimg = nailImg.getScaledInstance(100, 60,  java.awt.Image.SCALE_SMOOTH);
	private ImageIcon nailImgIcon = new ImageIcon(nail_newimg);

	// fat
	/*************************/
 	public JButton dollItem;
 	private Image DollImg = new ImageIcon("Resource/dollbtn.png").getImage().getScaledInstance(100,60,java.awt.Image.SCALE_SMOOTH);
 	private ImageIcon DollImgIcon = new ImageIcon(DollImg);
 	/*********************/

 	// Kuo
 	public JButton runeTableBtn;
 	public boolean runeTableSelect=false;
	private Image runeTableImg = new ImageIcon("Resource/runeTable.jpg").getImage().getScaledInstance(100, 60,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon runeTableImgIcon = new ImageIcon(runeTableImg);
 	//


	// 12/9
	public JButton rune2Item;
	public boolean rune2Select = false;
	private Image rune2Img = new ImageIcon("Resource/rune2.png").getImage().getScaledInstance(100, 60,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon rune2ImgIcon = new ImageIcon(rune2Img);

	public JButton rune3Item;
	public boolean rune3Select = false;
	private Image rune3Img = new ImageIcon("Resource/rune3.png").getImage().getScaledInstance(100, 60,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon rune3ImgIcon = new ImageIcon(rune3Img);

	public JButton rune4Item;
	public boolean rune4Select = false;
	private Image rune4Img = new ImageIcon("Resource/rune4.png").getImage().getScaledInstance(100, 60,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon rune4ImgIcon = new ImageIcon(rune4Img);

	public JButton bellyBoxItem;
	public boolean bellyBoxSelect = false;
	private Image bellyBoxImg = new ImageIcon("Resource/bellyBox.png").getImage().getScaledInstance(100, 60,java.awt.Image.SCALE_SMOOTH);
	private ImageIcon bellyBoxImgIcon = new ImageIcon(bellyBoxImg);	

	// 12/13 new
	public JButton runeDiaryItem;
 	public boolean runeDiarySelect = false;
 	private Image runeDiaryImg = new ImageIcon("Resource/runeDiary.png").getImage();
 	private Image runeDiaryNewimg = runeDiaryImg.getScaledInstance(100, 60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
 	private ImageIcon runeDiaryNewImgIcon = new ImageIcon(runeDiaryNewimg);

	private ItemHandler handler=new ItemHandler();

	// music
	public int count = 0;

	/******reset**********/
	public void reset(){
		keyItem.setVisible(false); 
		keySelected = false;
		runeBtn.setVisible(false);
		numberLockCardItem.setVisible(false);
		numberLockCardAppear = false;
		nailAppear = false;
		nailSelect = false;
		if(Doll.isNailRemove){
			this.add(this.nailItem);
			nailItem.setVisible(false);
		}
		nailItem.setVisible(false);
		if(Doll.isDollRemove){
			this.add(this.dollItem);
			dollItem.setVisible(false);
		}
		dollItem.setVisible(false);
		runeTableBtn.setVisible(false);
		runeTableSelect=false;
		rune2Item.setVisible(false);
		rune2Select = false;
		rune3Item.setVisible(false);
		rune3Select = false;
		rune4Item.setVisible(false);
		rune4Select = false;
		if(BellyBox.isBellyBoxRemove){
			this.add(this.bellyBoxItem);
			bellyBoxItem.setVisible(false);
		}
		bellyBoxItem.setVisible(false);
		bellyBoxSelect = false;
		runeDiaryItem.setVisible(false);
		runeDiarySelect = false;
		revalidate();
	}
	/*********************/

	ItemListPanel(){

		this.setPanel();		
		this.setItem();
		this.setPreferredSize(new Dimension(100,600));
        
	}
	private class ItemHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event){

			if(event.getSource() == keyItem){
				keySelected = true;
			}else{
				keySelected = false;
			}

			if(event.getSource() == runeBtn)
				GamePanel.changePanel("runeListPanel");
			if(event.getSource() == numberLockCardItem)
				GamePanel.changePanel("numberLockCardListPanel");
			if(event.getSource() == dollItem){
				GamePanel.changePanel("dollListPanel");
				// music
				if(count == 0){
					AePlayWave t = new AePlayWave("Media/doll.wav",ItemListPanel.this);
					t.start();
					count = t.count;
				}
			}
			if(event.getSource() == runeTableBtn)
				GamePanel.changePanel("runeTableListPanel");

			// 12/9
			if(event.getSource() == rune2Item)
			    GamePanel.changePanel("rune2ListPanel");
			if(event.getSource() == rune3Item)
			    GamePanel.changePanel("rune3ListPanel");
			if(event.getSource() == rune4Item)
			    GamePanel.changePanel("rune4ListPanel");
			if(event.getSource() == bellyBoxItem)
			    GamePanel.changePanel("bellyBoxListPanel");
			if(event.getSource() == runeDiaryItem)
       			GamePanel.changePanel("runeDiaryListPanel");

			if(event.getSource() == nailItem){
			    nailSelect = true;
			}else{
			    nailSelect = false;
			}

			// 12/10 new 
			if(event.getSource() == keyItem){
			    keySelected = true;
			}else{
			    keySelected = false;
			}
		}
	}
	public void setPanel(){

		this.setBackground(Color.gray);
		// 12/10 new
		this.setLayout(new GridLayout(11,1));

	}
	public void setItem(){
		
		// 12/13 new 
		runeDiaryItem = new JButton(runeDiaryNewImgIcon);
  		runeDiaryItem.setVisible(false);
  		runeDiaryItem.addActionListener(handler);
  		add(runeDiaryItem);

		// 12/9
		rune2Item = new JButton(rune2ImgIcon);
        rune2Item.setVisible(false);
        rune2Item.addActionListener(handler);
        add(rune2Item);
        //
		runeBtn = new JButton(runeImgIcon);
		runeBtn.setVisible(false);
		runeBtn.addActionListener(handler);
		add(runeBtn);

		numberLockCardItem = new JButton(numberLockCardImgIcon);
		numberLockCardItem.setVisible(false);
		numberLockCardItem.addActionListener(handler);
		add(numberLockCardItem);

		nailItem = new JButton(nailImgIcon);
		nailItem.setVisible(false);
		nailItem.addActionListener(handler);
		add(nailItem);

		runeTableBtn = new JButton(runeTableImgIcon);
        runeTableBtn.setVisible(false);
        runeTableBtn.addActionListener(handler);;
        add(runeTableBtn);

		dollItem = new JButton(DollImgIcon);
		dollItem.setVisible(false);
		dollItem.addActionListener(handler);
		add(dollItem);		

		rune3Item = new JButton(rune3ImgIcon);
        rune3Item.setVisible(false);
        rune3Item.addActionListener(handler);
        add(rune3Item);

		rune4Item = new JButton(rune4ImgIcon);
        rune4Item.setVisible(false);
        rune4Item.addActionListener(handler);
        add(rune4Item);

		bellyBoxItem = new JButton(bellyBoxImgIcon);
        bellyBoxItem.setVisible(false);
        bellyBoxItem.addActionListener(handler);
        add(bellyBoxItem);    
		
		// 12/10 new 
		keyItem = new JButton(newImgIcon);
		keyItem.setVisible(false);
		keyItem.addActionListener(handler);
		add(keyItem);
	}

}