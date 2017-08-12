package junit.framework.libraryassistant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.assistant.ui.addbook.BookAddController;
import library.assistant.ui.listbook.BookListController;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author cccce
 */
@RunWith(Parameterized.class)
public class TestSuite_AddBookModule extends TestRules {

    @Parameterized.Parameter
    public JSONObject json;

    @Parameterized.Parameters
    public static Collection data() throws IOException, ParseException {
        return HelperParameterizedTests.data();
    }

    @Test
    public void testDatabaseInsertionAddBookHappyPathBoundaries() {

        databaseHandler.execAction("INSERT INTO BOOK VALUES ("
                + "'" + json.get("bookID") + "',"
                + "'" + json.get("bookName") + "',"
                + "'" + json.get("bookAuthor") + "',"
                + "'" + json.get("bookPublisher") + "',"
                + "" + true + ""
                + ")");

        
        StringBuffer result = new StringBuffer("");
        ResultSet rs = databaseHandler.execQuery("SELECT id FROM BOOK");
        try {
            while (rs.next()) {
                String id = rs.getString("id");
                result.append(id + "; ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookAddController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("All book ids in the database: " + result);
        
        String stringResult = result.toString();

        Assert.assertTrue(stringResult.contains((CharSequence) json.get("bookID")));
    }

    @After
    public void deleteTheBookFromTheDatabase() {
        databaseHandler.execAction("DELETE FROM BOOK WHERE id='" + json.get("bookID") + "'");
    }

}
