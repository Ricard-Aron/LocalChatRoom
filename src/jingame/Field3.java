package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Field3 extends GameField{
	
	public Field3(Hero man) throws IOException {
		super(man);
			
	}
	static BufferedImage img;
		public void init()
		{
			floors = "������";
			//����¥��λ��
			setRight_x(12);
			setRight_y(5);
			try {
				img = ImageIO.read(new File("src/jingame/icon/helmet.png"));
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			/*things[10][2] = new Wall();*/
			things[getRight_x()][getRight_y()] = new RightStair();
			things[1][15] = new Equip("����", "Armour", 1,img
					, 10, 5, 5, 5);
			//����¥����Ϣ  ���ܷ��ڹ��캯������  ��Ϊ��Щ¥�㻹û�й���  Ȼ�����ѭ�� nullָ����
			//��������¥��λ��
			//����right_FΪ��һ��
			setRight_f(Game.fieldList.get(2));
		}
}
