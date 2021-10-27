package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class Conexion {

	private static Logger logger = LogManager.getLogger(Conexion.class);
	static String bd = "empresa";
	static String login = "root";
	static String password = "";
	static String host = "127.0.0.1"; // localhost

	static String url = "jdbc:mysql://";
	static Connection conexion; // atributo para guardar el objeto Connection

	public static Connection getConexion() {
		if (conexion == null) {
			crearConexion();
			String methodName = Conexion.class.getSimpleName();
			logger.info(String.format(" >>>>>> Conexion creada.", methodName));
		}
		return conexion;
	}

	// devuelve true si se ha creado correctamente
	public static boolean crearConexion() {
		try {
			// cargo el driver
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url + host + "/" + bd, login, password);

			conexion.setAutoCommit(false);

		} catch (SQLException e) {
			logger.warn(String.format(">>>>>> No se ha creado la conexion.", e));
			return false;
		} catch (Exception e) {
			logger.warn(String.format(">>>>>> No se ha creado la conexion.", e));
			return false;
		
		}
		logger.info(String.format(">>>>>>Se ha creado la conexion."));
		return true;
	}

	public static void desconectar() {
		try {
			conexion.close();
			conexion = null;
			System.out.println("La conexion a la  base de datos " + bd + " ha terminado");

		} catch (SQLException e) {
			System.out.println("Error al cerrar la conexion");
		}
	}
}
