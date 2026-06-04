package exam;
import java.sql.*;
import java.util.List;

public class DBManager {
    public DBManager() {
    }
    public void saveToSQLite(List<Phone> phones)
    {
        String url = "jdbc:sqlite:inventory.db";
        try(Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement()){
            System.out.println("Connected to SQLite");

            String createTable = "CREATE TABLE IF NOT EXISTS phones ("+
                    "id TEXT PRIMARY KEY," +
                    "brand TEXT,"+
                    "model TEXT,"+
                    "price REAL)";

            statement.execute(createTable);

            String insertSQL = "INSERT OR IGNORE INTO phones (id, brand, model, price) VALUES (?, ?, ?, ?)";

            try(PreparedStatement pstmt = connection.prepareStatement(insertSQL)){
                for (Phone p:phones)
                {
                    pstmt.setString(1, p.getId());
                    pstmt.setString(2,p.getBrand());
                    pstmt.setString(3,p.getModel());
                    pstmt.setDouble(4,p.getPrice());

                    pstmt.executeUpdate();
                }
                System.out.println("Successfully saved data to sqlite");
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }catch (SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
}
