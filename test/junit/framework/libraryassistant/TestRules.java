
package junit.framework.libraryassistant;

import library.assistant.database.DatabaseHandler;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

/**
 *
 * @author cccce
 */
public class TestRules {
    
    static DatabaseHandler databaseHandler;
    
//    @Rule
//    public Retry retry = new Retry(3);
    
    @BeforeClass
    public static void beforeClass(){
        System.out.println("BeforeClass in TestRules");
        System.out.println("BeforeClass in TestRules initializing DatabaseHandler databaseHandler static object.");
        databaseHandler = new DatabaseHandler();
    }
    
    @Before
    public void before(){
        System.out.println("Before in TestRules");
    }
    
    @After
    public void after(){
        System.out.println("After in TestRules");

    }
    
    @AfterClass 
    public static void afterClass(){
        System.out.println("AfterClass in Test Rules");
    }
    
    @Rule
    public TestRule listen = new TestWatcher() {
        
        @Override
        public void failed(Throwable t, Description description){
            System.out.println("Test method name '" + description.getMethodName() + "' FAILED");
        }
        
        @Override
        public void succeeded(Description description){
            System.out.println("Test method name '" + description.getMethodName() + "' PASSED");
        }
        
    };
    
}
