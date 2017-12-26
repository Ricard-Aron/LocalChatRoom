package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class DownStair implements Thing{
	private BufferedImage downStair;
	public DownStair()
	{
		try {
			downStair = ImageIO.read(new File("src/jingame/icon/stair1.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		GameField f;
		//��õ�ǰ¥���down¥��
		f = Game.position.getF().getDown_f();
		//����x y �����²� ��up ��λ�� �Ҹо�Ҫ+1��Ȼ��������ת��
		Game.position.getXy()[0]=f.getUp_x()+1;
		Game.position.getXy()[2]=f.getUp_y();
		//��ǰ¥�����óɲ�����
		//up���óɿ���
		Game.position.getF().setVisible(false);
		f.setVisible(true);
		//Ȼ�����õ�ǰ¥��  Ϊ��ǰ��up
		Game.position.setF(f);
		Game.position.getXy()[4]=(Game.fieldList.indexOf(f));
	}
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return downStair;
	}
}
