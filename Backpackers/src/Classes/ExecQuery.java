package Classes;

import java.sql.*;

public final class ExecQuery {

    public ExecQuery() {}

    public static ResultSet execQuery(String query) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/backpackers", "root", "root");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        return rs;
    }
}
