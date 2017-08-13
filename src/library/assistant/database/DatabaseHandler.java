package library.assistant.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author cccce
 */
public final class DatabaseHandler {

    private static DatabaseHandler handler = null;

    // Derby database is different because it can be used just as a folder, so you have the ability to just copy the folder and it will work on another computer
    private static final String DB_URL = "jdbc:derby:database/library2;create=true";
    private static Connection conn = null;
    private static Statement stmt = null;

    private DatabaseHandler() {
        createConnection();
        setupBookTable();
        setupMemberTable();
    }
    
    public static DatabaseHandler getInstance(){        
        if(handler == null){            
            handler = new DatabaseHandler();            
        }
        return handler;        
    }

    /**
     * Create a connection to the database before using it create the global
     * object stmt and use it later in setupBookTable, execQuery, execAction
     */
    void createConnection() {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            conn = DriverManager.getConnection(DB_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a database if there is not already a folder with a database when
     * the program is run
     *
     */
    void setupBookTable() {
        String TABLE_NAME = "BOOK";
        try {
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
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " ... setupDatabase");
        } finally {

        }
    }
    
       void setupMemberTable() {
        String TABLE_NAME = "MEMBER";
        try {
            stmt = conn.createStatement();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet tables = dbm.getTables(null, null, TABLE_NAME.toUpperCase(), null);
            if (tables.next()) {
                System.out.println("Table " + TABLE_NAME + " already exists. Ready for go!");
            } else {
                stmt.execute("CREATE TABLE " + TABLE_NAME + "("
                        + " id varchar(200) primary key,\n"
                        + " name varchar(200),\n"
                        + " mobile varchar(20),\n"
                        + " email varchar(100)"
                        + " )");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage() + " ... setupDatabase");
        } finally {

        }
    }

    /**
     * Query the database stmt.executeQuery(query); returns ResultSet
     *
     * @return ResultSet it returns the data that we request from the database
     */
    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler " + ex.getLocalizedMessage());
            return null;
        } finally {

        }
        return result;
    }

    /**
     * Execute an action on the database like CREATE, DELETE and use
     * stmt.execute(qu); because it does not reurn like
     * stmt.executeQuery(query); returns ResultSet
     *
     * @return boolean whether the action was succesfuly executed
     */
    public boolean execAction(String qu) {
        try {
            stmt = conn.createStatement();
            stmt.execute(qu);
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler " + ex.getLocalizedMessage());
            return false;
        } finally {

        }
    }

}
