package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Thief extends Monster{
	//head ��ͷ��
	public Thief()
	{	name = "С����";
		try {
			head = ImageIO.read(new File("src/jingame/icon/5.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ex = 16;
		money = 5;
		hp = 50;
		defence = 5;
		attack = 6;
		get_money = 1;
	}
}
