package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class R_pig extends Monster{
	//head ��ͷ��
		public R_pig()
		{	name = "��ë��";
			try {
				head = ImageIO.read(new File("src/jingame/icon/r_pig.gif"));
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			ex = 13;
			money = 6;
			hp = 20;
			defence = 12;
			attack = 4;
			//get_money = 2;
		}
}
