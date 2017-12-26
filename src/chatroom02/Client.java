package chatroom02;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
//ClientҪ����ô������
//�������ӷ�����
//�շ���������Ϣ  �ж����ĸ��û�������   ��ʾ�����¸��û���chatFrame������chatFrameҪ�и�������Ϣ�Ŀ�ݷ�����
//�շ��������û���Ϣ  ������JList
//����Ϣ  ��chatFrame����  ��loginFrame���� 
//���ڻ�����������Ҫ�����߳�
public class Client extends JFrame{

	//�Լ����Լ��ı�����
	private static Client client;
	//��������Ϣ
	private final String IP = "127.0.0.1";
	private final int PORT = 33233;
	
	//GUI��Ϣ  ûʲô���뷨  ��һ��JList����
	//��JList���ݵ�
	private DefaultListModel<UserInfo> listModel = new DefaultListModel<>();
	private JList<UserInfo> list = new JList(listModel);
	//�����������շ�����������UserInfo  
	private ArrayList<UserInfo> users;
	//���������û�
	private UserInfo all = new UserInfo("������" ,"", "all"
			, null , -2000, null);
	//�ͻ��˵�Socket
	private Socket socket;
	//�ͻ��˵������
	private BufferedWriter out;
	//����printStream
	private PrintStream ps;
	//�ͻ��˵�������
	private BufferedReader in;
	//������ ���ն���
	private ObjectInputStream inObject;
	//��ǰ�ͻ����Լ����û���
	public static String NAME;
	//��ʼ��GUI
	public Client(String name)
	{
		super("����������");
		// ���ø�JListʹ��ImageCellRenderer��Ϊ��Ԫ�������
		list.setCellRenderer(new ImageCellRenderer());
		this.NAME = name;
		listModel.addElement(all);
		list.addMouseListener(new ChangeMusicListener());
		add(new JScrollPane(list));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(2, 2, 200 , 600);
		//�����Ͻ�ͼ��
				ImageIcon icon=new ImageIcon("icon/icon.png");
		        this.setIconImage(icon.getImage()); 
		//�ô����м���ʾ��
				setLocationRelativeTo(null);
	}
	// ------�����ĸ������Ƕ�ListModel�İ�װ------
			// ���û��б�������û�
			public void addUser(UserInfo user)
			{
				listModel.addElement(user);
			}
			// ���û��б���ɾ���û�
			public void removeUser(int pos)
			{
				listModel.removeElementAt(pos);
			}
			// ��ȡ�����촰�ڵ��û�����
			public int getUserNum()
			{
				return listModel.size();
			}
			// ��ȡָ��λ�õ��û�
			public UserInfo getUser(int pos)
			{
				return listModel.elementAt(pos);
			}
	//��ʼ������
	public void init() throws IOException
	{
		//���ȳ�ʼ������  ���ӵ�������
			//���ӵ�������   String int
			socket = new Socket(IP, PORT);
			//��ȡ��socket��Ӧ����������� ����װ���ڵ���
			//out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//Ϊʲôд�� PrintStream���ܷ���ȥ  BufferedWriter�����أ�
			ps = new PrintStream(socket.getOutputStream());
			//in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			inObject = new ObjectInputStream(socket.getInputStream());
			new ReadObject().start();
			//new ReadMsg().start();
			System.out.println("���ӷ�����ʧ��");
		
		//Ȼ�����send()  Ҳ������������д
		//Ȼ������������Ϣ�߳�  ����ʱ����GUI
	}
	/*public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		client = new Client();
		client.init();
		
	}*/
	//�ͻ���  д����
	public void send(String content)
	{
		/*//����out
		try {
			out.write(content);
			out.flush();
			//System.out.println("123");
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}*/
		//֤���������out.write����������
		ps.println(content);
		
	}
	//�½�һ���߳� ��������������
	class ReadObject extends Thread
	{
		Object o;
		public void run()
		{
			try {
				
				//ArrayList<UserInfo> temp = new ArrayList<>();
				while((o =inObject.readObject())!=null)
				{//if �� arraylist
					if(o.getClass() == ArrayList.class)
					{	
						System.out.println("�ն���");
						users =(ArrayList<UserInfo>)o;
						//�յ��б�֮��Ĳ���  ��ʵ�Ǹ���DefualtList
						System.out.println("�յ��б�!" + "   size :" +users.size());
						/*for(UserInfo u : users)
						{
						System.out.println(u.getName() + "   " +u.getIcon());
						}*/
						//�ж�users�����Ƿ��С������ˡ�
						if(!users.contains(all))
							users.add(all);
					//����users �ж�listModel�����Ƿ�����˶��� ���û�� ���
					for(UserInfo u : users)
					{
						/*System.out.println("�û���:  " +getUserNum());
						boolean isAdd = true;
						if(listModel.isEmpty())
							{addUser(u);
							 isAdd = false;
							 System.out.println("�û���:  " +getUserNum());
							}
						else
							for(int i=1 ;i<getUserNum(); i++)
							{
								if(getUser(i).equals(u))
									isAdd = false;
							}
						if(isAdd)
							addUser(u);*/
						//System.out.println(u.getName() + "   " +u.getIcon());
						if(!listModel.contains(u))
							addUser(u);
					}
					//�ٱ���listModel  ��listModel�����Ƿ���usersû�е�  Ҳ�����Ѿ����ߵ�
					for(int i=0 ;i<getUserNum(); i++)
					{
						//�����������ɾ��
						if(!users.contains(getUser(i))&&getUser(i).getName()!="������")
						{
							removeUser(i);
						}
					}
				}//If ��String
					else if(o.getClass() == String.class)
					{
						//������
						String msg = (String)o;
						if(msg.startsWith(Protocol.MASSAGE) &&
								msg.endsWith(Protocol.MASSAGE))
						{
							//����Ϣ
							System.out.println("�յ����ݣ�" + msg);
							String temp = msg.substring(Protocol.LENGTH
									, msg.length()-Protocol.LENGTH);
							//�ٲ�
							String[] content = temp.split(Protocol.SPLITTER);
							System.out.println(content.length);
							System.out.println(content[0]);
							System.out.println(content[1]);
							//�汻����Ϣ�Ķ�����Ϣ   �����������
							UserInfo user;
							//�������������Ϣ
							if(content[0].startsWith(Protocol.ALL) 
									&& content[0].endsWith(Protocol.ALL))
							{
								//ȥ����ǩ
								String q = content[0].substring(Protocol.LENGTH
										, content[0].length()-Protocol.LENGTH);
								System.out.println(q);
								String[] w = q.split(Protocol.ALLSPLIT);
								System.out.println(w.length);
								System.out.println(w[0]);
								System.out.println(w[1]);
								//content[0]���Ǵ淢��Ϣ�˵�name
								content[0] = w[1];
								//user Ϊ ������
								user = searchByName(w[0]);
							}
							//�ҵ���user��chatFrame  ��ʾ��  ������������Ϣ��
							else
								 user = searchByName(content[0]);
							//�����  ��ʾ������  
							if(user.getChatFrame()!=null)
							{	// ������û��Ĵ���û����ʾ�����ø��û��Ĵ�����ʾ����
								if (!user.getChatFrame().isShowing())
								{
									
									user.getChatFrame().setVisible(true);
								}
								//������Լ�����Ⱥ����Ϣ  �����ٸ��¶Ի���
								if(!content[0].equals(NAME))
									{user.getChatFrame()
									.refreshText(content[0], content[1]);
									}
							}
							//���û�� ��� ������
							else 
							{
								user.setChatFrame(new ChatFrame(null, user));
								//������Լ�����Ⱥ����Ϣ  �����ٸ��¶Ի���
								if(!content[0].equals(NAME))
									{user.getChatFrame()
									.refreshText(content[0], content[1]);
									}
								user.getChatFrame().setVisible(true);
							}
						}
					}
				}
				
			} catch (ClassNotFoundException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				//�������  ������IO
			}
			
		}
	}// close ReadObject
	//����ͨ���ݵ��߳�
	/*class ReadMsg extends Thread
	{
		String msg = "";
		public void run()
		{
			try
			{
				System.out.println("�ջ���");
				while((msg = in.readLine()) != null)
				{
					//������
					if(msg.startsWith(Protocol.MASSAGE) &&
							msg.endsWith(Protocol.MASSAGE))
					{
						//����Ϣ
						String temp = msg.substring(Protocol.LENGTH
								, msg.length()-Protocol.LENGTH);
						//�ٲ�
						String[] content = temp.split(Protocol.SPLITTER);
						//�ҵ���user��chatFrame  ��ʾ��  ������������Ϣ��
						UserInfo user = searchByName(content[0]);
						//�����  ��ʾ������  
						if(user.getChatFrame()!=null)
						{
							user.getChatFrame().refreshText(content[1]);
							user.getChatFrame().setVisible(true);
						}
						//���û�� ��� ������
						else 
						{
							user.setChatFrame(new ChatFrame(null, user));
							user.getChatFrame().refreshText(content[1]);
						}
					}
				}
			}catch(IOException ex)
			{
				//�������  �����ж���
			}
		}
	}*/
	
