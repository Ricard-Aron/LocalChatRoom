package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import jingame.FightPanel.Background;

public class StoryPanel extends JPanel{
	//����������·�  �����ǶԻ� 
	//һ��ͷ���  һ�����ֿ�   NPC���������ݽṹ��Ի����ݣ�����һ��˵һ�䣿
	BufferedImage background;
	private final int TABLE_WIDTH = 790;
	private final int TABLE_HEIGHT = 250;
	private JLabel head;//35 25 140 140
	private JLabel content;//210 50 560 160
	private JLabel name;//60 180 150 60
	private Npc npc;
	private Hero man;
	//���̵�����̶�˵�ļ��仰
	private String msg1 = "�������Ҽҵĵ����ʲô���У�";
	private String hp = "1  ��������ֵ:";
	private String money = "���Ľ��:";
	private String attack = "2  ���ӹ�����:";
	private String defence = "3  ���ӷ�����:";
	private String ex = "4  ���Ӿ���ֵ:";
	private String exit = "�� '5'�˳��̵�Ŷ!";
	//��Door����̶����仰
	private String msg2 = "С�ӣ����������ˣ��뵽����ȥ?";
	private String y_k = "1 ��Ҫ��ɫӢ����:";
	private String b_k = "��Ҫ��ɫӢ����:";
	private String r_k = "��Ҫ��ɫӢ����:";
	private String fight = "3 �ҵ���·��������!";
	private String bride = "2 ��ү���к�,�Ҹ�����:";
	private String exit1 = "4 �һ���һ��������!";
	//����������ָ���  Ĭ��Ϊ0 �������鳤��
	public static int i =0;
	//�������̵���ŵ�
	public static char ch = '0';
	public static Run run;
	//��BufferedImageת����
	ImageIcon npc_head;
	ImageIcon man_head;
	//��ʼ��GUI
	public StoryPanel()
	{
		run = new Run();
		//ʵ������дpaint��JPanel
		Background back = new Background();
		head = new JLabel();
		content = new JLabel();
		name = new JLabel();
		//����ͼ
		try {
			background = ImageIO.read(new File("src/jingame/icon/story.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		head.setBounds(35, 25, 140, 140);
		content.setBounds(210, 50, 560, 160);
		name.setBounds(60, 180, 150, 60);
		back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
		JLayeredPane layerPanel = new JLayeredPane();
		layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		layerPanel.add(back,new Integer(1));
		layerPanel.add(head,new Integer(2));
		layerPanel.add(content,new Integer(2));
		layerPanel.add(name,new Integer(2));
		this.add(layerPanel);
		setVisible(true);
	}
	//����JPanel
	class Background extends JPanel
	{
		public void paint(Graphics g)
		{
		//super.paint(g);
		g.drawImage(background, 0, 0, null);
		}
	}
	//��һ��Npc ��һ��Hero  ����������أ��϶������ݰ�
	public void showStory(Npc npc, Hero man)
	{
		//��Game������жϵ������ھ�����
		Game.isStory = true;
		//����
		this.npc = npc;
		this.man = man;
		npc_head = new ImageIcon(npc.head);
		man_head = new ImageIcon(man.head);
		head.setIcon(null);
		head.setText(null);
		content.setText(null);
		name.setText(null);
		System.out.println("����");
		ch ='0';
		i=0;
		run.start();
	}
	class Run extends Thread
	{
		
		public void run()
		{
						
						//��������̵����棬�Ǿ�1,2,3,4�İ�����Ӧ
						//ԭ��Ҳ��һ�� ����ֵ ������ ������ ����ֵ
			
			System.out.println("����1");
			System.out.println("����2");
				//����ھ���(Ҳ���Ǿ���  �� ��)ģʽ��
				if(Game.isStory)
				{
					//System.out.println("����3");
					//��������ھ�����  �Ȱ�ͷ�����ʾ����
					if(npc.isStory)
					//true ��hero
					{
						if(npc.storylist[0].isWhoTalk())
						{	
						head.setIcon(man_head);
						name.setText(man.getName());
						}
						else
						{
						head.setIcon(npc_head);
						name.setText(npc.getName());
						}
					}
					//������̵���,��  Ҳ����ʾͷ��
					else
						{	head.setIcon(npc_head);
							name.setText(npc.getName());
						}
				}
			//���Ը��߳��Ƿ�������
				ActionListener taskPerformer = evt ->
		        {
		        	//System.out.println(i);
		        	//System.out.println(i);
					//System.out.println("����4");
						
						
						//����ھ�����  ��qˢ��
		        	if(npc.isStory)
		        	{	//����storylist   ����GUI����
		        		if(i<npc.getStorylist().length)
						{
						    	
								if(npc.storylist[i].isWhoTalk())
								{	
									head.setIcon(man_head);
									name.setText(man.getName());
								}

								else
									{
									head.setIcon(npc_head);
									name.setText(npc.getName());
									}
								content.setText(npc.storylist[i].getContent());
						}
						else
						{
							Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
						}
		        	}
		        	//������̵���  �� 1 2 3 4 5ѡ��
		        	if(npc.isStore)
		        	{
		        		//��ˢ���̵�Ŀ¼
		        		head.setIcon(npc_head);
						name.setText(npc.getName());
		        		content.setText("<html>"+msg1 + "<br>" 
		        				+hp + npc.getNumList()[0].getNum()
		        				+money + npc.getNumList()[0].getMoney()
		        				+"<br>" + attack +npc.getNumList()[1].getNum()
		        				+money + npc.getNumList()[1].getMoney()
		        				+"<br>" + defence +npc.getNumList()[2].getNum()
		        				+money + npc.getNumList()[2].getMoney()
		        				+"<br>" + ex +npc.getNumList()[3].getNum()
		        				+money + npc.getNumList()[3].getMoney()
		        				+"<br>" + exit+"</html>");
		        		System.out.println(ch);
		        		switch(ch)
		        		{
		        		//�����1  �ж��Ƿ���Ǯ  ����Hero
		        		case '1':
		        			//��Ǯ  ��Ǯ  ûǮ����
		        			if(man.getMoney()>=npc.getNumList()[0].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[0].getMoney());
		        				man.setHp(man.getHp()+npc.getNumList()[0].getNum());
		        			}
		        			//c ��λ
		        			ch = '0';
		        			break;
		        		case '2':
		        			//��Ǯ  ��Ǯ  ûǮ����
		        			if(man.getMoney()>=npc.getNumList()[1].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[1].getMoney());
		        				man.setAttack(man.getAttack()+npc.getNumList()[1].getNum());
		        			}
		        			ch = '0';
		        			break;
		        		case '3':
		        			//��Ǯ  ��Ǯ  ûǮ����
		        			if(man.getMoney()>=npc.getNumList()[2].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[2].getMoney());
		        				man.setDefence(man.getDefence()+npc.getNumList()[2].getNum());
		        			}
		        			ch = '0';
		        			break;
		        		case '4':
		        			//��Ǯ  ��Ǯ  ûǮ����
		        			if(man.getMoney()>=npc.getNumList()[3].getMoney())
		        			{
		        				man.setMoney(man.getMoney()-npc.getNumList()[3].getMoney());
		        				man.setEx(man.getEx()+npc.getNumList()[3].getNum());
		        			}
		        			ch = '0';
		        			break;
		        		case '5':
		        			//�˳��̵�
		        			ch = '0';
		        			Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
		        		}
		        		
		        	}
		        	//������� ��
		        	if(npc.isDoor)
		        	{
		        		head.setIcon(npc_head);
						name.setText(npc.getName());
		        		content.setText("<html>"+msg2 + "<br>" 
		        				+y_k + npc.getNum2List()[0]
		        				+b_k + npc.getNum2List()[1]
		        				+r_k + npc.getNum2List()[2]
		        				+"<br>" +bride +npc.getNum2List()[3]
		        				+"<br>" +fight
		        				+"<br>" + exit1+"</html>");
		        		switch(ch)
		        		{
		        		//�����1  �ж��Ƿ���Ʊ  ����Hero
		        		case 'w':
		        			//����Ʊ  ����Ʊ  û��Ʊ����
		        			//�ж�������Ʊ��  ������Ҫ������Ʊ�����
		        			if(man.getY_keys()>=npc.getNum2List()[0]
		        			&& man.getB_keys()>=npc.getNum2List()[1]
		        			&& man.getR_keys()>=npc.getNum2List()[2])
		        			{
		        				//����Ʊ
		        				man.setY_keys(man.getY_keys()-npc.getNum2List()[0]);
		        				man.setB_keys(man.getB_keys()-npc.getNum2List()[1]);
		        				man.setR_keys(man.getR_keys()-npc.getNum2List()[2]);
		        				Game.isStory = false;
								Game.story.setBounds(0,0,0,0);
								Game.position.getF()
		        				.things[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= new Floor();
								Game.position.getF()
		        				.flag[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= 1;
		        				
		        			}
		        			//c ��λ
		        			ch = '0';
		        			break;
		        		case '2':
		        			//��Ǯ  ��Ǯ  ûǮ����    ���ǻ�¸
		        			if(man.getMoney()>=npc.getNum2List()[3])
		        			{
		        				man.setMoney(man.getMoney()-npc.getNum2List()[3]);
		        				//��ǰλ�õ��� ���� �ذ�
		        				Game.position.getF()
		        				.things[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= new Floor();
		        				Game.position.getF()
		        				.flag[Game.position.getXy()[0]][Game.position.getXy()[2]] 
		        						= 1;
		        				Game.isStory = false;
								Game.story.setBounds(0,0,0,0);
		        			}
		        			ch = '0';
		        			break;
		        		case '3':
		        			//���   ��������Ĺ�������
		        			npc.m.doJob(man, Game.position.getXy()[0]
		        					, Game.position.getXy()[2]);
		        			Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
		        			ch = '0';
		        			break;
		        		case '4':
		        			//�˳�
		        			ch = '0';
		        			//�����˻���һ��λ��
		        			Game.position.getXy()[0]=Game.position.getXy()[1];
		        			Game.position.getXy()[2]=Game.position.getXy()[3];
		        			//��ʱ���»��� 
		        			Game.refreshField();
		        			Game.isStory = false;
							Game.story.setBounds(0,0,0,0);
		        			break;
		        		}
		        	}
		        };
		        Timer timer = new Timer(1000,taskPerformer);
		        timer.start();
				
	}
	}
}


















