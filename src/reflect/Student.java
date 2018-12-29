package reflect;

public class Student {
	public String name;
	public int age;
	private int sex;
	public Student() {
		// TODO Auto-generated constructor stub
		System.out.println("空构造器创建实例");
	}
	public Student(String name,int age,int sex) {
		this.name = name;
		this.age = age;
		this.sex = sex;
		System.out.println("创建了一个学生实例"+name+":"+age+":"+sex);
	}
	public void  goClass(){
		System.out.println("去上课");
	}
	
	private void goBack(){
		System.out.println("各回各家");
	}
}
