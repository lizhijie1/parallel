package reflect;

public class Student {
	public String name;
	public int age;
	private int sex;
	public Student() {
		// TODO Auto-generated constructor stub
		System.out.println("�չ���������ʵ��");
	}
	public Student(String name,int age,int sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		System.out.println("������һ��ѧ��ʵ��"+name+":"+age+":"+sex);
	}
	public void  goClass(){
		System.out.println("ȥ�Ͽ�");
	}
	
	private void goBack(){
		System.out.println("���ظ���");
	}
}
