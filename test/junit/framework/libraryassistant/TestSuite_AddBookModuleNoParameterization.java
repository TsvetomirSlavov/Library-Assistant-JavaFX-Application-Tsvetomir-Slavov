package junit.framework.libraryassistant;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.libraryassistant.TestRules.databaseHandler;
import library.assistant.database.DatabaseHandler;
import library.assistant.ui.addbook.BookAddController;
import library.assistant.ui.addbook.LibraryAssistant;
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

public class TestSuite_AddBookModuleNoParameterization  {



    @Test
    public void testDatabaseInsertionAddBookHappyPathBoundaries() {
        
//        LibraryAssistant  la = new LibraryAssistant();
//        try {
//            la.init();
//        } catch (Exception ex) {
//            Logger.getLogger(TestSuite_AddBookModuleNoParameterization.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        DatabaseHandler databaseHandler = new DatabaseHandler();

        databaseHandler.execAction("INSERT INTO BOOK VALUES ("
                + "'777',"
                + "'Seven Seventy Seven',"
                + "'Pavlov',"
                + "'Michurin Publishing',"
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

        Assert.assertTrue(stringResult.contains("777"));
    }

    @After
    public void deleteTheBookFromTheDatabase() {
        databaseHandler.execAction("DELETE FROM BOOK WHERE id='777'");
    }

}
