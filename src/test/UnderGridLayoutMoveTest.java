//2017.12.3
//����ͼƬ��GridLayout�����ܷ��ƶ�    �о�����   ���ǻ���   ģ��������
package test;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UnderGridLayoutMoveTest{

	//GUI
	JFrame f = new JFrame("�����ƶ�");
	JPanel p = new JPanel();
	//��ť
	JButton up = new JButton("up");
	JButton down = new JButton("down");
	JButton left = new JButton("left");
	JButton right = new JButton("right");
	//GridLayout
	GridLayout layout = new GridLayout(5,5,50,50);
	//����ֵ
	int x;
	int y;
	
	
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������

	}

}
