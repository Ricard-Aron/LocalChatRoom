package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ByPaintToMove{

	JFrame f = new JFrame("move");
	MoveTest m = new MoveTest();
	//����
	int x=0;
	int y=0;
	int l_x;
	int l_y;
	//λ����
	int x_move = 27;
	int y_move = 27;
	//File file = new File("icon/icon.png");
	BufferedImage icon;// = ImageIO.read(file);
	public void init() throws Exception
	{
		icon = ImageIO.read(new File("icon/icon.png"));
		ImageIcon i = new ImageIcon("src/jingame/icon/5.gif");
		JLabel l = new JLabel(i);
		JButton b=new JButton("asdasd");
		//test��λ��
		l.setBounds(30, 45, 40, 40);
		b.setBounds(30, 50, 20, 20);
		f.setLayout(null);
		f.add(l);
		f.add(b);
		//f.add(m);
		f.addKeyListener(new KeyAdapter()
		{
			public void keyPressed(KeyEvent e)
			{
				if(e.getKeyCode()==KeyEvent.VK_UP)
				{
					l_y=y;l_x=x;
					if(y>=1)
					y--;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_DOWN)
				{
					l_y=y;l_x=x;
					y++;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_LEFT)
				{
					l_x=x;l_y=y;
					if(x>=1)
					x--;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
				if(e.getKeyCode()==KeyEvent.VK_RIGHT)
				{
					l_x=x;l_y=y;
					x++;
					System.out.println("x: "+x+" y: "+y
							+" l_x: "+l_x+" l_y :"+l_y);
					m.repaint();
				}
			}
		});
		f.setPreferredSize(new Dimension(250,250));
		f.pack();
		f.setVisible(true);
		
	}
	public static void main(String[] args) throws Exception {
		// TODO �Զ����ɵķ������
		new ByPaintToMove().init();
	}

	class MoveTest extends JPanel
	{
		public void paint(Graphics g)
		{
			//�����   �����ħ������ô���  ���ǹ�����Щ�������Ⱦͻ��õ�?
			//���ǰ���һ�����껭�ɱ�����
			//ͨ��Ŀǰx y ֵ ����һ��x y ֵ �ȽϾ�֪�������ĸ��������Ծ��ػ��ĸ�
			g.setColor(new Color(255,0,0));
			g.fillRect(l_x*x_move,l_y*x_move,x_move,x_move);
			//clearRect��fillRect��ʵԭ��һ��  fillRectЧ������ ��ͨ��setColorָ����ɫ
			//��clearRect�������޸���ɫ.
			g.drawImage(icon, x*x_move, y*x_move, null);
			//�о���������   ������ƶ�ͼƬ�أ�
			//
		}
	}
}
