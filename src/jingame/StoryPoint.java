package jingame;

public class StoryPoint implements java.io.Serializable{
	//��������˭˵��
	//0��ʾNpc 1��ʾHero
	private boolean whoTalk;
	//��˵��������
	private String content;
	//������������
	public StoryPoint(boolean whoTalk, String content)
	{
		this.whoTalk = whoTalk;
		this.content = content;
	}
	
	

	public boolean isWhoTalk() {
		return whoTalk;
	}



	public void setWhoTalk(boolean whoTalk) {
		this.whoTalk = whoTalk;
	}



	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
