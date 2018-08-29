package postoapplication.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author fag
 */
public class ConnectionFactory {

    public Connection getConnection() {
        //jdbc do postgres/endereco/porta/database
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postodb",
                    "postgres",
                    "root");
        } catch (Exception ex) {
            System.out.println("Erro ao criar uma "
                    + "conexao com o banco de dados");
            ex.getMessage();
        }
        return null;
    }

}
