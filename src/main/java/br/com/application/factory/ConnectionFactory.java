package br.com.application.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static final String USERNAME = "admin";

    private static final String PASSWORD = "password";

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/crud";

    public static Connection createConnectionToMySQL() throws Exception{
        Class.forName("com.mysql.cj.jdbc.PreparedStatementWrapper");

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

        return connection;

    }
    public static void main (String[] args) throws Exception{
        Connection con = createConnectionToMySQL();

        if (con != null) {
            System.out.println("Conexão obtida com sucesso! " + con);
            con.close();
        }
    }


}