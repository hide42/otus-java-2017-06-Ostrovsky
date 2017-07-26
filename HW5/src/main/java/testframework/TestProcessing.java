package testframework;


import com.google.common.reflect.ClassPath;
import testframework.annotations.After;
import testframework.annotations.Before;
import testframework.annotations.Test;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created by peter on 17.07.2017.
 */
public class TestProcessing {

    private static ArrayList<Method> testableMethods = new ArrayList<Method>();
    private static ArrayList<Method> beforeTestableMethods = new ArrayList<Method>();
    private static ArrayList<Method> afterTestableMethods = new ArrayList<Method>();


    private TestProcessing() {
    }

    public static void testPackage(String testPackage) throws IOException, IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("TEST PACKAGE");
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        ClassPath classPath = ClassPath.from(classLoader);
        for (int i=0;i<classPath.getTopLevelClasses(testPackage).asList().size();i++)
            testClass(classPath.getTopLevelClasses(testPackage).asList().get(i).load());
    }

    public static void testClassList(Class ...classes) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        System.out.println("TEST CLASSES");
        for (Class oneClass : classes){
            testClass(oneClass);
        }
    }

    public static void testClass(Class testableClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        System.out.println("TEST CLASS "+testableClass.getSimpleName());
        initMethods(testableClass);

        if(!testableMethods.isEmpty())
        runTests(testableClass);
        System.out.println("******end testing "+testableClass.getSimpleName()+"*****");
    }
    private static void initMethods(Class testableClass){
        for(final Method method : testableClass.getMethods()){
            for(Annotation annotation: method.getAnnotations()){

                if(annotation.annotationType().equals(Test.class)){
                    testableMethods.add(method);
                }

                if(annotation.annotationType().equals(Before.class)){
                    beforeTestableMethods.add(method);
                }

                if(annotation.annotationType().equals(After.class)){
                    afterTestableMethods.add(method);
                }
            }
        }
    }
    private static void runTests(Class testableClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        for(Method method : testableMethods){
            final Object instanceOfClass = testableClass.newInstance();

            for(Method before : beforeTestableMethods)
                before.invoke(instanceOfClass);

            method.invoke(instanceOfClass);

            for (Method after : afterTestableMethods)
                after.invoke(instanceOfClass);
        }

        testableMethods.clear();
        afterTestableMethods.clear();
        beforeTestableMethods.clear();
    }

}
