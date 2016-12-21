# Java反射机制 #
## 简介 ##

Java反射机制是指在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。用一句话总结就是反射可以实现在运行时可以知道任意一个类的属性和方法。

## Class 对象 ##

在java中类也是对象，是Class类的实例对象，这个对象称为该类的类类型
对于普通的对象，我们一般都会这样创建和表示：

	Code code = new Code();

上面说了，所有的类都是Class的对象，那么该如何表示呢，是不是可不可以通过如下方式呢：

	Class cl = new Class();

但是我们查看Class的源码时，是这样写的：

	 private Class(ClassLoader loader) {
		// Initialize final field for classLoader.  The initialization value of non-null
		// prevents future JIT optimizations from assuming this final field is null.
		classLoader = loader;
	 }

可以看到构造器是私有的，只有JVM可以创建Class的对象，因此不可以像普通类一样new一个Class对象，虽然我们不能new一个Class对象，但是却可以通过已有的类得到一个Class对象，共有三种方式：

    Class objectClass = Object.class;//任何一个类都有一个隐含的静态成员变量class，这种方式是通过获取类的静态成员变量class得到的
    Class objectClass = object.getClass();//object是Object的一个对象，这种方式是通过一个类的对象的getClass()方法获得的
    Class objectClass = Class.forName("xxx.xxx.Object");//这种方法是Class类调用forName方法，通过一个类的全量限定名获得,一般使用这种方式获取

简单的例子：

        Class objectClass1 = User.class;
        System.out.println(objectClass1.getName());
        User user = new User();
        Class objectClass2 = user.getClass();
        System.out.println(objectClass2.getName());
        Class objectClass3 = Class.forName("com.java.reflection.demo.User");
        System.out.println(objectClass3.getName());
执行结果：

    com.java.reflection.demo.User
    com.java.reflection.demo.User
    com.java.reflection.demo.User

## 类名、包名、父类 ##

我们可以从Class对象中获取两个版本的类名：
    
    Class object = Class.forName("com.java.reflection.demo.User");
    
    object.getName() //全限定类名（包含包名） eg:com.java.reflection.demo.User
    object.getSimpleName()//类的名字(不包含包名) eg:User
	object.getPackage() //包名 eg:com.java.reflection.demo
	object.getSuperClass()//父类 eg:java.lang.Object

## 修饰符 ##
可以通过 Class 对象来访问一个类的修饰符， 即public,private,static 等等的关键字，你可以使用如下方法来获取类的修饰符：

    Class object = Class.forName("com.java.reflection.demo.User");
    int modifiers = aClass.getModifiers();

修饰符都被包装成一个int类型的数字，这样每个修饰符都是一个位标识(flag bit)，这个位标识可以设置和清除修饰符的类型。 可以使用 java.lang.reflect.Modifier 类中的方法来检查修饰符的类型：

    Modifier.isAbstract(int modifiers)
    Modifier.isFinal(int modifiers)
    Modifier.isInterface(int modifiers)
    Modifier.isNative(int modifiers)
    Modifier.isPrivate(int modifiers)
    Modifier.isProtected(int modifiers)
    Modifier.isPublic(int modifiers)
    Modifier.isStatic(int modifiers)
    Modifier.isStrict(int modifiers)
    Modifier.isSynchronized(int modifiers)
    Modifier.isTransient(int modifiers)
    Modifier.isVolatile(int modifiers)

## 方法、变量、实现接口、构造函数 ##
可以通过 Class 对象获取 Method 对象，类的成员变量，实现的接口、Constructor类的实例。

- Method对象 

    Method[] methods = Class.forName(className).getMethods();//public方法

    //Method[] methods = Class.forName(className).getDeclaredMethods();//private方法

	//指定的方法

	Method method = cl.getMethod("method");//public

    //Method method = cl.getDeclaredMethod("method")//private

- 成员变量 

    Field[] fields = Class.forName(className).getFields();//public修饰的成员变量

    //Field[] fields = Class.forName(className).getDeclaredFields();//private修饰的成员变量

	//指定的成员变量
	Field field = Class.forName(className).getDeclaredField("name");

- 实现的接口

    Class[] interfaces = Class.forName(className).getInterfaces();

- Constructor类的实例
    
    Constructor[] constructors = Class.forName(className).getConstructors();
 
	//指定的Constructor类的实例

	Constructor<User> userConstructor = (Constructor<User>) Class.forName(className).getConstructor(String.class,Integer.class,String.class);

## 演示demo下载 ##

gitHub:[https://github.com/wesley5201314/JavaReflection](https://github.com/wesley5201314/JavaReflection)

gitosc:[https://git.oschina.net/zhengweishan/JavaReflection](https://git.oschina.net/zhengweishan/JavaReflection)

## 总结 ##
到此，Java反射机制入门的差不多了，我是复习SpringMVC AOP里面自定义异常处理的时候，里面我们通过Java反射来实现的，希望这篇笔记也对你有用。

## 参考资料 ##

1. [Java Reflection Tutorial](http://tutorials.jenkov.com/java-reflection/index.html)
1. [Java反射机制深入详解](http://www.cnblogs.com/hxsyl/archive/2013/03/23/2977593.html)
1. [Java反射入门](http://blog.csdn.net/trigl/article/details/51042403)
1. [Java反射机制](http://www.cnblogs.com/jqyp/archive/2012/03/29/2423112.html)
1. [java反射详解](http://www.cnblogs.com/rollenholt/archive/2011/09/02/2163758.html)
1. [Java 反射机制浅析](http://www.cnblogs.com/gulvzhe/archive/2012/01/27/2330001.html)
1. [反射机制的理解及其用途](http://uule.iteye.com/blog/1423512)