package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Pig extends Monster{
	//head ��ͷ��
	public Pig()
	{	name = "��ë��";
		try {
			head = ImageIO.read(new File("src/jingame/icon/pig.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		ex = 12;
		money = 5;
		hp = 15;
		defence = 10;
		attack = 4;
		//get_money = 2;
	}
	

}
