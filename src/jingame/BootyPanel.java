package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jingame.FightPanel.Background;

public class BootyPanel extends JPanel{
	//ս��Ʒ��ʾ��  ��ʾ���м�
	//���������  һ���ǵõ���� �õ�����
	//��һ���ǻ��װ��   ���о�������
	BufferedImage background;
	private final int TABLE_WIDTH = 790;
	private final int TABLE_HEIGHT = 150;
	
	//
	JLabel label;
	String money ="��ý�� : ";
	String ex = "��þ��� : ";
	String thing ="���װ�� : ";
	String defeat = "����";
	public BootyPanel() throws IOException
	{
		//ʵ������дpaint��JPanel
		Background back = new Background();
		
		background = ImageIO.read(new File("src/jingame/icon/booty.png"));
		label = new JLabel();
		label.setBounds(70, 35, 650, 90);
		back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
		JLayeredPane layerPanel = new JLayeredPane();
		layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		layerPanel.add(back,new Integer(1));
		layerPanel.add(label,new Integer(2));
		this.add(layerPanel);
	}
	class Background extends JPanel
	{
		public void paint(Graphics g)
		{
		//super.paint(g);
		g.drawImage(background, 0, 0, null);
		}
	}
	public void showBooty(Thing thing, Hero h)
	{
		Game.booty.setBounds(480,315,TABLE_WIDTH,TABLE_HEIGHT);
		//�����ж�thing������  
		//����ǹ���
		//System.out.println(thing.getClass()+"    "+Monster.class);
		if(thing instanceof Monster)
		{
			System.out.println("����");
			Monster m = (Monster)thing;
			h.setMoney(h.getMoney()+m.money);
			h.setEx(h.getEx()+m.ex);
			label.setText(defeat + m.getName() + "," + money +m.money+" , "+ex+m.ex);
		}
		//��ʾ����
		Game.isBooty = true;
		//if(thing.getClass() == Monster.class)
			
	}
	public void showLvUp(Hero h)
	{
		Game.booty.setBounds(480,315,TABLE_WIDTH,TABLE_HEIGHT);
		label.setText("��ϲ "+h.getName()+"����! ,"+"�������ֵ:100,������:5,������:5"
				+ "�ٽ�����Ŷ!  ");
		//��ʾ����
		Game.isBooty = true;
	}
	public void showGetEquip(Equip e, Hero h)
	{
		Game.booty.setBounds(480,315,TABLE_WIDTH,TABLE_HEIGHT);
		label.setText("��ϲ "+h.getName()+"���װ��:"+e.getName()
		+"���ӹ���:"+e.getAttack()+"���ӷ���:"+e.getDefence()
		+"ÿ�ι�����Ѫ:"+e.getGet_hp()+"ÿ��͵Ǯ:"+e.getGet_money());
		//��ʾ����
		Game.isBooty = true;
	}
}
