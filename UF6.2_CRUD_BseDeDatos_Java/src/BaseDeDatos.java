
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;

public class BaseDeDatos {
	
		//Atributos 
	
	private String servidor;
	private String usuario;
	private String nombreBD;
	private String password;
	
		// constructor para conectarnos a la BBDD
	
	public BaseDeDatos(String servidor, String nombreBD, String usuario, String password)
	{
		this.servidor=servidor;
		this.nombreBD=nombreBD;
		this.usuario=usuario;
		this.password=password;
		
			// esto es para utilizar el driver para conectarnos en la base de datos
		try {
			DriverManager.registerDriver(new org.gjt.mm.mysql.Driver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean C(Usuario u, String tabla)
	{
		boolean result = false;
		String  consulta="";
		Connection conexion= null;
		Statement s= null;
		
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreBD, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// para establecer la consulta
		try {
			s = (Statement) conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			consulta= "INSERT INTO " + tabla + " (nombre, password, edad, salario, activo) VALUES ('"+u.getNombre()+"', '"+u.getPassword()+"', "+u.getEdad()+","+u.getSalario()+","+u.getActivo()+")";
			result = s.execute(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return result;
	}
	
	public ArrayList<Usuario> R(String consulta)
	{
		int contador = 0;
		Usuario u= null;
		ArrayList<Usuario> alu= new ArrayList<Usuario>();
		Connection conexion=null;
		Statement s= null;
			// para establecer la conexion
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreBD, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// para establecer la consulta
		try {
			s = (Statement) conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			// realizamos la consulta
		ResultSet rs= null;
		
		try {
			rs = s.executeQuery(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(rs.next())
			{
				// Para crear el usuario
				
				u= new Usuario();
				u.setId(rs.getInt("id"));
				u.setNombre(rs.getString("nombre"));
				u.setPassword(rs.getString("password"));
				u.setEdad(rs.getInt("edad"));
				u.setSalario(rs.getFloat("salario"));
				u.setActivo(rs.getBoolean("activo"));

				alu.add(u);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			//Cerramos la conexion
		
		try {
			conexion.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return alu;
	}
	public boolean U(Usuario u, String tabla)
	{
		boolean result = false;
		String  consulta="";
		Connection conexion= null;
		Statement s= null;
		
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreBD, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// para establecer la consulta
		try {
			s = (Statement) conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
	
			consulta= "UPDATE " + tabla + " SET nombre='"+u.getNombre()+"', password='"+u.getPassword()+"', edad='"+u.getEdad()+"', salario ="+u.getSalario()+", activo="+u.getActivo()+ " WHERE id= "+u.getId();
			result = s.execute(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return result;
	}
	public boolean D(String consulta)
	{
		boolean result = false;
		Connection conexion= null;
		Statement s= null;
		
		try {
			conexion = (Connection)DriverManager.getConnection("jdbc:mysql://"+this.servidor+"/"+this.nombreBD, usuario, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			// para establecer la consulta
		try {
			s = (Statement) conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result = s.execute(consulta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				
		return result;
		
	}
	
	public boolean validar(String user, String pass)
	{
		ArrayList<Usuario> l = new ArrayList<Usuario>();
		l=R("SELECT * FROM usuarios where nombre='"+user+"' and password= '"+pass+"' LIMIT 0,1");
		
		if (l.size()==0)
		{
			return false;
		}
		return true;
	}
	
	
}


