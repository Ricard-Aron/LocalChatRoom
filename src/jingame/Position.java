package jingame;

public class Position implements java.io.Serializable{
	//������ǵ�ǰ��λ����Ϣ
		//����λ��
		private GameField f;
		//Ҳ�Ǵ淿��λ��  �����Ǵ��fieldList
		//private int floor;
		//��ǰ����  ����һ������	
		//xy[0]=x xy[1]=l_x xy[2]=y  xy[3]=l_y
		//5��¥��λ��  λ�ô浵����
		private int[] xy =new int[5];
		public GameField getF() {
			return f;
		}
		public void setF(GameField f) {
			this.f = f;
		}
		public int[] getXy() {
			return xy;
		}
		public void setXy(int[] xy) {
			this.xy = xy;
		}
		/*public int getFloor() {
			return floor;
		}
		public void setFloor(int floor) {
			this.floor = floor;
		}*/
}
