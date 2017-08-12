
package junit.framework.libraryassistant;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 *
 * @author cccce
 */
public class Retry implements TestRule{
    
    private int retryCount;
    
    public Retry(int retryCount){
        this.retryCount = retryCount;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return null;
    }
    
//    private Statement stamenet(final Statement base, final Description description){
//        retrurn new Statement() {
//            @Override
//            public void evaluate() throws Throwable {
//                Throwable throwable = null;
//                for(int i = 0; i < retryCount; i++){
//                    try{
//                        base.evaluate();
//                        return;
//                    } catch(Throwable e){
//                        throwable = e;
//                    }
//                }
//                throw throwable;
//            }
//            
//        };
//    }
    
}