	// �������ڸı�JList�б�����۵���
	class ImageCellRenderer extends JPanel
		implements ListCellRenderer<UserInfo>
	{
		private ImageIcon icon;
		private String name;
		// ������Ƶ�Ԫ��ʱ�ı���ɫ
		private Color background;
		// ������Ƶ�Ԫ��ʱ��ǰ��ɫ
		private Color foreground;
		@Override
		public Component getListCellRendererComponent(JList list
			, UserInfo userInfo , int index
			, boolean isSelected , boolean cellHasFocus)
		{
			// ����ͼ��
			icon = new ImageIcon("icon/" + userInfo.getIcon() + ".gif");
			name = userInfo.getName();
			// ���ñ���ɫ��ǰ��ɫ
			background = isSelected ? list.getSelectionBackground()
				: list.getBackground();
			foreground = isSelected ? list.getSelectionForeground()
				: list.getForeground();
			// ���ظ�JPanel������Ϊ��Ԫ�������
			return this;
		}
		// ��дpaintComponent�������ı�JPanel�����
		public void paintComponent(Graphics g)
		{
			int imageWidth = icon.getImage().getWidth(null);
			int imageHeight = icon.getImage().getHeight(null);
			g.setColor(background);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(foreground);
			// ���ƺ���ͼ��
			g.drawImage(icon.getImage() , getWidth() / 2 - imageWidth / 2
				, 10 , null);
			g.setFont(new Font("SansSerif" , Font.BOLD , 18));
			// ���ƺ����û���
			g.drawString(name, getWidth() / 2 - name.length() * 10
				, imageHeight + 30 );
		}
		// ͨ���÷��������ø�ImageCellRenderer����Ѵ�С
		public Dimension getPreferredSize()
		{
			return new Dimension(60, 80);
		}
	}
	
	// ʵ��JList�ϵ����˫���¼��ļ�����
		class ChangeMusicListener extends MouseAdapter
		{
			public void mouseClicked(MouseEvent e)
			{
				// ������Ļ�����������2
				if (e.getClickCount() >= 2)
				{
					// ȡ�����˫��ʱѡ�е��б���
					UserInfo user = (UserInfo)list.getSelectedValue();
					// ������б����Ӧ�û��Ľ�̸����Ϊnull        //Ĭ�Ͼ���null
					if (user.getChatFrame() == null)
					{
						// Ϊ���û�����һ����̸���ڣ����ø��û����øô���
						user.setChatFrame(new ChatFrame(null , user));
					}
					// ������û��Ĵ���û����ʾ�����ø��û��Ĵ�����ʾ����
					if (!user.getChatFrame().isShowing())
					{
						user.getChatFrame().setVisible(true);
					}
				}
			}
		}
		
		//ͨ��name  �ҵ�ǰuser
		private UserInfo searchByName(String name)
		{
			//ѭ��users ��ƥ��
			for(UserInfo user : users)
			{
				if(user.getName().equals(name))
				{
					return user;
				}
			}
			return null;
		}
}
