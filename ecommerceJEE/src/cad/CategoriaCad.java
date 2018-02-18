package cad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabeans.Categoria;

public class CategoriaCad {
	
	public static ArrayList<Categoria> listar() {
		try {
			String sql = "{ CALL sp_listar_categoria_superior() }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			ResultSet rs = call.executeQuery();		
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("codigo"));
				categoria.setNombre(rs.getString("nombre"));
				categorias.add(categoria);
			}
			
			return categorias;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public static ArrayList<Categoria> listarSubCategoria(int padre) {
		try {
			String sql = "{ CALL sp_listar_sub_categoria(?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setInt(1, padre);
			ResultSet rs = call.executeQuery();		
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("codigo"));
				categoria.setNombre(rs.getString("nombre"));
				categorias.add(categoria);
			}
			
			return categorias;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public static boolean esSuperior(int idCategoria) {
		try {
			String sql = "{ CALL sp_contar_sub_categorias(?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setInt(1, idCategoria);
			ResultSet rs = call.executeQuery();
			
			rs.next();
			
			return rs.getInt("cantidad") > 0 ;
		} catch (SQLException ex) {
			return false;
		}
	}
	
	public static ArrayList<Categoria> listarTodoCategoria() {
		try {
			String sql = "{ CALL sp_listar_todo_categoria() }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			ResultSet rs = call.executeQuery();		
			ArrayList<Categoria> categorias = new ArrayList<Categoria>();
			
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("codigo"));
				categoria.setNombre(rs.getString("nombre"));
				categorias.add(categoria);
			}
			
			return categorias;
		} catch (SQLException ex) {
			return null;
		}
	}
}
