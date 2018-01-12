package Classes;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public final class ExecQuery {

    static Connection con;

    public static void createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers?autoReconnect=true&useSSL=false", "root", "root");
    }

    public static ResultSet execQuery(String query) throws SQLException, ClassNotFoundException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public static PreparedStatement updateTable(String statement) throws SQLException, ClassNotFoundException {
        PreparedStatement pstmt = con.prepareStatement(statement);
        return pstmt;
    }

    public static boolean createView(String name, String statement) {
        try {
            Statement st = con.createStatement();
            String code = "CREATE VIEW " + name + " AS " + statement;
            st.executeUpdate(code);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean dropView(String name) {
        try {
            Statement stmt = con.createStatement();
            String sql = "DROP VIEW " + name;
            stmt.executeUpdate(sql);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean viewExists(String name) {
        try {
            DatabaseMetaData dbm = con.getMetaData(); // connection is of type Connection (in JDBC)
            ResultSet tables = dbm.getTables(null, null, name, new String[]{"VIEW"});
            return tables.next();
        }
        catch (Exception e) {
            return false;
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
