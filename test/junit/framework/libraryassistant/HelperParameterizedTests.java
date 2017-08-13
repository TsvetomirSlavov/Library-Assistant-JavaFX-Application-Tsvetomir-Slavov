// json simple 0.4 library
package junit.framework.libraryassistant;

import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author cccce
 */
public class HelperParameterizedTests {
    
    public static Collection data(String jsonFileLocation) throws IOException, ParseException {
        ArrayList<Object[]> data = new ArrayList<Object[]>();
        JSONParser parser = new JSONParser();
        JSONObject rawJson = (JSONObject) parser.parse(new FileReader(jsonFileLocation));
        Object[] keys = rawJson.keySet().toArray();
        for(Object key : keys){
            JSONObject json = (JSONObject) rawJson.get(key);
            data.add(new Object[]{json});
        }
        return data;        
    }
    
}
