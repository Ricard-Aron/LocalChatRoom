package jingame;

import java.awt.image.BufferedImage;

public abstract class Creature implements java.io.Serializable{
	//ͷ��
	
	protected transient BufferedImage head;
	protected int hp;
	protected int attack;
	protected int defence;
	protected String name;
	//���Ǯ�;���
		protected int money;
		protected int ex;
	//��Ѫ
	protected int get_hp;
	//͵Ǯ
	protected int get_money;
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
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	public int getAttack() {
		return attack;
	}
	public void setAttack(int attack) {
		this.attack = attack;
	}
	public int getDefence() {
		return defence;
	}
	public void setDefence(int defence) {
		this.defence = defence;
	}
	public int getGet_hp() {
		return get_hp;
	}
	public void setGet_hp(int get_hp) {
		this.get_hp = get_hp;
	}
	public int getGet_money() {
		return get_money;
	}
	public void setGet_money(int get_money) {
		this.get_money = get_money;
	}
	
}
