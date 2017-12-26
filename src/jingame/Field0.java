package jingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Field0 extends GameField{

	BufferedImage wall;
	//��ͼ  Ϊ1��wall
	int[][] xy = {{0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1},
				  {0,0,0,0,0,1,0,1,1,1,1,1,1,1,1,1},
			      {1,1,1,1,0,1,0,1,0,0,0,0,0,0,0,1},
			      {0,0,0,1,0,1,0,1,0,1,0,1,1,0,0,1},
			      {0,0,0,1,0,1,0,1,0,1,0,1,1,1,0,1},
			      {0,0,0,0,0,1,1,1,0,1,1,1,1,1,0,1},
			      {0,1,1,1,1,1,0,0,0,0,0,0,0,1,0,1},
			      {0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,1},
			      {0,1,0,0,1,0,1,0,0,0,0,1,0,1,0,1},
			      {0,0,0,1,1,0,1,0,0,0,0,1,0,1,0,1},
			      {1,1,1,1,1,0,1,0,0,0,0,1,0,1,0,1},
			      {0,0,0,0,0,0,1,1,1,1,1,1,0,1,0,1},
			      {0,0,1,1,1,1,1,1,0,0,0,1,0,1,0,1},
			      {0,0,1,0,0,0,0,1,0,0,0,1,0,1,0,1},
			      {0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,1},
			      {0,0,1,0,0,0,0,1,0,1,0,1,0,1,0,1},
			      {0,0,1,1,1,1,1,1,0,1,0,0,0,1,0,1},
			      {0,0,0,0,0,0,0,0,0,1,1,1,1,1,0,1}};
	
	public Field0(Hero man) throws IOException {
		
		super(man);
	}
	
	//����¥����Ϣ  ���ܷ��ڹ��캯������  ��Ϊ��Щ¥�㻹û�й���  Ȼ�����ѭ�� nullָ����
	public void init()
	{
		
		try {
			wall = ImageIO.read(new File("src/jingame/icon/wall.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//��ͼ����
				floors = "�ξ�ͨ��";
				//��������¥��λ��
				setUp_x(15);
				setUp_y(17);
				//background = ImageIO.read(new File("src/jingame/icon/test.png"));
				//��ͼ��wall��ʼ��
				for(int i=0;i<18;i++)
				{   
					for(int j=0;j<16;j++)
					{
						if(xy[i][j]==1)
							things[j][i]= new Wall(wall,0);
					}
				}
				
				things[up_x][up_y] = new UpStair();
				/*for(int i=0;i<18;i++)
				{   System.out.print("{");
					for(int j=0;j<16;j++)
						System.out.print("0,");
					System.out.print("},");
					System.out.println("");
				}*/
		//����up_fΪ Ұ����
				setUp_f(Game.fieldList.get(2));
		
	}
	//��дrule
	//������дrule��
	//��дpaint
}
