package test;
//����Ҫ�û�����    ����˵�Ĺ���

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

public class JComboBoxTest {

	public void init()
	{
		String[] heads = new String[]
				{
						"1", "2", "3", "4", "5", "6"
				};
		JComboBox headBox = new JComboBox(heads);
		//ʹ��ListCellRenderer��װ�δ��б�
		headBox.setRenderer(new ImageCellRenderer());
		JFrame f = new JFrame();
		f.add(headBox);
		f.pack();
		f.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new JComboBoxTest().init();
	}
	class ImageCellRenderer extends JPanel
	implements ListCellRenderer
	{
		private ImageIcon icon;
		private String name;
		//������Ƶ�Ԫ��ʱ�ı���ɫ
		private Color background;
		//ǰ��ɫ
		private Color foreground;
		/*
		 * list - ���ڻ��Ƶ� JList��
		value - �� list.getModel().getElementAt(index) ���ص�ֵ��  Model��javaʵ������ ����ַ���
		Model�Ǵ����ݵ�
		index - ��Ԫ��������
		isSelected - ���ѡ����ָ���ĵ�Ԫ����Ϊ true��
		cellHasFocus - ���ָ���ĵ�Ԫ��ӵ�н��㣬��Ϊ true��
		*/
		public Component getListCellRendererComponent(JList list, Object value
				, int index, boolean isSelected, boolean cellHasFocus)
		{
			icon = new ImageIcon("icon/" + value + ".gif");
			name = value.toString();
			background = isSelected ? list.getSelectionBackground() 
					:list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
					:list.getForeground();
			//���ظ�JPanel������Ϊ�б��������
			return this;
		}
		//��дpaintComponent()����, �ı�JPanel�����
		public void paintComponent(Graphics g)
		{
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			g.setColor(background);
			//û��Ϊ���Component����  ��ô�����ģ���//����������
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(foreground);
			//����ͼ��
			g.drawImage(icon.getImage(), getWidth()/2-imageWidth/2, 10, null );
			g.drawString(name, getWidth()/2-name.length()*10, imageHeight+30);
		}
		//
		public Dimension getPreferredSize()
		{
			return new Dimension(60,80);
		}
		
	}

}
