package jingame;

public class NumPoint implements java.io.Serializable{

	//����ֵ  ��Ϊ�涨�ð�����ֵ ������ ������ ��������  ����ֻ��Ҫ��˳������
	private int num;
	//���ĵĽ��
	private int money;
	//������������
	public NumPoint(int num, int money)
	{
		this.num = num;
		this.money = money;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
}
