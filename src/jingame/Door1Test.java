package jingame;

import java.awt.image.BufferedImage;

public class Door1Test extends Npc{

	//���о���� ���п���ս������ķ���
	//��Ϊ���Ѿ��̳���npc������Ҫnew һ��monsterΪ�˲���ս������
	//��ΪҪ�ظ�����  ����ֱ�ӹ�������
	public Door1Test(String name, boolean isStory, boolean isStore
			, boolean isAdventure, boolean isDoor, StoryPoint[] storylist
			, NumPoint[] numList, int[] num2List
			, BufferedImage head, Monster m)
	{
		super(name, isStory, isStore, isAdventure, isDoor
				,storylist, numList, num2List, head);
		this.m = m;
	}
	public void doJob(Hero man, int i, int j) {
		// TODO �Զ����ɵķ������
		//�Ȱ�storyBounds���
				Game.story.setBounds(480,345,790,250);
				Game.isStory = true;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO �Զ����ɵ� catch ��
					e.printStackTrace();
				}
				Game.story.showStory(this , man);
		
	}
}
