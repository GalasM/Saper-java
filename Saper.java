import javax.swing.*;
import javax.swing.JComponent;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.Scanner;
import java.applet.Applet;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Saper extends JFrame implements MouseListener,ActionListener
{
	private
	int Width,Height,minesCount,minesFound,minesChecked;
	int[][] Field;
	Random rand;
	MyButton[][] fieldButtons;
	JPanel panel;
	double start,stop;
	JButton restart,play,exit,buttonMap;
	public Font font=new Font("Helvetica", Font.BOLD, 12);
	JFrame frame;
	JFrame menu,menuMap;
	JCheckBox beginnerCheckBox,advanceCheckBox,expertCheckBox;
	boolean isClicked;
	
	public void MouseTestPanel() {
		addMouseListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(restart==e.getSource())
		{
			newGame();
		}
		if(beginnerCheckBox==e.getSource())
		{
			int []x={8,8,10};
			
			if(advanceCheckBox.isSelected())
				advanceCheckBox.setSelected(false);
			if(expertCheckBox.isSelected())
				expertCheckBox.setSelected(false);

		}
		
		if(advanceCheckBox==e.getSource())
		{
			int []x={16,16,40};
			
			if(beginnerCheckBox.isSelected())
				beginnerCheckBox.setSelected(false);
			if(expertCheckBox.isSelected())
				expertCheckBox.setSelected(false);

		}
		
		if(expertCheckBox==e.getSource())
		{
			int []x={30,16,99};
			
			if(advanceCheckBox.isSelected())
				advanceCheckBox.setSelected(false);
			if(beginnerCheckBox.isSelected())
				beginnerCheckBox.setSelected(false);
			
			
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent e)
	{	
	}
	@Override
	public void mouseExited(MouseEvent e)
	{
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e){
		
		
		MyButton obj =(MyButton)e.getSource();
		int x=obj.getFieldX();
		int y=obj.getFieldY();
		
		if(e.isMetaDown())
		{
			
			if(fieldButtons[x][y].flagged==false)
			fieldButtons[x][y].flagged=true;
			else
			fieldButtons[x][y].flagged=false;	
		SetFlag(x,y);
		}	
		else
		{
			if(fieldButtons[x][y].flagged==false)
			{
			showFieldGraficznie(obj);
			obj.setEnabled(false);
			}
			
		}
	}
	@Override
	public void mousePressed(MouseEvent e) 
	{
	}
	
	public void setMap()
	{
		 
		menuMap = new JFrame();
		
		JPanel panelMap = new JPanel(null);
		JTextArea areaWidth = new JTextArea();
		areaWidth.setBounds(150,50,100,20);
		JTextArea areaHeight = new JTextArea();
		areaHeight.setBounds(150,100,100,20);
		JTextArea areaMines = new JTextArea();
		areaMines.setBounds(150,150,100,20);
		JLabel textWidth = new JLabel("Szerokosc: ");
		textWidth.setBounds(50,50,100,20);
		JLabel textHeight = new JLabel("Wysokosc: ");
		textHeight.setBounds(50,100,100,20);
		JLabel textMines = new JLabel("Ilosc: ");
		textMines.setBounds(50,150,100,20);
		buttonMap = new JButton("Graj");
		buttonMap.setBounds(50,200,250,50);
		buttonMap.addActionListener(this);
		beginnerCheckBox = new JCheckBox("Poczatkujacy");
		beginnerCheckBox.setBounds(275,30,250,50);
		beginnerCheckBox.addActionListener(this);
		advanceCheckBox = new JCheckBox("Zaawansowany");
		advanceCheckBox.setBounds(275,80,250,50);
		advanceCheckBox.addActionListener(this);
		expertCheckBox = new JCheckBox("Ekspert");
		expertCheckBox.setBounds(275,130,250,50);
		expertCheckBox.addActionListener(this);
		panelMap.add(areaWidth);
		panelMap.add(areaHeight);
		panelMap.add(textWidth);
		panelMap.add(textHeight);
		panelMap.add(areaMines);
		panelMap.add(textMines);
		panelMap.add(buttonMap);
		panelMap.add(beginnerCheckBox);
		panelMap.add(advanceCheckBox);
		panelMap.add(expertCheckBox);
		panelMap.setPreferredSize(new Dimension(450, 400));
		menuMap.add(panelMap);
		menuMap.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuMap.pack();
		menuMap.setLocation(500,200);
		menuMap.setVisible(true);
		menuMap.repaint();
		
		buttonMap.addActionListener(new ActionListener()
    {
		@Override
        public void actionPerformed(ActionEvent e)
        {
		 int h=0;
		 int w=0;
		 int m=0;
		 int[] x={0,0,0};
		 int[] b={8,8,10};
		 int[] a={16,16,40};
		 int[] ex={30,16,99};
		 menuMap.setVisible(false);
		 if(beginnerCheckBox.isSelected())
		 {		
				newGame(b); 
		 }
		 else if(advanceCheckBox.isSelected())
		 {
				newGame(a); 
		 }
		 else if(expertCheckBox.isSelected())
		 {
			
				newGame(ex); 
		 }
		 else
		 {
			/* if((Integer.parseInt(areaHeight.getText())>25 )|| Integer.parseInt(areaWidth.getText())>25 || Integer.parseInt(areaMines.getText())>(((Integer.parseInt(areaHeight.getText()))*(Integer.parseInt(areaWidth.getText())))-1))
			 {	
			JOptionPane.showMessageDialog(menuMap,"Zmniejsz parametry mapy!");
			 if(menuMap.isVisible())
					menuMap.dispose();
				setMap();
		 }
			 
			 else
			 {*/
			 if (areaHeight.getText().equals("") || areaWidth.getText().equals("") || areaMines.getText().equals("") || areaMines.getText().equals("0")){
			//buttonMap.setEnabled(false);
			if(areaMines.getText().equals("0"))
				JOptionPane.showMessageDialog(menuMap,"Wybierz wiecej min!");
			else 
				JOptionPane.showMessageDialog(menuMap,"Wybierz rozmiar planszy!");
			
			
				if(menuMap.isVisible())
					menuMap.dispose();
				setMap();
			 }
					else
					{
						boolean numeric = true;
				 try
				 {
					h=Integer.parseInt(areaHeight.getText());
					w=Integer.parseInt(areaWidth.getText());
					m=Integer.parseInt(areaMines.getText());
				 }
				 catch (NumberFormatException exc) {
            numeric = false;
				}
				 //if(areaHeight.isNumeric())
           if(!numeric)
		   {
				JOptionPane.showMessageDialog(menuMap,"Podaj liczby");
				if(menuMap.isVisible())
					menuMap.dispose();
				setMap();
		   }
		   else
		   {
			   if((Integer.parseInt(areaHeight.getText())>25 )|| Integer.parseInt(areaWidth.getText())>25 || Integer.parseInt(areaMines.getText())>(((Integer.parseInt(areaHeight.getText()))*(Integer.parseInt(areaWidth.getText())))-1))
			 {	
			JOptionPane.showMessageDialog(menuMap,"Zmniejsz parametry mapy!");
			 if(menuMap.isVisible())
					menuMap.dispose();
				setMap();
		 }
			 
			 else
			 {
			   
		   x[0]=h;
		   x[1]=w;
		   x[2]=m;
		   //System.out.println(Height+" "+Width+" "+minesCount);
			   //menuMap.setVisible(false);
				newGame(x);
		   }
		   }
		 }
		 }
        }
    });
	//System.out.println("BEZ THIS:"+Height+" "+Width+" "+minesCount);
	//System.out.println("THIS:"+this.Height+" "+this.Width+" "+this.minesCount);
	}
	
	
	public void Menu() 
	{
		
		frame = new JFrame();
		frame.setVisible(false);
		menu = new JFrame("Saper");
		JPanel panelMenu = new JPanel(null);
		JLabel np = new JLabel();
		np.setText("SAPER");
		 np.setBounds(200,50, 100,30);	 
		panelMenu.add(np);
		play = new JButton("Nowa Gra");
		play.addActionListener(this);
		play.setBounds(100,100,250,50);
		exit = new JButton("Wyjscie");
		exit.addActionListener(this);
		exit.setBounds(100,200,250,50);
		panelMenu.add(play);
		
		play.addActionListener(new ActionListener()
    {
		@Override
        public void actionPerformed(ActionEvent e)
        {
			   menu.setVisible(false);
				setMap();
        }
    });
		
		panelMenu.add(exit);
		
		exit.addActionListener(new ActionListener()
    {
		@Override
        public void actionPerformed(ActionEvent e)
        {
            
				System.exit(0);
        }
    });
		
		panelMenu.setPreferredSize(new Dimension(450, 400));
		menu.add(panelMenu);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menu.setLocation(500,200);
		menu.pack();
		menu.setVisible(true);
		menu.repaint();
		
	}
	
	
	
	public void SetFlag(int x,int y)
	{
		if(fieldButtons[x][y].isEnabled())
		{
		if(fieldButtons[x][y].getText()=="F")
			{
				//if(fieldButtons[x][y].flagged==true)
					//fieldButtons[x][y].flagged=false;
				minesChecked--;
				fieldButtons[x][y].setEnable(true);
				fieldButtons[x][y].setText("");
				if(Field[x][y]==-1){
					minesFound--;
				}
				System.out.println("Miny:"+minesFound);
				System.out.println("checked:"+minesChecked);
				setGuess();
			}
			else
			{
				//fieldButtons[x][y].flagged=false;
				if(minesChecked!=minesCount)
				{
				minesChecked++;
				fieldButtons[x][y].setEnable(false);
				fieldButtons[x][y].setText("F");
					if(Field[x][y]==-1)
					{
					minesFound++;
					}				
				System.out.println("Miny:"+minesFound);
				System.out.println("checked:"+minesChecked);
				setGuess();
				}
			}
		}
	}
	
	public void setGuess()
	{
		if(minesCount==minesFound)
		{
			stop=System.currentTimeMillis();
			stop=(stop-start)/1000;
			JFrame frame = new JFrame();
			//JOptionPane.showMessageDialog(frame,"WINNER! "+Double.toString(stop)+" sec");
			Object[] options = {"TAK","NIE"};
			int n = JOptionPane.showOptionDialog(frame,
				"Twoj czas to: "+stop+" Czy chcesz zagrac jeszcze raz",
				"WINNER!",
				JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,null);
				//JESLI ZWARACA 0 TAK 1 NIE
				if(n==1)
				{
					if(this.frame.isVisible())
					{
						this.frame.dispose();
						Menu();
					}
				}
				else
				{
					//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
					
					newGame();
					
				}
		}
	}
	
	public void init() 
	 {
		 
		
		 frame = new JFrame("Saper");
		 
		 this.panel = new JPanel(null);
		 
		 panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		 restart = new JButton("RESTART");
		 restart.addActionListener(this);
		 restart.setPreferredSize(new Dimension(100,50));
		// button.setText("RESTART");
		// panel.add(button);
		// button.setBounds(100,100,100,100);
		this.panel.setLayout(new GridLayout(Width,Height));
		 for(int i=0;i<Width;i++)
		 {
			 for(int j=0;j<Height;j++)
			 {
				 this.fieldButtons[i][j]= new MyButton(i,j);
				 this.panel.add(this.fieldButtons[i][j]);
				 //frame.add(this.fieldButtons[i][j]);
				 fieldButtons[i][j].addMouseListener(this);
			 }
		 }
		// 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.add(restart, BorderLayout.PAGE_END);
		frame.pack();
		//setBounds(100,100,450,400);
		//start=System.currentTimeMillis();
		frame.setLocation(500,200);
		frame.setVisible(true);
		frame.repaint();
		
	 }
	
	void newGame()
	{
		if(this.frame.isVisible())
		this.frame.dispose();
		/*MouseTestPanel();
		setField();
		generateField(Field,minesCount);
		init();*/
		setMap();
		//Saper NewGame1= new Saper(1);		
	}
	
	void newGame(int[]x)
	{
		if(this.frame.isVisible())
		this.frame.dispose();
		/*MouseTestPanel();
		setField();
		generateField(Field,minesCount);
		init();*/
		Saper NewGame1= new Saper(x);		
	}
	
	
	void setField()
	{
		/*Scanner hi,wi,mc;
		hi= new Scanner(System.in);
		wi= new Scanner(System.in);
		mc= new Scanner(System.in);
		System.out.println("Podaj szerokosc planszy");
		this.Width=wi.nextInt();
		System.out.println("Podaj wysokosc planszy");
		this.Height=hi.nextInt();
		System.out.println("Podaj liczbe min na planszy");
		this.minesCount=mc.nextInt();*/
		if(Width==0 || Height==0){
			System.out.println("W albo H = 0");
			System.exit(0);
		}
		else
		{
		Field = new int[Width][Height];
		fieldButtons = new MyButton[Width][Height];
		}
	}
	
	boolean canPutTheMine(int x,int y)
	{
		if(this.Field[x][y]==-1)
			return false;
		if(countMines(x,y)==8)
			return false;
		if(!minesCheck(x,y))
			return false;
		
		return true;
	}
	
	boolean minesCheck(int x,int y)
	{
		if((x==0||x==Width-1)&&(y==0||y==Height-1))
			if(countMines(x,y)==3)
				return false;
		if(x==0 || x==Width-1 || y==0 || y==Height-1)
			if(countMines(x,y)==5)
				return false;
		return true;
	}
	void showField()
	{
		for(int i=0;i<this.Width;i++)
		{
		for(int j=0;j<this.Height;j++)
			{
				System.out.print(" "+this.Field[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	int countMines(int x, int y)
	{
		int mines=0;
		for (int i=x-1; i<=x+1; i++)
		  if (i>=0 && i<Width)
		   for (int j=y-1; j<=y+1; j++)
			if (j>=0 && j<Height)
			 if (i!=x || j!=y)
			  if (Field[i][j]==-1 || Field[i][j]==-2)
			   mines++;
		 return mines;
	}
	
	void generateField(int Field[][],int minesCount)
	{
		if(Width==0 || Height==0){
			System.out.println("W albo H = 0");
			System.exit(0);
		}
		else
		{
		int i=this.minesCount;
		while(i>0)
		{
			rand= new Random();
			int x=rand.nextInt(Width);
			int y=rand.nextInt(Height);
			if(canPutTheMine(x,y))
			{
				this.Field[x][y]=-1;
				i--;
			}
		}
		for (i=0; i<Width; i++)
			for (int j=0; j<Height; j++)
				if (Field[i][j]!=-1)
					Field[i][j]=countMines(i,j);
	}
	}
	public class MyButton extends JButton
	{
		private final int fieldX;
		private final int fieldY;
		private boolean status,flagged;
		public MyButton(int x, int y)
		{
			super();
			this.setMargin(new Insets(1,1,1,1));
			this.setPreferredSize(new Dimension(25,25));
			this.setFont(font);
			fieldX=x;
			fieldY=y;
		}
		
		public int getFieldX()
		{
			return this.fieldX;
		}
		
		public int getFieldY()
		{
			return this.fieldY;
		}
		
		public void setEnable(boolean choose)
		{
			this.status=choose;
			
		}
	}
	
	private void showZeros(int x, int y)
	{
	 if (Field[x][y]==0)
	  for (int i=x-1; i<=x+1; i++)
	   if (i>=0 && i<Width)
		for (int j=y-1; j<=y+1; j++)
		 if (j>=0 && j<Height)
		  if (i!=x || j!=y){
			  if(fieldButtons[i][j].getText()=="F")
				minesChecked--;
			if(Field[i][j]==0)
				fieldButtons[i][j].setText("");
				else
		   fieldButtons[i][j].setText(Integer.toString(Field[i][j]));
		   if (fieldButtons[i][j].isEnabled()){
			fieldButtons[i][j].setEnabled(false);
			showZeros(i,j);
		   }
		  }
	}
	
	private void showFieldGraficznie(MyButton button)
	{
		int x = button.getFieldX();
		int y = button.getFieldY();
		if(isClicked==false)
		{
			start=System.currentTimeMillis();
			isClicked=true;
		}
			if(fieldButtons[x][y].getText()=="F")
				minesChecked--;
			if(this.Field[x][y]==-1)
			{
				button.setText("M");
				JOptionPane.showMessageDialog(this,"Game Over");
				if(frame.isVisible())
					frame.dispose();
				Menu();
			}
			else
			{
				if(Field[x][y]==0)
					button.setText("");
				else
				button.setText(Integer.toString(this.Field[x][y]));
				if(fieldButtons[x][y].isEnabled())
				fieldButtons[x][y].setEnabled(false);
				button.setEnabled(false);
				if(Field[x][y]==0)
					showZeros(x,y);
			}	
	}
	
	
	public Saper()
	{
		this.Menu();
		/*this.MouseTestPanel();
		this.setField();
		this.generateField(this.Field,this.minesCount);
		this.init();*/
	}
	public Saper(int[] x)
	{
		Height=x[0];
		Width=x[1];
		minesCount=x[2];
		this.MouseTestPanel();
		this.setField();
		this.generateField(this.Field,this.minesCount);
		this.init();
	}
	public Saper(int x)
	{
		this.MouseTestPanel();
		this.setMap();
		this.setField();
		this.generateField(this.Field,this.minesCount);
		this.init();
	}
	
	
	
	public static void main(String[] args)
	{
		//Saper.Menu newMenu;
		//newMenu = new Menu();
		Saper NewGame=new Saper();
		
		
	}
}

