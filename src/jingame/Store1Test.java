package jingame;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Store1Test extends Npc{

	//�����̵�
	public Store1Test ()
	{
		//���̵�
		isStore = true;
		//��������
		name = "��ӹ�ӻ��� ";
		//����ͷ��
				try {
					head = ImageIO.read(new File("src/jingame/icon/7.gif"));
				} catch (IOException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
		//����ֵ�б�
		numList = new NumPoint[4];
		//��������
		//��һ��������ֵ   (���ԣ����)
		numList[0] = setNumPoint(100,20);
		//������
		numList[1] = setNumPoint(5, 20);
		//������
		numList[2] = setNumPoint(5, 20);
		//����ֵ
		numList[3] = setNumPoint(20,20);
	}
	public void doJob(Hero man, int i, int j) {
		// TODO �Զ����ɵķ������
		//�Ȱ�storyBounds���
		Game.position.getXy()[0]=Game.position.getXy()[1];
		Game.position.getXy()[2]=Game.position.getXy()[3];
				Game.story.setBounds(480,345,790,250);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				Game.story.showStory(this , man);
		
	}
}
