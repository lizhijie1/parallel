package day01;


import java.util.function.Function;
/**
 * 函数式编程
 */
public class Text1 {
    /**
     * 方法引用
     *  1.静态方法引用:ClassName::methodName
     *  2.实例上的实例方法引用：instanceReference::methodName
     *  3.超类上的实例方法引用: super::methodName
     *  4.类型上的实例方法引用: ClassName::methodName
     *  5.构造方法引用:Class::new
     *  6.数组构造方法引用：TypeName[]::new
     *
     *
     *
     *
     *
     *
     *
     *
     */
    static int num = 2;
    public static void main(String args[]){
        Function<Integer,Integer> stringAdd = (from)-> (num* from);
        System.out.println(stringAdd.apply(3));
    }
}
