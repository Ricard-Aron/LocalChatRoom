package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LeftStair implements Thing{
	private transient BufferedImage leftStair;
	public LeftStair()
	{
		try {
			leftStair = ImageIO.read(new File("src/jingame/icon/stair.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		
		GameField f;
		//��õ�ǰ¥���left¥��
		f = Game.position.getF().getLeft_f();
		//����x y ����left�� ��right ��λ�� �Ҹо�Ҫ+1��Ȼ��������ת��
		Game.position.getXy()[0]=f.getRight_x()+1;
		Game.position.getXy()[2]=f.getRight_y();
		//��ǰ¥�����óɲ�����
		//up���óɿ���
		Game.position.getF().setVisible(false);
		f.setVisible(true);
		//Ȼ�����õ�ǰ¥��  Ϊ��ǰ��up
		Game.position.setF(f);
		Game.position.getXy()[4]=(Game.fieldList.indexOf(f));
		System.out.println(Game.fieldList.indexOf(f));
	}
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return leftStair;
	}
}
