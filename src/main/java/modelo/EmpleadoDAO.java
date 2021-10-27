package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import conexion.Conexion;

public class EmpleadoDAO {
	
	private Connection conexion;
	 public HashMap<Integer, Empleado> getTodosEmpleadosQuery() {

	        conexion = Conexion.getConexion();
	        Empleado empleado;
	        HashMap<Integer, Empleado> listaEmpleados = null;
	        if (conexion != null) {
	        	listaEmpleados = new HashMap<>();
	            try {
	                PreparedStatement ps = conexion.prepareStatement("select * from empleado");

	                ResultSet resultado = ps.executeQuery();

	                while (resultado.next()) {
	                	empleado = new Empleado();
	                	empleado.setCodigo(resultado.getInt("codigo"));
	                	empleado.setNombre(resultado.getString("nombre"));
	                	empleado.setApellido1(resultado.getString("apellido1"));
	                	empleado.setApellido2(resultado.getString("apellido2"));
	                	empleado.setLugarNacimiento(resultado.getString("lugar_nacimiento"));
	                	listaEmpleados.put(empleado.getCodigo(), empleado);
	                }
	                
	                resultado.close();
	                ps.close();

	            } catch (SQLException ex) {
	                System.out.println("Error en SQL en el metodo devolver todos los usuario");
	            } 

	        }
	        return listaEmpleados;

	    }

}
