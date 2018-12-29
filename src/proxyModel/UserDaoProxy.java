package proxyModel;

public class UserDaoProxy implements IUserDao{
	
	private IUserDao target;
	public UserDaoProxy(IUserDao target){
        this.target=target;
    }

    public void save() {
        System.out.println("��ʼ����...");
        target.save();//ִ��Ŀ�����ķ���
        System.out.println("�ύ����...");
    }

}
