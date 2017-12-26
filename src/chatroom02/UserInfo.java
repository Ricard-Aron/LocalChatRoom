package chatroom02;

import java.io.ObjectOutputStream;
import java.net.*;
import java.nio.channels.SocketChannel;
public class UserInfo implements java.io.Serializable
{

	//���û���ͼ��  ������ôд  ֮��JList��ʱ���Ҫ��Ӧ
	private String icon;
	//���û�������
	private String name;
	//����  ���벻�ܴ�
	private transient String password;
	//��������   SocketChannel(ÿ���û���Ӧһ��)
	//Ϊ���ڴ����ʱ��  �ܴ���UserInfo   ��ΪSocket���������л�  ���԰�����Ƴ�transient��
	private transient Socket socket;
	//���û�ʧȥ��ϵ�Ĵ���
	private transient int lost;
	//���û���Ӧ�Ľ�̸����  ���Ȳ�����  ���Գ�ʼΪnull
	private transient ChatFrame chatFrame;
	//������ObjectOutputStream op
	private transient ObjectOutputStream op;
	public UserInfo() {}
	//�в����Ĺ�����   ������chatFrame
	public UserInfo(String name, String password, String icon, Socket socket
			, int lost, ObjectOutputStream op)
	{
		this.icon = icon;
		this.name = name;
		this.password = password;
		this.socket = socket;
		this.lost = lost;
		this.op = op;
	}
	//ֻ���û��� ͷ��
	public UserInfo(String name, String icon)
	{
		this.icon = icon;
		this.name = name;
	}
	//���е�getter setter ����
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	public ChatFrame getChatFrame() {
		return chatFrame;
	}
	public ObjectOutputStream getOp() {
		return op;
	}
	public void setOp(ObjectOutputStream op) {
		this.op = op;
	}
	public void setChatFrame(ChatFrame chatFrame) {
		this.chatFrame = chatFrame;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	//ʹ��address��ΪUserInfo �ı�ʶ ��
	//����ͨ��address��дhashCode,equals
	/*public int hashCode()
	{
		return socket.hashCode();
	}*/
	public boolean equals(Object obj)
	{
		if(obj !=null && obj.getClass() == UserInfo.class)
		{
			//ת����UserInfo
			UserInfo target = (UserInfo)obj;
			//��������null������ȥ
			//�Է�����  ��Ĺ���
			if(getSocket()!=null)
				{if(getSocket().toString() != null)
				{
				/*System.out.println(target.getSocket().toString());
				System.out.println(getSocket().toString());
				System.out.println(getSocket().toString()
						.equals(target.getSocket().toString()));*/
				/*System.out.println((getSocket().toString()
						.equals(target.getSocket().toString())));*/
				//�Ƚ�socket ���ַ���  ������socket
				return (getSocket().toString().equals(target.getSocket().toString()));
				}
				}
			else//��Ϊ�ͻ��˴��socket��Ϊ��  ���Բ��ܱ�socket ֻ�ܱ�name
			{
				if(getName()!=null)
				{
					return(getName().equals(target.getName()));
				}
			}
		}
		//���ִ���ţ����   ����else   �������ifû�ɹ�  ��ô��Ҫ����һ��
		return false;
	}
	
}

