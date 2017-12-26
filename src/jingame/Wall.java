package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Wall implements Thing{

	private transient BufferedImage wall;
	//������ ǽ  �Ϳ�Ѫ
	private int sub_hp;
	public Wall(BufferedImage head,int sub_hp)
	{
		
			wall = head;
			this.sub_hp = sub_hp;
	}
	//ǽ�赲��  ����field x y = l_x l_y  ������ԭ�ز���
	public void doJob(Hero man,int i, int j) {
		Game.position.getXy()[0]=Game.position.getXy()[1];
		Game.position.getXy()[2]=Game.position.getXy()[3];
		//�����Ƕ��������wall ��һ��Ѫ  ���� ��������������  ˬ��
		man.setHp(man.getHp()-sub_hp);
	}
	public BufferedImage getWall() {
		return wall;
	}
	public void setWall(BufferedImage wall) {
		this.wall = wall;
	}
	@Override
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return wall;
	}

}
