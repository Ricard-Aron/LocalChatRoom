package jingame;

import java.awt.image.BufferedImage;
import java.beans.Transient;

public class Npc implements Thing{

	//Npc������Npc�ĸ���   �о��������鷳 ÿ��Npc��Ҫһ���ࣿ��������һ����ÿ������һ����
	//Npc������ ͷ�� ���о��� ������װ����  ����ֻ�������ԣ�
	//��װ������ ֻҪ��ʾ�����ӵ������Ӧ���У���װ��Ҫ�����Ŀǰ�����е�װ��
	//���� ֻ��������  �����ǹ̶���
	
	//���о�ֻʣ���� ͷ��  �Ƿ��о��� ���Ƿ����̵�  �Ƿ���������������ڲ��ࣩ
	//�����ø���������      �̵��ֻҪ������ֵ����
	protected transient BufferedImage head;
	protected String name;
	protected Monster m;
	//�Ƿ��Ǿ���
	protected boolean isStory;
	//�Ƿ����̵�
	protected boolean isStore;
	//�Ƿ�������
	protected boolean isAdventure;
	//�Ƿ�����
	protected boolean isDoor;
	//����Ի�����
	protected StoryPoint[] storylist;
	//����ֵ�б�   ҲҪ�����ݽṹ����   ���ȴ���ֵ  Ȼ���۸�
	protected NumPoint[] numList;
	//��Door�������Ϣ  ��int�͹���
	protected int[] num2List;
	//����ʵ��Monsterԭ��һ��  Ҳ����ֻ�ù��캯��  ��������
	public Npc() {}
	public Npc(String name, boolean isStory, boolean isStore, boolean isAdventure
			,boolean isDoor, StoryPoint[] storylist
			, NumPoint[] numList, int[] num2List
			, BufferedImage head)
	{
		this.name = name;
		this.isStory = isStory;
		this.isStore = isStory;
		this.isAdventure = isAdventure;
		this.storylist = storylist;
		this.numList = numList;
		this.num2List = num2List;
		this.isDoor = isDoor;
		this.head = head;
	}
	public BufferedImage getHead() {
		return head;
	}
	public void setHead(BufferedImage head) {
		this.head = head;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isStory() {
		return isStory;
	}
	public void setStory(boolean isStory) {
		this.isStory = isStory;
	}
	public boolean isStore() {
		return isStore;
	}
	public void setStore(boolean isStore) {
		this.isStore = isStore;
	}
	public boolean isAdventure() {
		return isAdventure;
	}
	public void setAdventure(boolean isAdventure) {
		this.isAdventure = isAdventure;
	}
	public StoryPoint[] getStorylist() {
		return storylist;
	}
	public void setStorylist(StoryPoint[] storylist) {
		this.storylist = storylist;
	}
	
	//���÷�����ʼ��Point
	protected static StoryPoint setStoryPoint(boolean who,String content)
	{
		return new StoryPoint(who,content);
	}
	protected NumPoint setNumPoint(int num, int money)
	{
		return new NumPoint(num, money);
	}
	public NumPoint[] getNumList() {
		return numList;
	}
	public void setNumList(NumPoint[] numList) {
		this.numList = numList;
	}
	@Override
	public void doJob(Hero man, int i, int j) {
		// TODO �Զ����ɵķ������
		//�Ȱ�storyBounds���
		//npc Ĭ��˵�λ�����ʧ 
		//����������  ��д�÷���!
				Game.position.getF().things[i][j] = new Floor();
				Game.position.getF().flag[i][j] = 1;
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
	@Override
	public BufferedImage getImage() {
		// TODO �Զ����ɵķ������
		return head;
	}
	public boolean isDoor() {
		return isDoor;
	}
	public void setDoor(boolean isDoor) {
		this.isDoor = isDoor;
	}
	public Monster getM() {
		return m;
	}
	public void setM(Monster m) {
		this.m = m;
	}
	public int[] getNum2List() {
		return num2List;
	}
	public void setNum2List(int[] num2List) {
		this.num2List = num2List;
	}
}
