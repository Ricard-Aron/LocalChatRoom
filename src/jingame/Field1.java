package jingame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Field1 extends GameField{

	BufferedImage wall;
	BufferedImage tree;
	BufferedImage door;
	//��ͼ  Ϊ1��wall 2 ���� 3������ 4 ���� 5 ����
		/*int[][] xy = {{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					  {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				      {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}};*/
		int[][] xy = {{0,0,0,0,0,2,2,2,2,0,0,0,0,2,0,0},
				  	  {0,0,0,0,0,2,0,0,2,0,0,0,0,2,0,3},
			          {0,0,0,0,0,2,0,0,2,2,2,2,0,2,0,0},
			          {2,2,2,2,3,2,0,0,3,0,0,2,0,2,0,2},
			          {0,0,2,0,4,0,0,0,2,0,0,0,0,2,0,0},
			          {0,0,2,2,0,3,2,0,2,0,0,2,3,2,0,2},
			          {0,3,2,2,0,0,2,0,3,0,0,0,0,2,0,0},
			          {3,3,2,2,0,0,2,0,2,2,2,2,0,2,2,0},
			          {5,5,2,2,5,3,2,0,2,0,2,2,0,2,0,0},
			          {4,4,2,2,0,0,0,0,2,0,2,2,0,2,0,0},
			          {3,3,2,2,2,2,2,2,0,0,2,2,0,2,0,0},
			          {3,3,2,2,0,0,0,2,0,0,2,2,0,2,0,0},
			          {0,0,2,2,0,0,0,2,0,0,0,0,0,2,0,0},
			          {0,0,0,0,0,0,0,2,0,0,0,0,2,0,0,0},
			          {0,0,0,0,0,0,0,2,0,0,0,2,0,0,0,0},
			          {0,0,0,0,0,0,0,2,0,0,2,0,0,0,0,0},
			          {0,0,0,0,0,0,0,0,0,2,0,0,0,0,0,0},
			          {0,0,0,0,0,0,0,2,0,3,0,0,3,0,0,0}};
	
	
	public Field1(Hero man) throws IOException {
		
		super(man);
	}
	
	//����¥����Ϣ  ���ܷ��ڹ��캯������  ��Ϊ��Щ¥�㻹û�й���  Ȼ�����ѭ�� nullָ����
	public void init()
	{
		
		try {
			wall = ImageIO.read(new File("src/jingame/icon/wall.gif"));
			tree = ImageIO.read(new File("src/jingame/icon/tree.gif"));
			door = ImageIO.read(new File("src/jingame/icon/2.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//��ͼ����
				floors = "Ұ����";
				//����¥��λ��
				setUp_x(10);
				setUp_y(15);
				setRight_x(14);
				setRight_y(0);
				setLeft_x(0);
				setLeft_y(5);
				int[] test = {0,1,0,20};
				//background = ImageIO.read(new File("src/jingame/icon/test.png"));
				//��ͼ�ϳ����ذ� ������thing
				for(int i=0;i<18;i++)
				{   
					for(int j=0;j<16;j++)
					{
						if(xy[i][j]==2)
							things[j][i]= new Wall(tree,0);
						if(xy[i][j]==3)
							things[j][i] = new Pig();
						if(xy[i][j]==4)
							things[j][i] = new G_pig();
						if(xy[i][j]==5)
							things[j][i] = new R_pig();
					}
				}
				
				
				things[up_x][up_y] = new UpStair();
				things[left_x][left_y] = new LeftStair();
				things[right_x][right_y] = new RightStair();
				
				things[1][2] = new Npc1Test();
				things[1][12] = new Store1Test();
				things[right_x][right_y+3] = new Door1Test("���Ź�", false, false, false, true
						, null , null,test,door ,new Pig());
				
		//����up_fΪ ������
				setUp_f(Game.fieldList.get(3));
		//����right_f Ϊ����ɽ��
				setRight_f(Game.fieldList.get(5));
		//����left_fΪ������
				setLeft_f(Game.fieldList.get(4));
	}
	//��дrule
	//������дrule��
	//��дpaint
}
