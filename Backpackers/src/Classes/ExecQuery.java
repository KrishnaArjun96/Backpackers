package Classes;

import java.sql.*;

public final class ExecQuery {

    public ExecQuery() {}

    public static ResultSet execQuery(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers?autoReconnect=true&useSSL=false", "root", "root");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }

    public static PreparedStatement updateTable(String statement) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers?autoReconnect=true&useSSL=false", "root", "root");
        PreparedStatement pstmt = con.prepareStatement(statement);
        return pstmt;
    }

    public static boolean createView(String name, String statement) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers?autoReconnect=true&useSSL=false", "root", "root");
            Statement st = con.createStatement();
            String code = "CREATE VIEW " + name + " As " + statement;

            st.executeUpdate(code);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean dropView(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers?autoReconnect=true&useSSL=false", "root", "root");
            Statement st = con.createStatement();
            String sql = "DROP VIEW " + name;
            st.executeUpdate(sql);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public static boolean viewExists(String name) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers?autoReconnect=true&useSSL=false", "root", "root");
            DatabaseMetaData dbm = con.getMetaData(); // connection is of type Connection (in JDBC)
            ResultSet tables = dbm.getTables(null, null, name, new String[]{"VIEW"});
            return tables.next();
        }
        catch (Exception e) {
            return false;
        }
    }
}
