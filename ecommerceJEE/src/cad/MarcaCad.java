package cad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabeans.Marca;

public class MarcaCad {

	public static ArrayList<Marca> listarTodoMarca() {
		try {
			String sql = "{ CALL sp_listar_todo_marca() }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			ResultSet rs = call.executeQuery();		
			ArrayList<Marca> marcas = new ArrayList<Marca>();
			
			while (rs.next()) {
				Marca marca = new Marca();
				marca.setCodigo(rs.getInt("codigo"));
				marca.setNombre(rs.getString("nombre"));
				marcas.add(marca);
			}
			
			return marcas;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public static int contarMarcas(int p_marca) {
		try {
			String sql = "{ CALL sp_contar_productos_marca(?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setInt(1, p_marca);
			ResultSet rs = call.executeQuery();		
			
			rs.next();
			
			return rs.getInt(1);
		} catch (SQLException ex) {
			return 0;
		}
	}
	
}
