package test;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;



public class Text {

	public void init()
	{
		String[] heads = new String[]
				{
						"1", "2", "3", "4", "5", "6"
				};
		JComboBox headBox = new JComboBox(heads);
		//��JComboBox��װ����   �̳�JPanel,�ӿ�ListCellRenderer
		headBox.setRenderer(new ImageCellRenderer());
		JFrame f = new JFrame();
		f.add(headBox);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Text().init();
	}
	//ImageCellRenderer   JComboBox��װ����  JListҲ������װ��
	class ImageCellRenderer extends JPanel implements ListCellRenderer
	{
		//JComboBox  GUI��Ա  ��Ҫ��getListCellRendererComponent����Ĳ���  �õ�ÿһ�������
		//������Щ����  �����ǻ��Ƴ���
		private ImageIcon icon;
		private String name;
		private Color background;
		private Color foreground;
		//������ListCellRenderer�ӿ�����ķ���
		//����ÿһ���б���  ���Ѿ�ת���ɶ�Ӧ��Component�ˣ�
		public Component getListCellRendererComponent(JList list
				, Object value, int index, boolean isSelected, boolean cellHasFocus )
		{
			//ͨ���б�������  �����������ı�����ֵ  Ϊ����������ǻ�����
			icon = new ImageIcon("icon/" +value + ".gif");
			//����ʵ��  Ŀǰ����Ҫ��name  ��Ϊ��ѡ��ͷ��
			name = value.toString();
			//����ı�����ѡ������JList  JComboBox����Ľӿ�ʵ�ֵ�  �Ժ�һ��Ҫ�о����ǵ�Դ����
			//�о���ƵĺÌ�   ����Ҳ����һ���� ˵����õ��ǹ���˼ά ���ǿ�ѧ˼ά  ����˼ά����Դ���� ֻҪ��һ�����ʽ�
			//����ѧ˼ά�����Ž�      Ҳ�����㷨���ݽṹ�������������㷨��ṹ  
			
			//�������list�������ص�Color��ʵ�����Լ�����
			background = isSelected ? list.getSelectionBackground()
					: list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
					: list.getForeground();
			//���ظ�JPanel������Ϊ�б������  Ҳ�����Լ�
			return this;
		}//close getComponent
		
		//��дJPanel�����paintComponent()����,�ı�JPanel�����   ������������ñ���  
		//���JLayer�в㼶��ϵ     JPanel������   ����
		public void paintComponent(Graphics g)
		{
			//��ȡͼƬ��С  ����֮�󲼾�    null ָ����  ImageObserver observer
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			//������
			//���軭����ɫ    ����Ϊʲô��� �� Ҫ�輸����Ա������
			g.setColor(background);
			//�����getWidth() , getHeight()�Ƿ��ظ�Component������
			//֮���getDimention���������˸�Component�Ĵ�С
			g.fillRect(0, 0, getWidth(), getHeight());
			//��ǰ��
			g.setColor(foreground);
			//����ͼ��        null ָ����  ImageObserver observer
			g.drawImage(icon.getImage(), getWidth()/2 - imageWidth/2, 10, null);
			g.drawString(name, getWidth()/2-name.length()*10, imageHeight+30);
		}
		public Dimension getPreferredSize()
		{
			return new Dimension(60,80);
		}
	}
}
