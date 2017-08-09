
package library.assistant.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





/**
 *
 * @author cccce
 */
public final class DatabaseHandler {
    
    private static DatabaseHandler handler;
    
    // Derby database is different because it can be used just as a folder, so you have the ability to just copy the folder and it will work on another computer
    private static final String DB_URL = "djbc:derby:database/library2;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;
    
    public DatabaseHandler(){
        createConnection();
        setupBookTable();
    }
    
    void  createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void setupBookTable() {
        String TABLE_NAME = "BOOK";
        try{
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + " id varchar(200) primary key,\n"
                        + " title varchar(200),\n"
                        + " author varchar(200),\n"
                        + " publisher varchar(100),\n"
                        + " isAvailable boolean default true"
                        + " )");                
            } 
        }catch (SQLException e) {
                    System.err.println(e.getMessage() + " ... setupDatabase");
        } finally {
                    
        }
    }
}
    
    

