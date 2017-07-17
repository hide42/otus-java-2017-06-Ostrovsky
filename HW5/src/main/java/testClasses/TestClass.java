package testClasses;

import testframework.annotations.*;

/**
 * Created by peter on 17.07.2017.
 */
public class TestClass {
    @Test
    public void mainMethodA(){
        System.out.println("in mainmethod A");
    }

    @Test
    public void mainMethodB(){
        System.out.println("in mainmethod B");
    }

    @Before
    public void preMethod(){
        System.out.println("in preMethod");
    }

    @After
    public void afterMethod(){
        System.out.println("in afterMethod");
    }
}
