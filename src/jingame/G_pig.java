package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class G_pig extends Monster{
	//head ��ͷ��
			public G_pig()
			{	name = "��ë��";
				try {
					head = ImageIO.read(new File("src/jingame/icon/g_pig.gif"));
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				ex = 15;
				money = 6;
				hp = 25;
				defence = 12;
				attack = 4;
				//get_money = 2;
			}
}
