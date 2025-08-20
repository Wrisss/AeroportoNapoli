package implementazionePostgresDAO;

import dao.PostgresDAO;
import database.ConnessioneDatabase;
import java.sql.Connection;
import java.sql.SQLException;

public class ImplementazionePostgresDAO implements PostgresDAO {

    private Connection connection;

    public ImplementazionePostgresDAO() {
        try {
            connection = ConnessioneDatabase.getInstance().connection;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
