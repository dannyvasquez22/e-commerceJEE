package cad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javabeans.Producto;
import javabeans.ProductoMoneda;

public class ProductoCad {

	public static boolean registrarProducto(Producto p, ProductoMoneda cop, ProductoMoneda usd, ProductoMoneda pen) {
		try {
			String sql = "{ CALL sp_registrar_producto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setString(1, p.getNombre());
			call.setFloat(2, p.getPrecio());
			call.setFloat(3, p.getPrecio_nuevo());
			call.setInt(4, p.getStock());
			call.setBoolean(5, p.isNuevo());
			call.setBoolean(6, p.isRecomendado());
			call.setString(7, p.getDescripcion());
			call.setBoolean(8, p.isVisible());
			call.setInt(9, p.getCodigo_marca());
			call.setInt(10, p.getCodigo_categoria());
			call.setString(11, p.getImg());
			call.setString(12, cop.getMoneda());
			call.setFloat(13, cop.getPrecio());
			call.setFloat(14, cop.getPrecio_nuevo());
			call.setString(15, usd.getMoneda());
			call.setFloat(16, usd.getPrecio());
			call.setFloat(17, usd.getPrecio_nuevo());
			call.setString(18, pen.getMoneda());
			call.setFloat(19, pen.getPrecio());
			call.setFloat(20, pen.getPrecio_nuevo());
								
			return call.executeUpdate() > 0;
		} catch (SQLException ex) {
			return false;
		}
	}
	
	public static ArrayList<Producto> listarProductosRecomendados(String p_moneda) {
		try {
			String sql = "{ CALL sp_listar_recomendados(?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setString(1, p_moneda);
			ResultSet rs = call.executeQuery();
			ArrayList<Producto> lista = new ArrayList<Producto>();
			
			while (rs.next()) {
				Producto p = new Producto();
				p.setWebid(rs.getInt("webid"));
				p.setNombre(rs.getString("nombre"));
				p.setImg(rs.getString("img"));
				p.setStock(rs.getInt("stock"));
				p.setNuevo(rs.getBoolean("nuevo"));
				
				if (!p_moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(rs.getFloat("precio2"));
					p.setPrecio_nuevo(rs.getFloat("precion2"));
				} else {
					p.setPrecio(rs.getFloat("precio"));
					p.setPrecio_nuevo(rs.getFloat("precio_nuevo"));
				}
				lista.add(p);
			}
			return lista;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public static ArrayList<Producto> listarProductosPorCategoria(String p_moneda, int p_categoria) {
		try {
			String sql = "{ CALL sp_listar_por_categoria(?, ?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setString(1, p_moneda);
			call.setInt(2, p_categoria);
			ResultSet rs = call.executeQuery();
			ArrayList<Producto> lista = new ArrayList<Producto>();
			
			while (rs.next()) {
				Producto p = new Producto();
				p.setWebid(rs.getInt("webid"));
				p.setNombre(rs.getString("nombre"));
				p.setImg(rs.getString("img"));
				p.setStock(rs.getInt("stock"));
				p.setNuevo(rs.getBoolean("nuevo"));
				
				if (!p_moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(rs.getFloat("precio2"));
					p.setPrecio_nuevo(rs.getFloat("precion2"));
				} else {
					p.setPrecio(rs.getFloat("precio"));
					p.setPrecio_nuevo(rs.getFloat("precio_nuevo"));
				}
				lista.add(p);
			}
			return lista;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public static ArrayList<Producto> listarProductosPorMarca(String p_moneda, int p_marca) {
		try {
			String sql = "{ CALL sp_listar_por_marca(?, ?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setString(1, p_moneda);
			call.setInt(2, p_marca);
			ResultSet rs = call.executeQuery();
			ArrayList<Producto> lista = new ArrayList<Producto>();
			
			while (rs.next()) {
				Producto p = new Producto();
				p.setWebid(rs.getInt("webid"));
				p.setNombre(rs.getString("nombre"));
				p.setImg(rs.getString("img"));
				p.setStock(rs.getInt("stock"));
				p.setNuevo(rs.getBoolean("nuevo"));
				
				if (!p_moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(rs.getFloat("precio2"));
					p.setPrecio_nuevo(rs.getFloat("precion2"));
				} else {
					p.setPrecio(rs.getFloat("precio"));
					p.setPrecio_nuevo(rs.getFloat("precio_nuevo"));
				}
				lista.add(p);
			}
			return lista;
		} catch (SQLException ex) {
			return null;
		}
	}
	
	public static Producto consultarProducto(String p_moneda, int p_webid) {
		try {
			String sql = "{ CALL sp_consultar_producto(?, ?) }";
			Connection c = Conexion.conectar();
			CallableStatement call = c.prepareCall(sql);
			call.setString(1, p_moneda);
			call.setInt(2, p_webid);
			ResultSet rs = call.executeQuery();
			
			Producto p = null;
			
			if (rs.next()) {
				p = new Producto();
				p.setWebid(rs.getInt("webid"));
				p.setNombre(rs.getString("nombre"));
				p.setImg(rs.getString("img"));
				p.setStock(rs.getInt("stock"));
				p.setNuevo(rs.getBoolean("nuevo"));
				
				if (!p_moneda.equalsIgnoreCase("MXN")) {
					p.setPrecio(rs.getFloat("precio2"));
					p.setPrecio_nuevo(rs.getFloat("precion2"));
				} else {
					p.setPrecio(rs.getFloat("precio"));
					p.setPrecio_nuevo(rs.getFloat("precio_nuevo"));
				}
			}
			return p;
		} catch (SQLException ex) {
			return null;
		}
	}
}
