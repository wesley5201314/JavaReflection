
import com.java.reflection.demo.UserReflection;

import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, NoSuchFieldException, ClassNotFoundException {
        UserReflection.getClassName("com.java.reflection.demo.User");
       /* UserReflection.getMethodNames("com.java.reflection.demo.User");
        UserReflection.getConstructor("com.java.reflection.demo.User");
        UserReflection.getInterfaces("com.java.reflection.demo.User");
        UserReflection.getFields("com.java.reflection.demo.User");
        UserReflection.testMethod("com.java.reflection.demo.User");
        UserReflection.createUser("com.java.reflection.demo.User");
        UserReflection.setGetValue("com.java.reflection.demo.User");
        UserReflection.getClassLoader("com.java.reflection.demo.User");*/
    }
}
