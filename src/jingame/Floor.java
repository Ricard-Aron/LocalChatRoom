package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floor implements Thing{

	transient BufferedImage floor;
	public Floor()
	{
		try {
		floor = ImageIO.read(new File("src/jingame/icon/floor.png"));
	} catch (IOException e) {
		// TODO �Զ����ɵ� catch ��
		e.printStackTrace();
	}
	}
	public Floor(BufferedImage floor)
	{
		/*try {
			floor = ImageIO.read(new File("src/jingame/icon/floor.png"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/this.floor = floor;
	}
	//�ذ����ʲô������
	public void doJob(Hero man,int i, int j) {
	}
	@Override
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return floor;
	}

	
}
