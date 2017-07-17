
import testClasses.ClassWithoutTests;
import testClasses.TestClass;
import testClasses.TestClassB;
import testframework.TestProcessing;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by peter on 17.07.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException, InvocationTargetException {
        TestProcessing.testPackage("testClasses");

        TestProcessing.testClass(TestClassB.class);

        TestProcessing.testClassList(ClassWithoutTests.class,TestClassB.class);
    }
}
