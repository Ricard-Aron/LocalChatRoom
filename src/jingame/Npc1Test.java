package jingame;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Npc1Test extends Npc{

	//���Ծ���Npc
	public Npc1Test()
	{
		//Ϊ����npc
		isStory = true;
		//��������
		name = "������";
		//����ͷ��
		try {
			head = ImageIO.read(new File("src/jingame/icon/qwe.gif"));
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
		//һ����仰
		storylist = new StoryPoint[15];
		storylist[0] = setStoryPoint(false, "С�ӿ���������棬����ͽ����?");
		storylist[1] = setStoryPoint(true, "(..��ô������һ��ƭ��?)");
		storylist[2] = setStoryPoint(true, "����ͽ�ܸ���?");
		storylist[3] = setStoryPoint(true, "(���ԣ���Ҳƭƭ������)");
		storylist[4] = setStoryPoint(false, "�㲻�뵱��ͽ����");
		storylist[5] = setStoryPoint(true, "�ð����ҵ���ͽ��");
		storylist[6] = setStoryPoint(false, "�����ںɻ��ص���,������ҪС��·�ϵĶ���");
		storylist[7] = setStoryPoint(false, "Ұ���ֵ�Ұ��Ƥ�����");
		storylist[8] = setStoryPoint(false, "�����µĵ���̰������");
		storylist[9] = setStoryPoint(false, "����ɽ�µĳ�����������Ƥ��");
		storylist[10] = setStoryPoint(false, "�����ɻ��ص���Ů��������Ǯ��");
		storylist[11] = setStoryPoint(false, "Ŷ���һ���˵���ߺ�����ʲô������");
		storylist[12] = setStoryPoint(true, "�������������������˾���?��");
		storylist[12] = setStoryPoint(true, "���кɻ�����ôȥ�����Ҳ�֪����!");
		storylist[13] = setStoryPoint(false, "������컯��(�ⶼ���������ٺ�)");
		storylist[14] = setStoryPoint(true, "������ô�����ˣ����������˶���������");
		//����һ����ʾ
	}
	public Npc1Test(boolean isStory, String name
			, BufferedImage head, StoryPoint[] storylist)
	{
		this.isStory = isStory;
		this.name = name;
		this.head = head;
		this.storylist = storylist;
	}
	
}
