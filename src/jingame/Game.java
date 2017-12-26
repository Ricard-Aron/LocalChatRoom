//2017.12.5
//�����������Ϸ�������
package jingame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Game extends JFrame{

	//�������趨�����С
	private final int WIDTH = 1290;
	private final int HEIGHT = 960;
	/*//��ǰ����
	private int x=0;
	private int y=0;
	//��һ���ƶ�����
	private int l_x=0;
	private int l_y=0;*/
	//λ����Ϣ��    ���Զ�������  �Ҷ�����ˢ��
	public static Position position = new Position();
	//��Ϣ��
	private Info info;
	//��ͼ��
	//�ø������������浱ǰ���ĸ�GameField   1Ϊ��ǰ��  0Ϊ���ǵ�ǰ��      ����xy[4]�Ƕ��پ��Ǽ���
	//��ȫ����GameField
	public static ArrayList<GameField> fieldList = new ArrayList<>();
	//ս������
	public static FightPanel fight;
	public static boolean isFight = false;
	//ս��Ʒ����
	public static BootyPanel booty;
	public static boolean isBooty = false;
	//���黭��
	public static StoryPanel story;
	public static boolean isStory = false;
	//�浵�������
	/*public static Save save;
	public static Load load;*/
	//��ͼ
	private GameField enter;
	private GameField zero;
	private GameField one;
	private GameField two;
	private GameField three;
	private GameField shao_down;
	//Hero
	private static Hero man;
	//Heroͷ��
	private static BufferedImage m_head;
	
	//��д���ļ�����
	/*private ObjectOutputStream keep;
	private ObjectInputStream load;*/
	RandomAccessFile randomfile;
	private File file;
	//�ж��Ƿ��˴浵��ť  ���û�� �Ͷ�ȡ�ϴεĶ���
	//������� ����
	//private boolean isChange = false;
	//�浵���м���� ��hero��Ϣ  ������
	private Hero h;
	private int[] temp = new int[5];
	//�浵�����¥��
	private int floor;
	public Game()
	{
		super("��ӹȺ����");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		file = new File("src/jingame/file/load1.txt");
		ImageIcon icon=new ImageIcon("src/jingame/icon/title.png");
        this.setIconImage(icon.getImage());
		//����һ����������ȡ�ļ���RandomAccessfile
		//�����óɿɶ�д
		try {
			randomfile=new RandomAccessFile(file,"rw");
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		/*try {
			//��ȡ���������
			keep = new ObjectOutputStream(new FileOutputStream(file));
			load = new ObjectInputStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
	}
	public void init() throws IOException
	{
		//��ʼ��Ӣ��
		m_head = ImageIO.read(new File("src/jingame/icon/front.gif"));
		man = new Hero(1,50,15,2
				,0,0,0
				,0,0,m_head);
		
		fight = new FightPanel();
		booty = new BootyPanel();
		story = new StoryPanel();
		/*save = new Save();
		load = new Load();*/
		//Ĭ�ϲ���ʾ
		//fight.setVisible(false);
		info = new Info();
		enter = new Entrance(man);
		fieldList.add(enter);
		zero = new Field0(man);
		fieldList.add(zero);
		one = new Field1(man);
		fieldList.add(one);
		two = new Field2(man);
		fieldList.add(two);
		three = new Field3(man);
		fieldList.add(three);
		shao_down = new Field_shaolinshanxia(man);
		fieldList.add(shao_down);
		//�ʼ�ǵ�һ��
		for(GameField f:fieldList)
		{
			f.init();
			//���������Ȳ���ʾ
			f.setVisible(false);
			addField(f);
		}
		//������one ��ʾ    ��ΪJPanel����Ĭ����ʾ  �������ô����  �������һЩ��ʾ����  
 		/*one.setVisible(true);
		one.setVisible(false);
		one.setVisible(true);*/
		
		//������ʾ��ҳ  ��������   
		enter.setVisible(true);
		position.setF(enter);
		//������xy[0] = 7; xy[2] = 17;
		position.getXy()[0] = 7;
		position.getXy()[1] = 7;
		position.getXy()[2] = 17;
		position.getXy()[3] = 17;
		//position.setFloor(0);
		/*one.setVisible(false);
		two.setVisible(true);*/
		//�������� ArrayList��0��λ�ÿ�ʼ��
		/*System.out.println(fieldList.get(0));
		System.out.println(fieldList.get(1));*/
		setLayout(null);
		//���ø������λ�ü���С
		/*enter.setBounds(480,0,800,900);
		zero.setBounds(480,0,800,900);
		one.setBounds(480,0,800,900);
		two.setBounds(480,0,800,900);
		three.setBounds(480,0,800,900);*/
		info.setBounds(0,0,480,900);
		fight.setBounds(480,0,0,0);
		booty.setBounds(480,315,0,0);
		story.setBounds(480,0,0,0);
		
		/*save.setBounds(420,280,760,410);
		load.setBounds(420,280,760,410);*/
		//��¥�����JFrame
		/*add(enter);
		add(zero);
		add(one);
		add(two);
		add(three);*/
		//��info
		add(info);
		//��fight
		add(fight);
		add(booty);
		add(story);
		/*add(save);
		add(load);*/
		/*add(one, BorderLayout.EAST);
		add(info, BorderLayout.WEST);*/
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�ô����м���ʾ��
		//setLocationRelativeTo(null);
		pack();
		setResizable(false);
		setVisible(true);
		/*new showFight().start();*/
		//��ʱ����  info������
		ActionListener taskPerformer = evt ->
        {
        	//����
        	refreshInfoByHero();
        	//System.out.println(man);
        	//���µ�ǰλ��  Ҳ���ǵ�ͼλ��
    		info.getFloors().setText(position.getF().getFloors());
        	if(isFight)
        		{
        		//while(isFight)
        			fight.repaint();
        		}
        	if(isBooty)
        	{
        		booty.repaint();
        	}
        	if(isStory)
        	{
        		story.repaint();
        	}
        };
        Timer timer = new Timer(500,taskPerformer);
        timer.start();
		addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
			
				//ֻ���ھ����� ���ܰ���Щ
				if(isStory)
				{
					if(e.getKeyCode()==KeyEvent.VK_Q)
				
				{
					StoryPanel.i++;
					//�����ʱ�������  ֹͣ�Ǹ��߳�
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD1)
				{
					
					StoryPanel.ch = '1';
					//�����ʱ�������  ֹͣ�Ǹ��߳�
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD2)
				{
					StoryPanel.ch = '2';
					//�����ʱ�������  ֹͣ�Ǹ��߳�
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD3)
				{
					StoryPanel.ch = '3';
					//�����ʱ�������  ֹͣ�Ǹ��߳�
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD4)
				{
					StoryPanel.ch = '4';
					//�����ʱ�������  ֹͣ�Ǹ��߳�
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				if(e.getKeyCode()==KeyEvent.VK_NUMPAD5)
				{
					StoryPanel.ch = '5';
					//�����ʱ�������  ֹͣ�Ǹ��߳�
					//if(!isStory)
						//StoryPanel.run.stop();
				}
				}
				//ֻ�в���ս����  ���ھ����У������̵��� ,�����ƶ�
				//���ܴ浵
				
				if(!isFight && !isStory)
				{
					if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[2]>=1)
						position.getXy()[2]--;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					
					//����isField���ĸ��ػ�
					//�ȴ����ºõ�ֵ��ȥ
					refreshField();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[2]<GameField.HEIGHT-1)
						position.getXy()[2]++;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					
					refreshField();
					//fight.setBounds(480,0,790,170);
					//fight.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[0]>=1)
						position.getXy()[0]--;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					
					//����isField���ĸ��ػ�
					refreshField();
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					position.getXy()[3]=position.getXy()[2];
					position.getXy()[1]=position.getXy()[0];
					if(position.getXy()[0]<GameField.WIDTH-1)
						position.getXy()[0]++;
					System.out.println("x: "+position.getXy()[0]
							+" y: "+position.getXy()[2]
							+" l_x: "+position.getXy()[1]
							+" l_y :"+position.getXy()[3]);
					//����isField���ĸ��ػ�
					refreshField();
				}
				//�浵��ť
				if(e.getKeyCode()==KeyEvent.VK_E)
				{
					/*save.setVisible(true);*/
					keepFile();
					/*isChange = true;*/
				}
				//ȡ����ť   ֮����Ըĳɼ�����ť  �м����浵
				if(e.getKeyCode()==KeyEvent.VK_R)
				{
					/*load.setVisible(true);*/
					loadFile();
					refreshFile();
					//isChange = false;
				}
			}
				//
			}
		});
	}
	//�ҵ�ǰField �����»���
	public static void refreshField()
	{
		//ͨ���ƶ��ر�ս��Ʒ��
		isBooty = false;
		booty.setBounds(480,315,0,0);
		GameField f;
		//��õ�ǰ��
		f = position.getF();
		//����f�Ĺ������
		f.rule();
		//Ӧ���ǰ��ҵ���ǰField  �ٴ�x y l_x l_y
		//ֵ��ȥ   �ٰ�ֵ������
		//one.rule(xy);
	}
	/*public static void main(String[] args) throws IOException
	{
		new Game().init();
	}*/
	//��дpaint?    Ϊ����������Ϸʱ   ȫ��һ���ػ�  
	public void paint(Graphics g)
	{
		info.repaint();
		//fight.repaint();
		//one.repaint();
		GameField f;
		//��õ�ǰ��
		f = position.getF();
		//����f�Ĺ������
		f.rule();
	}
	//ͨ��Hero�ĸ���  ����Info��Ϣ
	//д��timer  ��ʱ��������?
	private void refreshInfoByHero()
	{
		//����man�����num  ���ڴ浵
		man.refreshNum();
		info.getLv().setText(intToString(man.getLv()));
		info.getHp().setText(intToString(man.getHp()));
		info.getAttack().setText(intToString(man.getAttack()));
		info.getDefence().setText(intToString(man.getDefence()));
		info.getMoney().setText(intToString(man.getMoney()));
		info.getEx().setText(intToString(man.getEx()));
		info.getY_keys().setText(intToString(man.getY_keys()));
		info.getB_keys().setText(intToString(man.getB_keys()));
		info.getR_keys().setText(intToString(man.getR_keys()));
		//��װ�� �ӵ�ǰװ�����Ժ͸���info
		if(man.getHelmet()!=null)
		{
			info.getHelmet().setIcon(new ImageIcon(man.getHelmet().head));
		}
		else
			info.getHelmet().setIcon(null);
		if(man.getArmour()!=null)
		{
			info.getArmour().setIcon(new ImageIcon(man.getArmour().head));
			
		}
		else
			info.getArmour().setIcon(null);
		if(man.getShoe()!=null)
		{
			info.getShoe().setIcon(new ImageIcon(man.getShoe().head));
		}
		else
			info.getShoe().setIcon(null);
		if(man.getWeapon()!=null)
		{
			info.getWeapon().setIcon(new ImageIcon(man.getWeapon().head));
		}
		else
			info.getWeapon().setIcon(null);
		//������鳬��100  ����ֵ��100 ������5 ������5 ��������
		if(man.getEx()>=100)
		{
			man.setEx(man.getEx()-100);
			man.setHp(man.getHp()+100);
			man.setAttack(man.getAttack()+5);
			man.setDefence(man.getDefence()+5);
			man.setLv(man.getLv()+1);
			booty.showLvUp(man);
		}
		//�����װ���ͼӵ�ǰװ��������
		
	}
	//int ת string
	public static String intToString(int num)
	{
		return Integer.toString(num);
	}
	//�浵����   
	public  void keepFile()
	{
		/*heroҪ��  hp attack defence money ex lv y,b,r_keys
		���о���װ����Ϣһ��������װ�� ����4��intλ��
		13int

		¥��λ�ú�������Ϣ��һ��int[5]����

		¥��������Ϣ��һ��int[16][18]����*/
			
			try {
				
				randomfile.seek(0);
				//Ӣ����Ϣ
				for(int i=0;i<15;i++)
				{
					randomfile.writeInt(man.getNum()[i]);
					System.out.println(man.getNum()[i]);
				}
				for(int i=0;i<5;i++)
				randomfile.writeInt(position.getXy()[i]);
				for(GameField f :fieldList)
				for(int i=0;i<16;i++)
					for(int j=0;j<18;j++)
						randomfile.writeInt(f.flag[i][j]);
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		/*//��hero position ����ÿ���thing�����ȥ
		//�ö��������
		try {
			System.out.println(man);
			keep.writeObject(man);
			System.out.println("man:"+man);
			//System.out.println(position.getFloor());
			//keep.writeObject(position.getFloor());
			keep.writeObject(position.getXy());
			System.out.println(position.getXy()[0]+":"+
					position.getXy()[1]+":"+
					position.getXy()[2]+":"+
					position.getXy()[3]+":"+
					position.getXy()[4]);
			//������ÿ��thing
			for(GameField f :fieldList)
			{
				keep.writeObject(f.flag);
			}
			keep.writeObject(null);
			keep.flush();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
	}
	//���û�а��浵��ť  ����дһ�θ����ļ�  Ϊ���ö��ļ�ָ���λ
	private void un_keepFile()
	{
		/*try {
			keep.writeObject(h);
			keep.writeObject(temp);
			//������ÿ��thing
			for(GameField f :fieldList)
			{
				keep.writeObject(f.f_flag);
			}
			keep.writeObject(null);
			keep.flush();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
	}
	//��������
	public  void loadFile()
	{
		try {
			randomfile.seek(0);
			//Ӣ����Ϣ
			for(int i=0;i<15;i++)
			man.getNum()[i] = randomfile.readInt();
			for(int i=0;i<5;i++)
			position.getXy()[i] = randomfile.readInt();
			for(GameField f :fieldList)
			for(int i=0;i<16;i++)
				for(int j=0;j<18;j++)
					f.flag[i][j] = randomfile.readInt();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		/*Object o ;
		int i=0;
		//������˴浵��ť  ��ȡ���µĴ浵
		//ʲô������
			try {
				load = new ObjectInputStream(new FileInputStream(file));
				System.out.println("��load");
			} catch (FileNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		if(!isChange)
		{
			//��д�ļ�һ��
			un_keepFile();
			System.out.println("��load");
		}
			try {
			while((o = load.readObject())!=null)
			{
				if(o.getClass() == Hero.class)
					{	
						//Ϊ����man��֮ǰ�Ķ���һ��
					    //������һһ��ֵ
						h = (Hero)o;
						System.out.println("h:"+h);
						//man= (Hero)o;
					}
				//���� ¥����Ϣ
				if(o.getClass() == int[].class)
					{
					//���ﾹȻҲ�Ǵ�����  ��  �鷳
					temp = (int[])o;
					//System.out.println("asd:"+temp);
					}
				
				if(o.getClass() == int[][].class)
				{
					//������ֵ
					fieldList.get(i).flag =(int[][])o; 
					i++;
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		
		//��������
		//��Ȼ��ֵ����  ���ö��󴫵�  ���⴫������ʹ��ͬһ������
		for(int e=0;e<5;e++)
			{
				position.getXy()[e] = temp[e];
			}
		System.out.println(position.getXy()[0]+":"+
				position.getXy()[1]+":"+
				position.getXy()[2]+":"+
				position.getXy()[3]+":"+
				position.getXy()[4]);
		System.out.println(temp+"  "+position.getXy());
		position.setXy(temp);
		System.out.println(temp[0]+":"+temp[1]+":"+temp[2]);
		//��ÿ��flag  ��f_flag  ֻ�Ǵ�ֵ  �����Ǵ�����
		for(GameField f :fieldList)
		{
			for(int q=0;q<GameField.WIDTH;q++)
			{
				for(int j=0;j<GameField.HEIGHT;j++)
				{
					f.f_flag[q][j] = f.flag[q][j];
					//System.out.println(f.flag[q][j]);
				}
			}
		}
		//���û�и��´浵  ��ȡ�ϴε�(Ҳ���ǲ��߶�ȡ����)
				man.setHp(h.getHp());
				man.setEx(h.getEx());
				man.setAttack(h.getAttack());
				man.setDefence(h.getDefence());
				man.setLv(h.getLv());
				man.setGet_hp(h.getGet_hp());
				man.setGet_money(h.getGet_money());
				man.setY_keys(h.getY_keys());
				man.setB_keys(h.getB_keys());
				man.setR_keys(h.getR_keys());
				man.setGet_money(h.get_money);
				man.setHelmet(h.getHelmet());
				
					try {
						load.close();
						System.out.println("��load");
					} catch (IOException e) {
						// TODO �Զ����ɵ� catch ��
						e.printStackTrace();
					}*/
				
	}
	
	/*//����̸߳�������  
		class showFight extends Thread
		{
			public void run()
			{
				//ÿ��һ�����һ��
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				fight.repaint();
				System.out.println("123");
			}
		}*/
	//���¶���֮���   ������Ϣ
	public void refreshFile()
	{
		//����״̬
		//���µذ�  
		for(GameField f :fieldList)
		{	
			//����浵ǰû��
			//���������ڴ浵֮��  �����˱仯�Ļ�
			f.init();
			//�ȳ�ʼ����ǰ¥��
			int a=0;
			for(int i=0;i<GameField.WIDTH;i++)
			{
				for(int j=0;j<GameField.HEIGHT;j++)
				{
					//����иı�
					if(f.flag[i][j]==1)
					{	
						System.out.println("����");
						//��ǰ¥���ʼ�� ��ֻ��ʼ��һ��
						//if(a==0)
							//f.init();
						f.things[i][j] = new Floor();
						//a++;
					}
				}
			}
			//����hero
			man.setLv(man.getNum()[0]);
			man.setHp(man.getNum()[1]);
			man.setAttack(man.getNum()[2]);
			man.setDefence(man.getNum()[3]);
			man.setMoney(man.getNum()[4]);
			man.setEx(man.getNum()[5]);
			man.setY_keys(man.getNum()[6]);
			man.setB_keys(man.getNum()[7]);
			man.setR_keys(man.getNum()[8]);
			switch(man.getNum()[9])
			{
			case 0:man.setHelmet((new Equip("����", "helmet", 0, new Pig().getHead()
					, 10, 5, 5, 5)));
			default:man.setHelmet(null);
			}
			switch(man.getNum()[10])
			{
			/*case 0:man.setArmour((new Equip("����", "helmet", new Pig().getHead()
					, 10, 5, 5, 5)));*/
			default:man.setArmour(null);
			}
			switch(man.getNum()[11])
			{
			case 1:man.setWeapon(new Equip("����", "Armour", 1,Field3.img
					, 10, 5, 5, 5));
			default:man.setWeapon(null);
			}
			switch(man.getNum()[12])
			{
			/*case 0:man.setShoe((new Equip("����", "helmet", new Pig().getHead()
					, 10, 5, 5, 5)));*/
			default:man.setShoe(null);
			}
			man.setGet_hp(man.getNum()[13]);
			man.setGet_money(man.getNum()[14]);
			
		}
		//����¥��
		position.getF().setVisible(false);
		System.out.println(floor);
		position.setF(fieldList.get(position.getXy()[4]));
		//System.out.println(position.getFloor());
		position.getF().setVisible(true);
		refreshField();
		repaint();
	}
	//���¥�㵽Game
	private void addField(GameField f)
	{
		f.setBounds(480,0,800,900);
		add(f);
	}
}
