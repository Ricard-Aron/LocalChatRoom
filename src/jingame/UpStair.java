package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UpStair implements Thing{
	private transient BufferedImage upStair;
	public UpStair()
	{
		try {
			upStair = ImageIO.read(new File("src/jingame/icon/stair.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	
	public void doJob(Hero man,int i, int j) {
		
		GameField f;
		//��õ�ǰ¥���up¥��
		f = Game.position.getF().getUp_f();
		//����x y �����ϲ� ��down ��λ�� �Ҹо�Ҫ+1��Ȼ��������ת��
		Game.position.getXy()[0]=f.getDown_x()+1;
		Game.position.getXy()[2]=f.getDown_y();
		//��ǰ¥�����óɲ�����
		//up���óɿ���
		Game.position.getF().setVisible(false);
		f.setVisible(true);
		//Ȼ�����õ�ǰ¥��  Ϊ��ǰ��up
		Game.position.setF(f);
		Game.position.getXy()[4]=(Game.fieldList.indexOf(f));
	}
	@Override
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return upStair;
	}
}
