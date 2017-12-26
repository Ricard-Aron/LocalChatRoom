
package jingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field_shaolinshanxia extends GameField{

	BufferedImage pot;
	BufferedImage carrot;
	//BufferedImage floor;
	//1��Ͱ��  2��r_pig 3 ��g_pig  4 s_monk 5��carrot
	int[][] xy = {{0,0,0,0,0,1,1,1,0,0,1,0,0,0,0,0},
				  {0,0,0,0,0,1,0,1,2,2,1,5,1,1,0,0},
				  {0,0,0,0,1,0,0,1,0,3,1,0,0,4,0,0},
				  {0,0,1,1,5,0,5,1,1,4,1,0,1,1,1,0},
				  {0,0,0,4,1,1,3,2,1,2,1,2,1,0,0,0},
				  {0,0,1,1,0,0,4,2,5,1,1,4,1,1,0,0},
				  {0,0,0,0,0,0,2,0,2,0,3,0,3,1,0,0},
				  {0,0,1,1,0,2,0,0,0,0,1,0,3,1,0,0},
				  {0,1,0,1,0,0,0,0,1,0,1,0,0,0,0,0},
				  {0,5,0,1,1,1,1,1,1,0,1,1,1,1,1,0},
				  {0,1,0,1,4,1,0,0,2,0,0,4,1,0,0,0},
				  {0,1,4,0,0,1,0,1,5,1,1,2,1,0,0,0},
				  {0,0,0,0,4,1,4,0,0,0,1,0,1,1,1,0},
				  {0,1,1,0,0,0,1,3,5,4,1,0,1,0,0,0},
				  {0,0,0,1,0,0,1,3,0,0,1,0,0,4,0,0},
				  {0,3,4,0,0,0,5,1,4,1,1,4,1,1,1,0},
				  {0,4,4,2,0,0,1,0,0,0,1,0,1,0,0,0},
				  {0,2,4,3,0,0,1,0,0,0,1,1,1,0,0,0}};
		
	public Field_shaolinshanxia(Hero man) throws IOException {
		super(man);
		
	}
	//����¥����Ϣ  ���ܷ��ڹ��캯������  ��Ϊ��Щ¥�㻹û�й���  Ȼ�����ѭ�� nullָ����
		public void init()
		{
			try {
				pot = ImageIO.read(new File("src/jingame/icon/pot.gif"));
				carrot = ImageIO.read(new File("src/jingame/icon/carrot.gif"));
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			floors = "����ɽ��";
			for(int i=0;i<18;i++)
			{   
				for(int j=0;j<16;j++)
				{
					if(xy[i][j]==1)
						things[j][i]= new Wall(pot,0);
					if(xy[i][j]==5)
						things[j][i]= new Wall(carrot,0);
					if(xy[i][j]==4)
						things[j][i] = new S_monk();
					if(xy[i][j]==3)
						things[j][i] = new G_pig();
					if(xy[i][j]==2)
						things[j][i] = new R_pig();
				}
			}
			//���õذ�
			//����¥��λ��
			setLeft_x(8);
			setLeft_y(17);
			things[getLeft_x()][getLeft_y()] = new LeftStair();
			
			//��������¥��λ��	
			//����down_fΪҰ����
			setLeft_f(Game.fieldList.get(2));
		}
}