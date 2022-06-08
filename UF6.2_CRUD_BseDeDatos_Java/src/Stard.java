import java.util.ArrayList;
import java.util.Scanner;


import java.util.ArrayList;
import java.util.Scanner;

public class Stard {
	
	public static void mostrarUsuarios(BaseDeDatos db) 
	{
		System.out.println("*******************************************");
		int i = 0;
		
		ArrayList<Usuario> usuarios=new ArrayList <Usuario>();
		usuarios= db.R("SELECT * FROM usuarios");
		
		for(i=0;i< usuarios.size();i++)
		{
			System.out.println(usuarios.get(i).getId() + "--"+usuarios.get(i).getNombre() + "--" + usuarios.get(i).getPassword() + "--" + usuarios.get(i).getEdad() + "--" + usuarios.get(i).getSalario() + "--" + usuarios.get(i).getActivo()); 
		}
		System.out.println("*******************************************");
	}
	public static void insertarUsuario(BaseDeDatos db)
	{
		Scanner entradaDeDatos = new Scanner(System.in);
		Usuario u= new Usuario();
		boolean estado= false;
		
		System.out.print("Introduce tu nombre de usuario: ");
		u.setNombre(entradaDeDatos.next());
		System.out.print("Introduce tu password: ");
		u.setPassword(entradaDeDatos.next());
		System.out.print("Introduce tu edad: ");
		u.setEdad(entradaDeDatos.nextInt());
		System.out.print("Introduce tu password: ");
		u.setSalario(entradaDeDatos.nextFloat());
		
		estado=db.C(u, "usuarios");
		
		if (!estado)
		{
			System.out.println("Usuario creado correctamente");
		}
		else
		{
			System.out.println("Se ha producido un error al crear el usuario");
		}
	}
	
	public static void borrarUsuario(BaseDeDatos db)
	{
		Scanner entradaDeDatos = new Scanner(System.in);
		Usuario u= new Usuario();
		boolean estado= false;
		int id=0;
		
		mostrarUsuarios(db);
		System.out.println("******************************");
		
		System.out.print("Introduce el id del usuario  ELIMINAR: ");
		id=entradaDeDatos.nextInt();
		
		
		estado=db.D("DELETE FROM USUARIOS WHERE id= " + id);
		
		if (!estado)
		{
			System.out.println("Usuario borrado correctamente");
		}
		else
		{
			System.out.println("Se ha producido un error al borrar el usuario");
		}
	}
	
	public static void modificarUsuario(BaseDeDatos db)
	{
		Scanner entradaDeDatos = new Scanner(System.in);
		Usuario u= new Usuario();
		boolean estado= false;
		
		mostrarUsuarios(db);
		System.out.println("******************************");
		
		System.out.print("Introduce el id de usuario: ");
		u.setId(entradaDeDatos.nextInt());
		System.out.print("Introduce tu nombre de usuario: ");
		u.setNombre(entradaDeDatos.next());
		System.out.print("Introduce tu password: ");
		u.setPassword(entradaDeDatos.next());
		System.out.print("Introduce tu edad: ");
		u.setEdad(entradaDeDatos.nextInt());
		System.out.print("Introduce tu password: ");
		u.setSalario(entradaDeDatos.nextFloat());
		
		estado=db.U(u, "usuarios");
		
		if (!estado)
		{
			System.out.println("Usuario actualizado correctamente");
		}
		else
		{
			System.out.println("Se ha producido un error al actualizar el usuario");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//CRUD Create Read Update Delete
		
		Scanner entradaDeDatos = new Scanner(System.in);
		
		boolean re=false;
		Usuario user= new Usuario();
		String usuario="";
		String password="";
		boolean tieneAcceso = false;
		int opcion = -1;
		
		
			
			BaseDeDatos db= new BaseDeDatos("localhost", "ifp", "root", "");
			
			System.out.println("******************************");
			System.out.println("******GESTOR DE USUARIOS******");
			System.out.println("******************************");
			System.out.println("");
			
			do
			{
				System.out.print("Introduce tu nombre de usuario: ");
				usuario= entradaDeDatos.next();
				System.out.print("Introduce tu password: ");
				password= entradaDeDatos.next();
				
				tieneAcceso = db.validar(usuario, password);
				
				if (!tieneAcceso)
				{
					System.out.println("Lo siento no tiene acceso");
				}
			}
			while(!tieneAcceso);
				
			System.out.println("Tienes acceso");
			
			do
			{
			
			System.out.println("******************************");
			System.out.println("******GESTOR DE USUARIOS******");
			System.out.println("******************************");
			
			System.out.println("");
			System.out.println("Selecione una opcion del menu.");
			System.out.println("");
			System.out.println("     1)Crear un nuevo usuario.");
			System.out.println("     2)Borrar un usuario.");
			System.out.println("     3)Actualizar un usuario.");
			System.out.println("     4)Listar usuarios.");
			System.out.println("");
			System.out.println("     0)Salir");
			System.out.println("");
			System.out.print("Opcion:  ");
			opcion= entradaDeDatos.nextInt();
			System.out.println("");
			
			if (opcion==1)
			{
						// crear usuario
				
				System.out.println("****************************");
				insertarUsuario(db);
				System.out.println("****************************");
				mostrarUsuarios(db);
				System.out.println("****************************");
				System.out.println("");
				
			}
			
			else if (opcion==2)
			{
					// borrar usuario
				
				System.out.println("****************************");
				borrarUsuario(db);
				System.out.println("****************************");
				mostrarUsuarios(db);
				System.out.println("****************************");
				System.out.println("");
				
			}
			else if (opcion==3)
			{
					// modificar usuario
				
				System.out.println("****************************");
				modificarUsuario(db);
				System.out.println("****************************");
				mostrarUsuarios(db);
				System.out.println("****************************");
				System.out.println("");
				
				
			}
			else if (opcion==4)
			{
					// listar usuario
				
				System.out.println("****************************");
				mostrarUsuarios(db);
				System.out.println("****************************");
				System.out.println("");
			}
			else if (opcion!=0)
			{
				System.out.println("Opcion erronea");
			}
	
	}while(opcion!=0);
			
			System.out.println("Fin del programa");
		
	
			// para mostrar todos los usuarios
			//mostrarUsuarios(db);
			
			/***
			 * Create
			 */
			/*
			//creamos un usuario
			user.setId(4);
			user.setNombre("Pepito Grillo");
			user.setPassword("2222");
			user.setEdad(19);
			user.setSalario(3000);
			
			re=db.U(user, "usuarios");
			 if (!re)
			 {
				 System.out.println("Actualizado correctametne.");
			 }
			 else
			 {
				 System.out.println("Se ha producido un error en la actualizacion.");
			 }
			
			 
			 mostrarUsuarios(db);
			/***
			 * Delete
			 */
		/*
			re=db.D("DELETE FROM usuarios WHERE id=1");
			 if (!re)
			 {
				 System.out.println("Borrado correctametne.");
			 }
			 else
			 {
				 System.out.println("erro al borrar el usuario");
			 }
			*/
			
			
		
}
}