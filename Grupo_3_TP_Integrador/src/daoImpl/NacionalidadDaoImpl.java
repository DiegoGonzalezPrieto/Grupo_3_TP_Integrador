package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.NacionalidadDao;
import dominio.Nacionalidad;

public class NacionalidadDaoImpl implements NacionalidadDao{
	@Override
    public Nacionalidad buscarPorId(int id) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String select = "SELECT id_nacionalidad, nacionalidad FROM nacionalidades WHERE id_nacionalidad = ?;";
        
        try (Connection conexion = Conexion.getConnection();
                PreparedStatement statement = conexion.prepareStatement(select)) {

               statement.setInt(1, id);

               ResultSet result = statement.executeQuery();

               if (result.next()) {
                   return new Nacionalidad(result.getInt("id_nacionalidad"), result.getString("nacionalidad"));
               }

           } catch (SQLException e) {
               e.printStackTrace();
               return null;
           }
           return null;
       }

       @Override
       public ArrayList<Nacionalidad> buscarTodos() {
           try {
               Class.forName("com.mysql.jdbc.Driver");
           } catch (ClassNotFoundException e) {
               e.printStackTrace();
           }

           String select = "SELECT id_nacionalidad, nacionalidad FROM nacionalidades;";
           ArrayList<Nacionalidad> resultado = new ArrayList<>();

           try (Connection conexion = Conexion.getConnection();
                PreparedStatement statement = conexion.prepareStatement(select)) {

               ResultSet result = statement.executeQuery();

               while (result.next()) {
                   Nacionalidad nacionalidad = new Nacionalidad(result.getInt("id_nacionalidad"), result.getString("nacionalidad"));
                   resultado.add(nacionalidad);
               }

           } catch (SQLException e) {
               e.printStackTrace();
               return resultado;
           }
           return resultado;
       }
   }
