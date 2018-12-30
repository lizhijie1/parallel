package day01;

import java.util.ArrayList;
import java.util.List;

public class InstanceMethodRef {
    public static void main(String args[]){
        List<User> users = new ArrayList<User>();
        for (int i = 1;i<10;i++){
            users.add(new User(1,"billy"+Integer.toString(i)));
        }
        users.stream().map(User::getName).forEach(System.out::println);
    }
}
