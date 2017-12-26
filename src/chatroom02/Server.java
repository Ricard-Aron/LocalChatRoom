package chatroom02;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class Server extends JFrame{

	//table������
	String[] first = {"�û���", "����", "ͼ��", "Socket", "����"};
	//ά���������  //��չ��DefalutTabelModel
	ExtendedTableModel model = new ExtendedTableModel(first, null);
	//���
	JTable table = new JTable(model);
	//TextArea  ���û���Ϣ�������
	JTextArea area = new JTextArea(20, 50); 
	// ����һ�����ڸ�ʽ�����ڵĸ�ʽ��
			private DateFormat formatter = DateFormat.getDateTimeInstance();
	public Server(String title)
	{
		super(title);
		//area���ɱ༭
		area.setEditable(false);
		table.setRowSelectionAllowed(false);
		table.setRowHeight(40);
		//����table���п�
		table.getColumn(first[3]).setPreferredWidth(400);
		//��table�Ӹ�������   ���ӵ���ǰFrame��
		add(new JScrollPane(table));
		//��area������  ���ӹ�����
		add(new JScrollPane(area), BorderLayout.SOUTH);
		//�ø�JFrame�ܱ��ر�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//����Frame��С
		setPreferredSize(new Dimension(600, 800));
		//�����Ͻ�ͼ��
		ImageIcon icon=new ImageIcon("icon/icon.png");
        this.setIconImage(icon.getImage()); 
		pack();
		setVisible(true);
	}
	//�ø�ArrayList���û�
	ArrayList<UserInfo> users = new ArrayList<>();
	
	public void init()
	{
		try 
			(ServerSocket ss = new ServerSocket(33233))
		{	while(true)
			{
				//���д�������  һֱ�ȱ�������
				Socket socket = ss.accept();
				new ServerThread(socket).start();
			}
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
		new Server("server").init();
	}

	private class ServerThread extends Thread
	{
		Socket socket;
		ObjectOutputStream sendList;
		BufferedReader bb;
		public ServerThread(Socket socket)
		{
			this.socket = socket;
		}
		//��֪��֮ǰΪʲô����ͻ���û����   ��Ϊ��ûд��run����
		public void run()
		{
			try {
				bb = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				//ÿ���ͻ��� �����Լ���sendList
				sendList = new ObjectOutputStream(socket.getOutputStream());
				String line = null;
				System.out.println("server" +socket);
				//��ʵ֤�� ���while�Ƕ�����    Ϊʲô��һ�����while ��ֻ��ͬʱ����һ���ͻ���?
				try
				{	while((line = bb.readLine())!=null)
					{
					//ÿ�ζ����ж���ʲô��Ϣ
					//�����������Ϣ
					if(line.startsWith(Protocol.ONTIME) && 
							line.endsWith(Protocol.ONTIME))
					{
						//��Ϣ�ṹ
						System.out.println("�������յ����ߣ�" + line);
						/*Protocol.ONTIME +userName.getText() 
						+Protocol.SPLITTER
						+ String.valueOf(password.getPassword()) + Protocol.SPLITTER 
						+headBox.getSelectedItem() + Protocol.ONTIME;*/
						//������   �Ѳ���һ  ��������λ�õ��ַ��������
						String content = line.substring(Protocol.LENGTH,
								line.length()-Protocol.LENGTH);
						//�ٲ�    ���ղ���  ��ɼ����ַ���
						String[] info = content.split(Protocol.SPLITTER);
						//��һ���¶���   ��ʼlostΪ0
						//System.out.println("icon:  "+info[1]);
						UserInfo user = new UserInfo(info[0], info[1], info[2], socket
								, 0, sendList);
						//ת����Object[]  ����model
 						Object[] o = {user.getName(), user.getPassword()
									, new ImageIcon("icon/" + user.getIcon() + ".gif")
									, user.getSocket(), true};
						//���ж�users�Ƿ���ֵ��Ҳ�����Ƿ��Ѿ�ע�ᣩ
						if(users.isEmpty())
						{
							user.setLost(0);
							users.add(user);
							model.addRow(o);
							//������Ϣ��
							area.setText(formatter.format(new Date())+"---"
									+(user.getName()) 
									+"  ����"
									+"\n" +area.getText());
						}
						//�����׼Ӳ���
						boolean isAdd = true;
						//��ÿ���û�������һ��
						for(UserInfo u : users)
						{
							//���
							/*System.out.println(u.getSocket().toString());
							System.out.println(user.getSocket().toString());
							System.out.println(u.getSocket().toString()
									.equals(user.getSocket().toString()));*/
							if(u.equals(user))
							{
								u.setLost(0);
								//System.out.println("lost1:  "+u.getLost());
								isAdd = false;
							}
							//�����ǰlost>2  ȥ�����û�
							if(u.getLost()>2)
							{
								//�б��Ƴ�
								users.remove(u);
								//���ݿ� ���û�������ϢΪfalse
								model.setFalse(u.getName());
							}
							//��ǰlost+1
							u.setLost(u.getLost()+1);
							//����  ���Լ�û����users
							System.out.println(u.getName() +u.getSocket() +u.getLost() 
							+ "  "
							+ u.getSocket()
							/*.toString().equals(u.getSocket()
									.toString())*/
							);
						}
						//���û�а��� �������
						if(isAdd)
						{
							users.add(user);
							model.addRow(o);
							area.setText(formatter.format(new Date())+"---"
									+(user.getName()) 
									+"  ����"
									+"\n" +area.getText());
						}
						System.out.println("������" +model.getRowCount());
						System.out.println("������" +model.getColumnCount());
						//����������Ϣ�������û�
						//����������Ϣ�ͷ�list����ǰ�ͻ���
					//UserInfo[] u = new UserInfo[users.size()];
					//users.toArray(u);
					//Ϊʲôÿ�ζ��½�һ��Arraylist��  ��ΪwriteObject�Ļ���  ֻ����һ�ζ���ֵ  ����
						//ÿ�δ���ȥ�Ķ���  ֵ�����   ����ÿ�δ���һ���µ�
							sendList.writeObject(new ArrayList<UserInfo>(users));
							System.out.println(user.getName()+"���յ�: "+users.size());
				}//close ontime
					//�����������Ϣ
					else if(line.startsWith(Protocol.MASSAGE) &&
							line.endsWith(Protocol.MASSAGE))
					{
						//����Ϣ
						System.out.println("�������յ����ݣ�" + line);
						String content = line.substring(Protocol.LENGTH
								, line.length()-Protocol.LENGTH);
						System.out.println("�������յ����ݣ�" + content);
						//�ٲ�
						String[] msg = content.split(Protocol.SPLITTER);
						//  ��Ҫ����˭  Ҳ����msg[0] �������� ����Ϣ�Ķ���
						sendMsg(msg[0], msg[1],searchUserBySocket(socket));
						area.setText(formatter.format(new Date())+"---"
								+(searchUserBySocket(socket).getName()) 
								+" �� " +msg[0] 
								+" ˵: "+msg[1]
								+"\n" +area.getText());
						//���ø�user�� socket��
					}
			 }}catch(IOException ex)
				{
					//�������ʧ�ܵĻ�
				 //���·�����  ��Ϣ��
				 area.setText(formatter.format(new Date())+"---"
							+(searchUserBySocket(socket).getName()) 
							+"  �뿪"
							+"\n" +area.getText());
				 //���ݿ� ���û�������ϢΪfalse
				 model.setFalse(searchUserBySocket(socket).getName());
				    //�Ƴ���ǰ����
				 users.remove(searchUserBySocket(socket));
				  
					
				    System.out.println("��ǰusers.size: " +users.size());
				    //�ر���Դ
				    try{
				    	if(bb != null)
				    {
				    	bb.close();
				    }
				    if(sendList != null)
				    {
				    	sendList.close();
				    }
				    if(socket != null)
				    {
				    	socket.close();
				    }
				    }catch(IOException e)
				    {
					   e.printStackTrace();
				    }
				}//close while try
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
		}//close run
		//ͨ��socket�ҵ���ǰUserInfo����
		private UserInfo searchUserBySocket(Socket socket)
		{
			//����users
			for(UserInfo user : users)
			{
				if(user.getSocket().equals(socket))
				{
					return user;
				}
			}
			return null;
		}
		//ͨ��name�ҵ���ǰUserInfo����
		private UserInfo searchUserByName(String name)
		{
			//����users
			for(UserInfo user : users)
			{
				System.out.println((user.getName() + " = " +name)
						+ (user.getName().equals(name)));
				if(user.getName().equals(name))
				{
					return user;
				}
			}
			return null;
		}
		private void sendMsg(String name, String content, UserInfo u)
		{
			//user �Ǳ���   u �Ƿ�    Ҳ����name  �Ǳ����� ��
			//���ø÷��� ���ض�ӦUserInfo
			UserInfo user;
			//���user����
				try 
				{
					//�����name ��������   �ͷ���ÿһ���û�
					if(name.equals("������"))
					{
						for(UserInfo uu : users)
						{
							String msg = Protocol.MASSAGE 
									+ Protocol.ALL + name + Protocol.ALLSPLIT
									+u.getName()
									+ Protocol.ALL
									+Protocol.SPLITTER 
									+ content +Protocol.MASSAGE;
							//��������socket����
							
							System.out.println(uu.getName() + " ����: " +msg);
							uu.getOp().writeObject(msg);
						}
					}
					if(( user = searchUserByName(name) )!= null)
					{	//��װ��Ϣ
					
				String msg = Protocol.MASSAGE + u.getName() +Protocol.SPLITTER 
					+ content +Protocol.MASSAGE;
				
				//��������socket����
			
				System.out.println(u.getName() + " ����: " +msg);
				user.getOp().writeObject(msg);
				}}
			    catch (IOException e) {
				// 
			    	//�������ʧ�ܵĻ�
					//�Ƴ���ǰ����
			    	user = searchUserByName(name);
				    users.remove(user);
				    System.out.println("��ǰusers.size: " +users.size());
				    //�ر���Դ
				    try{
				    	if(user.getOp()!= null)
				    {
				    		user.getOp().close();
				    }
				    if(user.getSocket() != null)
				    {
				    	user.getSocket().close();
				    }
				    }catch(IOException ex)
				    {
					   ex.printStackTrace();
			    	//���͸�u  user�����ߵ���ʵ
					String msg = Protocol.MASSAGE + user.getName() +Protocol.SPLITTER 
							+ "���Ѿ����ߣ���ر������"+content +Protocol.MASSAGE;
					try {
						u.getOp().writeObject(msg);
					} catch (IOException exx) {
						// TODO �Զ����ɵ� catch ��
						exx.printStackTrace();
					}
				}
			}
		}
	}//close serverThread
	class ExtendedTableModel extends DefaultTableModel
	{
		//���¹���һ��������  ί�и�����Defalut��
		public ExtendedTableModel(String[] columnNames, Object[][] cells)
		{
			super(cells, columnNames );
		}
		//��дgetColumnClass����  ����ÿ�е�һ��ֵ�����ظ�������
		public Class getColumnClass(int c)
		{
			return getValueAt(0, c).getClass();
		}
		//дһ������ �Ѹö���������Ϣ�ĳ�false
		public void setFalse(String name)
		{
			//����ÿ���еĵ�һ��   ���equals  ����������
			for(int i=0;i<getRowCount();i++)
			{
				System.out.println(" asd" +getValueAt(i,0).equals(name));
				if(getValueAt(i,0).equals(name))
				{
					setValueAt(false, i, 4 );
				}
			}
		}
	}
}
