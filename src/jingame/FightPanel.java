package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jingame.Info.Background;

public class FightPanel extends JPanel{
	//��ܿ�  //���Ǵ�һ�������hero����  ��������ʾս�������
	//����������������  ˳���жϵ�ʧ  Ӯ�˾���ʾ��ս��Ʒ��  ���˾Ͱܱ���
	//���Ϸ�
	//ͨ���� �����hero����Ϣ  ��������˸��Ե�ͼ�� ������
	BufferedImage background;
	private final int TABLE_WIDTH = 790;
	private final int TABLE_HEIGHT = 170;
	//����JLabel
	JLabel m_image = new JLabel();// 25 15 115 95
	JLabel h_image = new JLabel();//640 15
	JLabel m_name = new JLabel();//40 130 115 35
	JLabel h_name = new JLabel();//650 130
	JLabel m_hp = new JLabel();;// 250 35 50 30
	JLabel m_attack = new JLabel();;//250 80
	JLabel m_defence = new JLabel();;//250 125
	JLabel h_hp = new JLabel();;//480 35
	JLabel h_attack = new JLabel();;//480 80
	JLabel h_defence = new JLabel();;//480 125
	Monster m;
	Hero h;
	public int mee=10;
	/*JFrame f = new JFrame();*/
	public FightPanel()
	{
	//ʵ������дpaint��JPanel
	Background back = new Background();		
	//����ͼ
	try {
		background = ImageIO.read(new File("src/jingame/icon/fight.png"));
	} catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
	m_image.setBounds(25, 15, 115, 95);
	m_name.setBounds(40, 130, 115, 35);
	m_hp.setBounds(250, 35, 50, 30);
	m_attack.setBounds(250, 80, 50, 30);
	m_defence.setBounds(250, 125, 50, 30);
	h_image.setBounds(640, 15, 115, 95);
	h_name.setBounds(650, 130, 115, 35);
	h_hp.setBounds(480, 35, 50, 30);
	h_attack.setBounds(480, 80, 50, 30);
	h_defence.setBounds(480,125,50,30);
	back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
	JLayeredPane layerPanel = new JLayeredPane();
	layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
	layerPanel.add(back,new Integer(1));
	layerPanel.add(m_image,new Integer(2));
	layerPanel.add(m_name,new Integer(2));
	layerPanel.add(m_attack,new Integer(2));
	layerPanel.add(m_defence,new Integer(2));
	layerPanel.add(m_hp,new Integer(2));
	layerPanel.add(h_image,new Integer(2));
	layerPanel.add(h_name,new Integer(2));
	layerPanel.add(h_attack,new Integer(2));
	layerPanel.add(h_defence,new Integer(2));
	layerPanel.add(h_hp,new Integer(2));
	this.add(layerPanel);
	//this.setOpaque(false);
	setVisible(true);
	//repaint();
	//setVisible(false);
	/*f.add(this);
	f.pack();
	f.setVisible(true);*/
	}
	class Background extends JPanel
	{
		public void paint(Graphics g)
	{
		//super.paint(g);
		g.drawImage(background, 0, 0, null);
	}
	}
	//��ʱ��������
	public boolean fight(Monster m, Hero h)
	{
		//��ʼ��ս����Ϣ
		this.m = m;
		this.h = h;
		ImageIcon m_head = new ImageIcon(m.head);
		ImageIcon h_head = new ImageIcon(h.head); 
		m_image.setIcon(m_head);
		h_image.setIcon(h_head);
		m_name.setText(m.name);
		h_name.setText(h.name);
		m_hp.setText(Game.intToString(m.hp));
		h_hp.setText(Game.intToString(h.hp));
		m_attack.setText(Game.intToString(m.attack));
		h_attack.setText(Game.intToString(h.attack));
		m_defence.setText(Game.intToString(m.defence));
		h_defence.setText(Game.intToString(h.defence));
		new Fight().start();
		//this.repaint();
		//������Ѫ��ʱ��
		/*if(h.hp>0 && m.hp<0 )
			return true;
		else if(m.hp >0 && h.hp<0)
			return false;*/
		return true;
  }
	class Fight extends Thread
	{	
		public void run()
		{
			//��Ѫ��������ʱ  ѭ��
			while( m.hp >0 && h.hp >0 )
			{    
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				//������﹥������Ӣ�۷������� ÿ�ο�һ��Ѫ
				if(m.attack < h.defence)
					h.hp--;
				else
					{
						//������Ѫ
						h.hp = h.hp -m.attack +h.defence;
						//�������������л�Ѫ ͵Ǯ����
						//h.setMoney(h.getMoney() -m.getGet_money());
						//��͵����Ǯ
						m.money = m.money - m.getGet_money();
						h.hp = h.hp - m.getGet_hp();
					}
				//System.out.println(h.hp);
				//�෴Ӣ��
				if(h.attack < m.defence)
					m.hp--;
				else
					{
						m.hp = m.hp - h.attack +m.defence;
						//h.setMoney(h.getMoney() +h.getGet_money());
						h.hp = h.hp + h.getGet_hp();
						//͵�˵�Ǯ
						m.money = m.money+h.getGet_money();
					}
				
				h_hp.setText(Game.intToString(h.hp));
				m_hp.setText(Game.intToString(m.hp));
				
			}
			Game.isFight = false;
			Game.fight.setBounds(480,0,0,0);
			Game.booty.showBooty(m,h);
		}
	}
}
