package jingame;

import java.awt.image.BufferedImage;

public class Monster extends Creature implements Thing{

	
	//��ʵֻҪ�����ʱ���������Ծ��� ����Ҫ��pig��������һ��������
	//��������Ҳ��   
	public Monster() {}
	public Monster(String name, int hp, int attack, int defence
			, int money, int ex, BufferedImage head)
	{
		this.name = name;
		this.hp = hp;
		this.attack = attack;
		this.defence = defence;
		this.money = money;
		this.ex = ex;
		this.head = head;
	}
	protected boolean fight(Hero man) {
		//Ҫ�ø�Dialog��ʾ������    �����һ��JPanel  ÿ�δ�ֵ��ȥ 
		//����ʾ ֱ��ĳһ��hp=0   Ȼ��JPanel����  �ٳ����жϵ���˭Ӯ  ���Ӯ���ٳ�һ��Jpanel��
		//���ó�ս�����  Ȼ���������������Щ����һ��JPanel ���м���ʾ���� ���߰������أ�
		//�������˼·  ���̵�ʲô�ľͼ��˰�  ������ʾ�����ݷ��� ������ ʵ�ʲ���
		//��������ʾ���������
		//����Game��Fight���
		//��������ʾ
		Game.fight.setBounds(480,0,790,170);
		Game.isFight = true;
		
		/*try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
		return Game.fight.fight(this,man);
		/*return true;*/
	}
	//��дdoJob
	public void doJob(Hero man,int i, int j) {
		//��������¼������Ӯ���µ�ǰλ����Ϣ���������Ϸ����
		if(fight(man))
		{
			//��ǰλ�ù�����ʧ
			System.out.println("��ʧ!");
			Game.position.getF().things[i][j] = new Floor();
			Game.position.getF().flag[i][j] = 1;
		}
	}
	//��дgetimage
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return head;
	}
}