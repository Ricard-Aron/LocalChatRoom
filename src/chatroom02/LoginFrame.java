//2017.11.25
//��¼����   ����ͨ��������    �о��ֵֹ�  �����ϳ���
package chatroom02;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.IOException;

import javax.swing.*;
public class LoginFrame extends JFrame{
	//���ȶ���GUI����
	//��ʾ��ͼ��JLabel
	private JLabel top ;
	//ѡ��ͷ���JComboBox
	private JComboBox headBox;
	//ͷ��ͼ������    ��ΪJComboBox���촫ͼ������͹�����ͼ��������
	private String[] heads;
	//JList��װ����    ����Ū����DefaultListModel����JList�б���ɱ�  ������Ԫ�ؿɱ�ļ����ࣩ
	//װ������ʵֻ����ListCellRenderer
	//�û�����
	private JTextField userName;
	//�����
	private JPasswordField password;
	//��¼��ť
	private JButton login;
	//����Ҫ��client������Ϣ
	public static Client client;
	//���첢ʹ����ʼ
	public LoginFrame(String title)
	{
		super(title);
		//client = parent;
		//��ʼtopʹ���Ǹ���ͼ  �����ô�С
		top = new JLabel(new ImageIcon("icon/chatroomtitle.png"));
		top.setPreferredSize(new Dimension(430,130));
		//��ʼJComboBox
		heads = new String[]
				{
						"1", "2", "3", "4", "5", "6"
				};
		headBox = new JComboBox(heads);
		//��JComboBox��װ����   �̳�JPanel,�ӿ�ListCellRenderer
		headBox.setRenderer(new ImageCellRenderer());
		//��JComboBox�Ӹ���Ӧ��  ����ѡ���ĸ�ѡ�� Ӧ�ò���  ֱ����getSelectedItem
		
		//�û�����   ����  Ĭ��ֵ   ������������ַ�
		userName = new JTextField("�û���", 12);
		//Ĭ����ʾ******
		password = new JPasswordField("123456", 12);
		//passwordĬ�ϻ����ַ�Ӧ����*
		
		login = new JButton("��¼");
		//��login����Ӧ��  
		login.addActionListener(e ->{
			//�����Ӧ��Ҫ��������  һ�� ��ע����Ϣ����������   ��ÿ��һ��ͷ�һ��
			//���� ��ʼ���ͻ��ˣ��Ҹо���������    �ÿͻ��˵�LoginFrame�ȽϺã� ֻ�ǿͻ���Ĭ�ϲ���ʾ
			//������������ʾ��Ҳ������ʾclient  ����ʾthis��  �����һ��Ǵ��ڵ�   Ȼ��chatFrame���client
			//���ڲ���  Ȼ��ֱ�ӵ���client����ķ�������  ��������Ӧ�ö������
			client = new Client(userName.getText());
			try
			{	client.init();
				//���ȷ���Ϣ
				//��Ҫ���װ�� ��Ϣ   ע��password�Ļ�ȡ����getPassword  ������getText��  ����ȫ����
				String content = Protocol.ONTIME +userName.getText() 
				+Protocol.SPLITTER + String.valueOf(password.getPassword()) + Protocol.SPLITTER 
				+headBox.getSelectedItem() + Protocol.ONTIME;
				client.send(content);
				System.out.println(content);
				//  ����Timer ÿ20�뷢һ��
				javax.swing.Timer timer = new javax.swing.Timer(1000*5 ,event ->
				{
					client.send(content);	
				});
				timer.start();
			}catch(IOException ex)
			{
				JOptionPane.showMessageDialog
        		(null, "��ȷ�Ϸ���������,����ϵ����Ա"
        				,"ERROR",JOptionPane.WARNING_MESSAGE);
				//��ΪJOption��ģʽ��   �����Ҳ���ȷ�����Ͳ������½��� 
				//������һ��JOptionPane�������
				System.exit(1);
			}
			//��ʾclient  Ȼ��this����ʾ
			client.setVisible(true);
			setVisible(false);
		});
		add(top, BorderLayout.NORTH);
		add(headBox, BorderLayout.WEST);
		
		//���û�������������  ����һ��������Ӧ
		//���� ���涼��Ĭ��ֵ   ����ȡ����  Ĭ��ֵ���
		userName.addFocusListener(new FocusAdapter()
				{
			
					public void focusGained(FocusEvent e)
					{
						userName.setText("");
					}
				});
		password.addFocusListener(new FocusAdapter()
				{
					public void focusGained(FocusEvent e)
					{
						password.setText("");
					}
				});
		//װ��userName password
		JPanel info = new JPanel();
		//����ΪBoxʽ����
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.add(userName);
		info.add(password);
		add(info, BorderLayout.CENTER);
		add(login, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�ô����м���ʾ��
		setLocationRelativeTo(null);
		//�����Ͻ�ͼ��
		ImageIcon icon=new ImageIcon("icon/icon.png");
        this.setIconImage(icon.getImage()); 
		pack();
		setVisible(true);
	}
	//ImageCellRenderer   JComboBox��װ����  JListҲ������װ��
	//�ղų��˸����   ��д�ĺ�����д����  �������Ϊ�����Լ������������ʾ������   ���˴��������
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
			//���û���
			//g.drawString(name, getWidth()/2-name.length()*10, imageHeight+30);
		}
		public Dimension getPreferredSize()
		{
			return new Dimension(60,80);
		}
	}
	/*//����Ҫ����̸߳�client
	class initClient extends Thread
	{
		public void run()
		{
			client = new Client();
			client.init();
		}
	}*/
	public static void main(String[] args)
	{
		new LoginFrame("ss");
	}
}
