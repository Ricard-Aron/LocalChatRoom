/*package jingame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import jingame.BootyPanel.Background;

public class Show_Monster extends JPanel{
	//��ʾ����Ĺ�����Ϣ
	//ս��Ʒ��ʾ��  ��ʾ���м�
		//���������  һ���ǵõ���� �õ�����
		//��һ���ǻ��װ��   ���о�������
		BufferedImage background;
		private final int TABLE_WIDTH = 790;
		private final int TABLE_HEIGHT = 920;
		
		//11��label  ����10����ʾ��ǰ�����  1������Ϊ����
		JLabel title;
		JLabel label1;
		JLabel label2;
		JLabel label3;
		JLabel label4;
		JLabel label5;
		JLabel label6;
		JLabel label7;
		JLabel label8;
		JLabel label9;
		JLabel label10;
		String name = "����: ";
		String hp ="����ֵ: ";
		String attack = "������: ";
		String defence = "������: ";
		public Show_Monster() throws IOException
		{
			//ʵ������дpaint��JPanel
			Background back = new Background();
			
			background = ImageIO.read(new File("src/jingame/icon/booty.png"));
			//����label�Ķ�λ
			
			back.setBounds(0, 0, TABLE_WIDTH, TABLE_HEIGHT);
			//�����ӵĲ㼶��
			JLayeredPane layerPanel = new JLayeredPane();
			layerPanel.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
			//�ײ�
			layerPanel.add(back,new Integer(1));
			//�ϲ�
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
		//��ʾ��ǰ��Ĺ�����Ϣ
		public void showMonster()
		{
			
		}
}*/
