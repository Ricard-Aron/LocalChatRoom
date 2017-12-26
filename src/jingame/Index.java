package jingame;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Index{

	//��ҳ  ������������ť  �µĿ�ʼ ��������  ��Ϸ���
	//�������趨�����С
	private final int TABLE_WIDTH = 1290;
	private final int TABLE_HEIGHT = 960;
	private final int B_WIDTH = 620;
	private final int B_HEIGHT =100;
	private JFrame f;
	private JButton newGame;
	private JButton loadGame;
	private JButton aboutGame;
	public Index()
	{
		f = new JFrame("��ӹȺ����");
		newGame = new JButton("�µĿ�ʼ");//330 430
		loadGame = new JButton("��������");
		aboutGame = new JButton("��Ϸ���");
		ImageIcon icon=new ImageIcon("src/jingame/icon/title.png");
        f.setIconImage(icon.getImage());
		newGame.setBounds(330, 430, B_WIDTH, B_HEIGHT);
		loadGame.setBounds(330,530, B_WIDTH, B_HEIGHT);
		aboutGame.setBounds(330,700, B_WIDTH, B_HEIGHT);
		
		//��Ӹ�����ť��Ӧ��
		newGame.addActionListener(e ->
		{
			f.setVisible(false);
			try {
				new Game().init();
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		});
		//����ֻ�ܶ�һ��   ��  ����д��  ���꣡
		loadGame.addActionListener(e ->{
			f.setVisible(false);
			try {
				Game g = new Game();
				g.init();
				g.loadFile();
				g.refreshFile();
			} catch (IOException e1) {
				// TODO �Զ����ɵ� catch ��
				e1.printStackTrace();
			}
		});
		f.setLayout(null);
		f.add(newGame);
		f.add(loadGame);
		f.add(aboutGame);
		f.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//�ô����м���ʾ��
		//f.setLocationRelativeTo(null);
		f.pack();
		f.setResizable(false);
		f.setVisible(true);
		
	}
	public static void main(String[] args) throws IOException
	{
		new Index();
	}
}
