//2017.11.25
package chatroom02;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

//�̳�Dialog
public class ChatFrame extends Dialog {

	// ����һ�����ڸ�ʽ�����ڵĸ�ʽ��
		private DateFormat formatter = DateFormat.getDateTimeInstance();
	//�û�
	UserInfo user ;
	//������Ϣ��
	JTextArea msgArea = new JTextArea(20,60);
	//����������
	JTextField msgField = new JTextField(30);
	//���Ͱ�ť
	JButton send = new JButton("����(ctrl+enter)");
	//�û���Ϣ��
	JPanel userInfo = new JPanel();
	//�û�ͷ��
	ImageIcon image ;
	//�������GUI     ����Client�������û�ע���ʱ��Ͱ�������ʼ���ˣ�����������ʾ 1
	//Ҫô��ͨ��LoginFrame LoginFrame��һ�����Ա��Client  ͨ��������  2
	//�����õڶ��ַ���    ���Գ�ʼ����ʱ�����parent=null
	public ChatFrame(Client parent, final UserInfo user)
	{
		super(parent, "��" + user.getName() +"������");
		this.user = user;
		this.image = new ImageIcon("icon/"+user.getIcon() + ".gif");
		//����Ϣ�����ɱ༭
		msgArea.setEditable(false);
		//����Ϣ���ӹ�����   BorderLayout����
		add(new JScrollPane(msgArea));
		userInfo.add(new JLabel(user.getName(),image,JLabel.CENTER));
		add(userInfo,BorderLayout.NORTH);
		JPanel bottom = new JPanel();
		bottom.add(new JLabel("�����룺"));
		bottom.add(msgField);
		bottom.add(send);
		add(bottom,BorderLayout.SOUTH);
		//���Ϊsend��Ӽ�����        �����õ���Action�ӿ�  �Ҹо�����û��Ҫ��   ����  Action�ӿڹ��ܺ�ǿ��
		//�Ժ��õõ�         �ղ�����lambda��ôд��   �������ں���ʽ�ӿ�    ���e�������淽���Ĳ���  Ȼ��ֱ��д����
		Action sendAction = new AbstractAction()
		{
			public void actionPerformed(ActionEvent evt)
			{
			//ֱ�ӵ���LoginFrame�����Client��send����
			//������ChatFrame����װmassage      ��Ϊ����û���Ϣ ������MASSAGE�����Ǻ�
			//Ȼ���û�����������SPLITTER����  Ȼ��client����send���͸�������
			//��������ͨ����ʽ�⿪   ����û���������     ������ͨ����ǰ��Socket��1�������ϳ�˭����
			//Ȼ����ͨ������û�����ȡ����Socket  ��ͨ�����Socket ��2��
			//��װSocket(1)���û���������  ͨ����2������
			//Ȼ��ͻ���  �⿪��Ϣ   ����û���������  ��֪����˭����
			//ͨ������û�����ȡchatFrame  �������chatFrame��ʾ  Ȼ���ٰ���Ϣ����msgAreaͨ��refreshText
			String content = Protocol.MASSAGE +user.getName() +Protocol.SPLITTER 
					+ msgField.getText() +Protocol.MASSAGE;
			LoginFrame.client.send(content);
			refreshText("��" , msgField.getText());
			//���msgField�Ա㷽��
			msgField.setText("");
			}
		};
		send.addActionListener(sendAction);
		// ��Ctrl+Enter����"send"����
		msgField.getInputMap().put(KeyStroke.getKeyStroke('\n'
			, java.awt.event.InputEvent.CTRL_MASK) , "����(ctrl+enter)");
		// ��"send"��sendAction����
		msgField.getActionMap().put("����(ctrl+enter)", sendAction);
		//chatFrame��ʵֻҪ���������  ��װ��Ϣ ����client��send����                  client��ʵ���ù�Ⱥ������
		//˽�˷�  ֻ�ܷ�������������  �����UDP��һ��  �տ�ʼ���Ի���  UDP���и�IP �˿�ר����broadcast�� 
		//��ÿ���û����и��Լ���IP �˿�  ��TCP�з������Ϳͻ���֮��  ����ֻ��һ���Եİ���Ϣ���ո�ʽ������������
		//��������ô����  �������£����ո�ʽ������У�  ˼·������
		
		//����Dialog  ����رյ�Ȩ��
		this.addWindowListener(new WindowAdapter()  
	    {  
	        public void windowClosing(WindowEvent e)  
	        {  
	        	//������ô���ڲ���������ⲿ�౾��Ķ����� 
	        	//��Ϊ���ڲ������õ�this ��ָ��ǰ�ڲ��� ��ָ�����ⲿ��
	        	//���Ե����ⲿ�� ����ĺ���  ���ⲿ������ĺ���������this 
	        	//������ָ���ⲿ����   ţ�� ѧ����
	            closeFrame();  
	        }  
	    });
		//�����Ͻ�ͼ��
				ImageIcon icon=new ImageIcon("icon/icon.png");
		        this.setIconImage(icon.getImage()); 
		//�ô����м���ʾ��
				setLocationRelativeTo(null);
		//���������Ӧ�ҵĶ���ĸ��ִ�С
		pack();
	}
	// �رմ���   
    private void closeFrame()  
    {  
        System.out.println("���ô���رչ���");  
        int result = JOptionPane.showConfirmDialog
        		(null, "ֻ���ظ�ҳ�棨���رգ�", "ȷ��", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);  
        if (result == JOptionPane.NO_OPTION)  
            this.dispose();  
        else
        	this.setVisible(false);
    }  
	//����������������ݷ���   (ͨ��Client������,Client����Socket�ж�user,ͨ��user�õ���ӦChatFrame
	//��ͨ����ChatFrame ���ø÷������и���
	public void refreshText(String name, String content)
	{
		//����������Ϣǰ
		msgArea.setText(name+"��������"+formatter.format(new Date())+": \n " +content
				+ "\n" + msgArea.getText());
	}  
}
