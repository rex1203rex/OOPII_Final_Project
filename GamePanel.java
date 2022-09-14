import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class GamePanel extends JPanel {

	public static boolean isTurnOn=false;

	public static CardLayout cardlayout;
	public static GamePanel gamePanel;//bad
	private static ItemListPanel itemList;

	private static JPanel playPanel;
	private static JPanel livingRoomListPanel;	
	private static JPanel bathRoomListPanel;
	private static JPanel ceilingListPanel;
	private static JPanel floorListPanel;
	private static JPanel beforePanel;
	private static JPanel afterPanel;
	
	private static BathRoomPanel bathRoomPanel;
	private static StartPanel startPanel;
	private static SettingPanel settingPanel;
	private static RoomPanel roomPanel ;
	private static LivingRoomPanel livingRoomPanel;
	private static CeilingPanel ceilingPanel;
	private static FloorPanel floorPanel;
	
	private static Rune rune;
	private static JPanel runeListPanel;
	private static NumberLockCard numberLockCard;	
	private static JPanel numberLockCardListPanel;
	private static Doll doll;	
	private static JPanel dollListPanel;
    private static RuneTable runeTable;
    private static JPanel runeTableListPanel;	

    // 12/9
	private static Rune2 rune2;
	private static JPanel rune2ListPanel;

	private static Rune3 rune3;
	private static JPanel rune3ListPanel;

	private static Rune4 rune4;
	private static JPanel rune4ListPanel;

	private static BellyBox bellyBox;
	private static JPanel bellyBoxListPanel;

	private static RuneDiary runeDiary;
 	private static JPanel runeDiaryListPanel;

	//
	private static Container nowPanel;
	//
	public GamePanel(){
		GamePanel.gamePanel = this;

	    cardlayout=new CardLayout();
	    cardlayout.setHgap(0);
	    cardlayout.setVgap(0);

	    // panel object
	    itemList = new ItemListPanel();
	    startPanel= new StartPanel();
	    settingPanel=new SettingPanel();
		// NEW
		beforePanel = new BeforePanel();
		afterPanel = new AfterPanel();

	    roomPanel = new RoomPanel(this.itemList);
	    livingRoomPanel = new LivingRoomPanel(this.itemList);
	    bathRoomPanel= new BathRoomPanel(this.itemList);
	    ceilingPanel = new CeilingPanel(this.itemList);
	    floorPanel = new FloorPanel(this.itemList);

	    //new 
	    rune = new Rune(this.itemList);
	   	numberLockCard = new NumberLockCard(this.itemList);
	   	doll = new Doll(this.itemList);
	   	runeTable = new RuneTable(this.itemList);

	   	// 12/9
	   	rune2 = new Rune2(this.itemList);  
		rune3 = new Rune3(this.itemList);
		rune4 = new Rune4(this.itemList);
		bellyBox = new BellyBox(this.itemList);

		// 12/13 new 
		runeDiary = new RuneDiary(this.itemList);



	    // Mix with roomPanel & itemListPanel
	    playPanel = new JPanel(); 
	    playPanel.setLayout(new BorderLayout());
	    playPanel.add(roomPanel,BorderLayout.CENTER);

	    livingRoomListPanel = new JPanel();
	    livingRoomListPanel.setLayout(new BorderLayout());
	    livingRoomListPanel.add(livingRoomPanel,BorderLayout.CENTER);
	    
	    // 12/03 new add
	    bathRoomListPanel = new JPanel();
	    bathRoomListPanel.setLayout(new BorderLayout());
	    bathRoomListPanel.add(bathRoomPanel,BorderLayout.CENTER);
	    
	    // 12/05 new add
	    ceilingListPanel = new JPanel();
	    ceilingListPanel.setLayout(new BorderLayout());
	    ceilingListPanel.add(ceilingPanel,BorderLayout.CENTER);

	    floorListPanel = new JPanel();
	    floorListPanel.setLayout(new BorderLayout());
	    floorListPanel.add(floorPanel,BorderLayout.CENTER);	  

	    //new
	    runeListPanel = new JPanel();
	    runeListPanel.setLayout(new BorderLayout());  
	    runeListPanel.add(rune,BorderLayout.CENTER);

	    numberLockCardListPanel = new JPanel();
	    numberLockCardListPanel.setLayout(new BorderLayout());  
	    numberLockCardListPanel.add(numberLockCard,BorderLayout.CENTER);

	    dollListPanel = new JPanel();
	    dollListPanel.setLayout(new BorderLayout());  
	    dollListPanel.add(doll,BorderLayout.CENTER);

        runeTableListPanel = new JPanel();
        runeTableListPanel.setLayout(new BorderLayout());
        runeTableListPanel.add(runeTable,BorderLayout.CENTER);
	    // 12/9
		rune2ListPanel = new JPanel();
        rune2ListPanel.setLayout(new BorderLayout());
        rune2ListPanel.add(rune2,BorderLayout.CENTER);

  		rune3ListPanel = new JPanel();
        rune3ListPanel.setLayout(new BorderLayout());
        rune3ListPanel.add(rune3,BorderLayout.CENTER);

  		rune4ListPanel = new JPanel();
        rune4ListPanel.setLayout(new BorderLayout());
        rune4ListPanel.add(rune4,BorderLayout.CENTER);

  		bellyBoxListPanel = new JPanel();
        bellyBoxListPanel.setLayout(new BorderLayout());
        bellyBoxListPanel.add(bellyBox,BorderLayout.CENTER);

		// 12/13 new
		runeDiaryListPanel = new JPanel();
        runeDiaryListPanel.setLayout(new BorderLayout());
        runeDiaryListPanel.add(runeDiary,BorderLayout.CENTER);

	    this.setLayout(cardlayout);
	    this.add(startPanel,"startPanel");
	    this.add(settingPanel,"settingPanel");
	    this.add(playPanel,"playPanel");
	    this.add(livingRoomListPanel,"livingRoomListPanel");
	    //new
	    this.add(beforePanel,"beforePanel");
	    this.add(afterPanel,"afterPanel");

	    // 12/03 new add
	    this.add(bathRoomListPanel,"bathRoomListPanel");

	    // 12/05 new add
	    this.add(ceilingListPanel,"ceilingListPanel");
	    this.add(floorListPanel,"floorListPanel");

	    //new
	    this.add(runeListPanel,"runeListPanel");
	    this.add(numberLockCardListPanel,"numberLockCardListPanel");
	    this.add(dollListPanel,"dollListPanel");
	    this.add(runeTableListPanel,"runeTableListPanel");

	    // 12/9
		this.add(rune2ListPanel,"rune2ListPanel");
		this.add(rune3ListPanel,"rune3ListPanel");
		this.add(rune4ListPanel,"rune4ListPanel");
		this.add(bellyBoxListPanel,"bellyBoxListPanel");

		// 12/13 new 
		this.add(runeDiaryListPanel,"runeDiaryListPanel");
	}
	public static void changePanel(String panel){
		
		GamePanel.cardlayout.show(GamePanel.gamePanel,panel);
		if(panel.equals("startPanel")){
			ceilingPanel.reset();
			bathRoomPanel.reset();
			floorPanel.reset();
			livingRoomPanel.reset();
			roomPanel.reset();
			itemList.reset();
		}		

		// add listPanel in specific panel
		if(panel.equals("livingRoomListPanel")){
			livingRoomListPanel.add(itemList,BorderLayout.EAST);
			nowPanel = livingRoomListPanel;
		}

		if(panel.equals("playPanel")){
			playPanel.add(itemList,BorderLayout.EAST);
			nowPanel = playPanel;
		}
		
		// 12/03 new add 
		if(panel.equals("bathRoomListPanel")){
			bathRoomListPanel.add(itemList,BorderLayout.EAST);
			nowPanel = bathRoomListPanel;
		}
		
		// 12/05 new add
		if(panel.equals("ceilingListPanel")){
			ceilingListPanel.add(itemList,BorderLayout.EAST);
			nowPanel = ceilingListPanel;
		}

		if(panel.equals("floorListPanel")){
			floorListPanel.add(itemList,BorderLayout.EAST);	
			nowPanel = floorListPanel;
		}

		if(panel.equals("runeListPanel"))
			runeListPanel.add(itemList,BorderLayout.EAST);
		if(panel.equals("numberLockCardListPanel"))
			numberLockCardListPanel.add(itemList,BorderLayout.EAST);
		if(panel.equals("dollListPanel"))
			dollListPanel.add(itemList,BorderLayout.EAST);
        if(panel.equals("runeTableListPanel"))
            runeTableListPanel.add(itemList,BorderLayout.EAST);
  		if(panel.equals("rune2ListPanel"))
            rune2ListPanel.add(itemList,BorderLayout.EAST);
  		if(panel.equals("rune3ListPanel"))
            rune3ListPanel.add(itemList,BorderLayout.EAST);
  		if(panel.equals("rune4ListPanel"))
            rune4ListPanel.add(itemList,BorderLayout.EAST);
  		if(panel.equals("bellyBoxListPanel"))
            bellyBoxListPanel.add(itemList,BorderLayout.EAST);
		if(panel.equals("runeDiaryListPanel"))
            runeDiaryListPanel.add(itemList,BorderLayout.EAST);

	}
	public static void changePreviousPanel(){
		if (nowPanel == ceilingListPanel)
			changePanel("ceilingListPanel");
		if (nowPanel == livingRoomListPanel)
			changePanel("livingRoomListPanel");
		if (nowPanel == floorListPanel)
			changePanel("floorListPanel");
		if (nowPanel == bathRoomListPanel)
			changePanel("bathRoomListPanel");
		if (nowPanel == playPanel)
			changePanel("playPanel");
	}
}