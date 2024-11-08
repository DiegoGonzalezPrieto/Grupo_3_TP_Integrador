package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.ClienteDao;
import dao.UsuarioDao;
import dominio.Cliente;
import dominio.Localidad;
import dominio.Nacionalidad;
import dominio.Provincia;

public class ClienteDaoImpl implements ClienteDao {
	
	private int id;
	private String nombre;

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	@Override
	public boolean insert(Cliente c) {
		String insert = "INSERT INTO clientes (idCliente, dni, cuil, nombre, apellido, correoElectronico, telefono, "
                + "genero, nacionalidad, fechaNacimiento, direccion, localidad, provincia, tipoUsuario) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";	
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(insert)) {
			statement.setInt(1, c.getIdCliente());
	        statement.setString(2, c.getDni());
	        statement.setString(3, c.getCuil());
	        statement.setString(4, c.getNombre());
	        statement.setString(5, c.getApellido());
	        statement.setString(6, c.getCorreoElectronico());
	        statement.setString(7, c.getTelefono());
	        statement.setString(8, c.getGenero());	         
	        statement.setInt(9, c.getNacionalidad().getId());  
	        statement.setDate(10, java.sql.Date.valueOf(c.getFechaNacimiento().toLocalDate()));
	        statement.setString(11, c.getDireccion());
	        statement.setInt(12, c.getLocalidad().getId());
	        statement.setInt(13, c.getProvincia().getId());
	        statement.setInt(14, c.getTipoUsuario().getId());
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(int id, Cliente c) {
		String updateCliente = "UPDATE clientes SET dni = ?, cuil = ?, nombre = ?, apellido = ?, correoElectronico = ?, "
                + "telefono = ?, genero = ?, nacionalidad = ?, fechaNacimiento = ?, direccion = ?, "
                + "localidad = ?, provincia = ? WHERE idCliente = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(updateCliente)) {
			statement.setString(1, c.getDni());
			statement.setString(2, c.getCuil());
			statement.setString(3, c.getNombre());
			statement.setString(4, c.getApellido());
			statement.setString(5, c.getCorreoElectronico());
			statement.setString(6, c.getTelefono());
			statement.setString(7, c.getGenero());
			statement.setString(8, c.getNacionalidad().toString()); 
			//statement.setDate(9, new java.sql.Date(c.getFechaNacimiento().getTime())); 
			statement.setString(10, c.getDireccion());
			statement.setString(11, c.getLocalidad().toString());  
			statement.setString(12, c.getProvincia().toString());  
			statement.setInt(13, id);  
			int rowsAffected = statement.executeUpdate();
			return rowsAffected > 0;
		
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(int id) {
		
		return false;
	}

	@Override
	public Cliente encontrarPorId(int id) {
		
		String selectCliente = "Select \r\n" + 
								"	C.id_cliente As 'ID'," + 
								"    C.dni As 'DNI'," + 
								"    C.cuil As 'CUIL'," + 
								"    C.nombre As 'Nombre'," + 
								"    C.apellido As 'Apellido'," + 
								"    C.email as 'E-mail'," + 
								"    C.telefono As 'Telefono'," + 
								"    C.sexo as 'Genero'," + 
								"    C.fecha_nacimiento As 'Fecha de nacimiento' ," + 
								"    C.direccion As 'Dirección'," + 
								"    N.nacionalidad As 'Nacionalidad'," + 
								"    P.provincia As 'Provincia'" + 
								"from clientes" + 
								"	Inner Join nacionalidades N on N.id_nacionalidad = C.id_nacionalidad" + 
								"	Inner Join provincias P on P.id_provincia = C.id_provincia" + 
								"	Inner Join localidades L on L.id_localidad = C.id_localidad" + 
								"Where C.id_cliente = ?";
								
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	        return null;
	    }

	    try (Connection conexion = Conexion.getConnection();
	         PreparedStatement statement = conexion.prepareStatement(selectCliente)) {
	        statement.setInt(1, id); 
	        ResultSet resultSet = statement.executeQuery();
	        if (resultSet.next()) {
	        	 Cliente cliente = new Cliente();
	             cliente.setIdCliente(resultSet.getInt("id_Cliente"));
	             cliente.setDni(resultSet.getString("dni"));
	             cliente.setCuil(resultSet.getString("cuil"));
	             cliente.setNombre(resultSet.getString("nombre"));
	             cliente.setApellido(resultSet.getString("apellido"));
	             cliente.setCorreoElectronico(resultSet.getString("email"));
	             cliente.setTelefono(resultSet.getString("telefono"));
	             cliente.setGenero(resultSet.getString("sexo"));
	             cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
	             cliente.setDireccion(resultSet.getString("direccion"));
	             Nacionalidad nacionalidad = new Nacionalidad(resultSet.getInt("nacionalidad_id"), resultSet.getString("nacionalidad"));
	             //nacionalidad.setId(resultSet.getInt("nacionalidad_id")); 
	             //nacionalidad.setNombre(resultSet.getString("nacionalidad_nombre")); 
	             cliente.setNacionalidad(nacionalidad);
	             Provincia provincia = new Provincia(resultSet.getInt("id_provincia"), resultSet.getString("provincia"));
	             //provincia.setId(resultSet.getInt("id_provincia")); 
	             //provincia.setNombre(resultSet.getString("provincia")); 
	             cliente.setProvincia(provincia);
	             Localidad localidad = new Localidad(resultSet.getInt("localidad_id"), resultSet.getString("localidad"), provincia);
	             //localidad.setId(resultSet.getInt("id_localidad")); 
	             //localidad.setNombre(resultSet.getString("localidad_nombre")); 
	             cliente.setLocalidad(localidad);
	             return cliente;
	        } else {
	            return null;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	@Override
	public Cliente encontrarPorNombre(String nombre) {
		
		String selectCliente = "Select \r\n" + 
								"	C.id_cliente As 'ID'," + 
								"    C.dni As 'DNI'," + 
								"    C.cuil As 'CUIL'," + 
								"    C.nombre As 'Nombre'," + 
								"    C.apellido As 'Apellido'," + 
								"    C.email as 'E-mail'," + 
								"    C.telefono As 'Telefono'," + 
								"    C.sexo as 'Genero'," + 
								"    C.fecha_nacimiento As 'Fecha de nacimiento' ," + 
								"    C.direccion As 'Dirección'," + 
								"    N.nacionalidad As 'Nacionalidad'," + 
								"    P.provincia As 'Provincia'" + 
								"from clientes C" + 
								"	Inner Join nacionalidades N on N.id_nacionalidad = C.id_nacionalidad" + 
								"	Inner Join provincias P on P.id_provincia = C.id_provincia" + 
								"	Inner Join localidades L on L.id_localidad = C.id_localidad" + 
								"Where C.nombre = ?"; 
        					
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		try (Connection conexion = Conexion.getConnection();
				PreparedStatement statement = conexion.prepareStatement(selectCliente)) {
		
			statement.setString(1, nombre); 
			ResultSet resultSet = statement.executeQuery();
			
			if (resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setIdCliente(resultSet.getInt("id_Cliente"));
				cliente.setDni(resultSet.getString("dni"));
				cliente.setCuil(resultSet.getString("cuil"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setCorreoElectronico(resultSet.getString("email"));
				cliente.setTelefono(resultSet.getString("telefono"));
				cliente.setGenero(resultSet.getString("sexo"));
				cliente.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
				cliente.setDireccion(resultSet.getString("direccion"));		
				Nacionalidad nacionalidad = new Nacionalidad(resultSet.getInt("nacionalidad_id"), resultSet.getString("nacionalidad"));
				cliente.setNacionalidad(nacionalidad);		
				Provincia provincia = new Provincia(resultSet.getInt("id_provincia"), resultSet.getString("provincia"));
				cliente.setProvincia(provincia);
				Localidad localidad = new Localidad(resultSet.getInt("localidad_id"), resultSet.getString("localidad"), provincia);
				cliente.setLocalidad(localidad);				
				return cliente;
				} else {
				return null;
			}
			
			} catch (SQLException e) {
			e.printStackTrace();
			return null;
			}
				}

	}
