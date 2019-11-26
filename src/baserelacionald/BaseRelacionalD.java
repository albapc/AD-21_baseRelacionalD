package baserelacionald;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alba
 */
public class BaseRelacionalD {

    public static Connection conn = null;

    private Connection conexion() {
        final String driver = "jdbc:oracle:thin:";
        final String host = "localhost.localdomain";
        final String porto = "1521";
        final String sid = "orcl";
        final String usuario = "hr";
        final String password = "hr";
        String url = driver + usuario + "/" + password + "@" + host + ":" + porto + ":" + sid;

//        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void amosarMetaDatos() {
        String sql = "SELECT produtos.* FROM produtos";

        try (Connection conn = this.conexion();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("Número de columnas: " + rsmd.getColumnCount() + "\n");

            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(i + " ");
                System.out.println(rsmd.getColumnName(i) + ": " + rsmd.getColumnTypeName(i) + ", " + rsmd.getPrecision(i));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BaseRelacionalD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws SQLException {
        BaseRelacionalD obx = new BaseRelacionalD();
        obx.amosarMetaDatos();
        conn.close();
    }

}
