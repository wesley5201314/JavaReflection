package com.java.reflection.demo;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by wesley on 2016/12/21.
 */
public class UserReflection {

    /**
     * 通过反射机制获取类名以及包名,父类
     */
    public static void getClassName(String className) throws ClassNotFoundException {
        System.out.println("-----通过反射机制获取类名以及包名-----");
        //获取类的三种方式
        Class objectClass1 = User.class;
        System.out.println(objectClass1.getName());
        User user = new User();
        Class objectClass2 = user.getClass();
        System.out.println(objectClass2.getName());
        Class objectClass3 = Class.forName("com.java.reflection.demo.User");
        System.out.println(objectClass3.getName());

        Class object = Class.forName(className);
        System.out.println("-------super class--------"+object.getSuperclass().getName());
        System.out.println("-----package name---" + object.getPackage().getName());
        System.out.println("------class name-----" + object.getName());
        System.out.println("------class name-----" + object.getSimpleName());
        System.out.println("=====================================================");
    }

    /**
     * 通过反射机制获取方法名
     */

    public static void getMethodNames(String className) throws ClassNotFoundException{
        System.out.println("-------------通过反射机制获取方法名----------");
        //获取方法名
        Method[] methods = Class.forName(className).getMethods();//public
        //Method[] methods = Class.forName(className).getDeclaredMethods();//private
        for (Method method : methods) {
            System.out.println("-method-" + method.getName());
        }
        System.out.println("=====================================================");
    }

    /**
     * 获取构造器或者说是构造函数
     *
     * @param className
     */
    public static void getConstructor(String className) throws ClassNotFoundException{
        System.out.println("-----------获取构造器或者说是构造函数-------------");
        Constructor[] constructors = Class.forName(className).getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("----constructor-----" + constructor.getName());
            //获取构造函数入参类型
            Class[] classes = constructor.getParameterTypes();
            for (Class cl : classes) {
                System.out.println("-----ParameterType-----" + cl.getName());
            }
        }
        System.out.println("=====================================================");
    }

    /**
     * 获取实现的接口
     *
     * @param className
     */
    public static void getInterfaces(String className) throws ClassNotFoundException{
        System.out.println("-----------获取实现的接口-------------");
        Class[] interfaces = Class.forName(className).getInterfaces();
        for (Class cl:interfaces){
            System.out.println("----interface---"+cl.getName());
        }
        System.out.println("=====================================================");
    }

    /**
     * 利用反射机制获取变量名
     * @param className
     */
    public static void getFields(String className) throws ClassNotFoundException {
        System.out.println("-----------利用反射机制获取变量名-------------");
        //Field[] fields = Class.forName(className).getFields();//public
        Field[] fields = Class.forName(className).getDeclaredFields();//private
        for (Field field : fields) {
            System.out.println("---field name-----"+field.getName()+"----fields type-----"+field.getType());
        }
        System.out.println("=====================================================");
    }

    /**
     * 通过Java反射机制创建一个对象
     * @param className
     * @return
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static User createUser(String className) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException {
        System.out.println("--------通过Java反射机制创建一个对象------");
        Class cl = Class.forName(className);
        User user = (User) cl.newInstance(); //通过无参构造创建
        user.setName("111");
        user.setAge(10);
        user.setAddress("address");
        System.out.println("--------通过无参构造创建 user--------"+user);
        Constructor<User> userConstructor = (Constructor<User>) cl.getConstructor(String.class,Integer.class,String.class);
        User u = userConstructor.newInstance("name",10,"address"); //通过有参构造函数创建
        System.out.println("--------通过有参构造创建 user--------"+u);
        System.out.println("=====================================================");
        return u;
    }

    /**
     * 通过Java反射机制调用类方法
     * @param className
     * @throws NoSuchMethodException
     */
    public static void testMethod(String className) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        System.out.println("-----------通过Java反射机制调用类方法-------------");
        Class cl = Class.forName(className);
        Method method = cl.getMethod("method");//public
        //Method method = cl.getDeclaredMethod("method")//private
        if(method != null){
            Object obj = cl.newInstance();// 无参方式创建对象
            method.invoke(obj);// 调用obj指定的方法
        }
        System.out.println("===========================================");
    }

    /**
     * 通过Java反射机制操作成员变量, set 和 get
     * @param className
     */
    public static void setGetValue(String className) throws IllegalAccessException, InstantiationException, NoSuchFieldException, ClassNotFoundException {
        System.out.println("-----------通过Java反射机制操作成员变量, set 和 get-------------");
        Class cls = Class.forName(className);
        User user = (User) cls.newInstance();
        Field field = cls.getDeclaredField("name");
        field.setAccessible(true);//取消访问检查
        field.set(user,"testName");
        System.out.println("----get value-----"+field.get(user));
        Field field1 = cls.getDeclaredField("address");
        field1.setAccessible(true);//取消访问检查
        field1.set(user,"addressTest");
        System.out.println("----get value-----"+field1.get(user));
        System.out.println("----final user----"+user);
        System.out.println("===========================");
    }

    /**
     * 通过Java反射机制得到类加载器信息
     * 在java中有三种类类加载器
     * 1）Bootstrap ClassLoader 此加载器采用c++编写，一般开发中很少见。
     * 2）Extension ClassLoader 用来进行扩展类的加载，一般对应的是jre\lib\ext目录中的类
     * 3）AppClassLoader 加载classpath指定的类，是最常用的加载器，同时也是java中默认的加载器。
     */
    public static void getClassLoader(String className) throws ClassNotFoundException{
        Class cl = Class.forName(className);
        String classLoaderName = cl.getClassLoader().getClass().getName();
        System.out.println("-----classLoaderName----"+classLoaderName);
    }
}
