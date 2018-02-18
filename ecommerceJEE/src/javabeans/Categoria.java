package javabeans;

import java.io.Serializable;

public class Categoria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;	
	private String nombre;
	private boolean visible;
	private int categoria_superior;
	
	public Categoria() {
		// TODO Auto-generated constructor stub
	}

	public Categoria(int codigo, String nombre, boolean visible, int categoria_superior) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.visible = visible;
		this.categoria_superior = categoria_superior;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public int getCategoriaSuperior() {
		return categoria_superior;
	}
	
	public void setCategoriaSuperior(int categoria_superior) {
		this.categoria_superior = categoria_superior;
	}
	
}
