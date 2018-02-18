package javabeans;

public class Producto {

	private int webid;
	private String nombre;
	private float precio;
	private float precio_nuevo;
	private float precio_cop;
	private float precio_nuevo_cop;
	private float precio_usd;
	private float precio_nuevo_usd;
	private float precio_pen;
	private float precio_nuevo_pen;
	private boolean nuevo;
	private boolean recomendado;
	private boolean visible;
	private int codigo_marca;
	private int codigo_categoria;
	private int stock;
	private String descripcion;
	private String img;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}

	public int getWebid() {
		return webid;
	}

	public void setWebid(int webid) {
		this.webid = webid;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getPrecio_nuevo() {
		return precio_nuevo;
	}

	public void setPrecio_nuevo(float precio_nuevo) {
		this.precio_nuevo = precio_nuevo;
	}

	public float getPrecio_cop() {
		return precio_cop;
	}

	public void setPrecio_cop(float precio_cop) {
		this.precio_cop = precio_cop;
	}

	public float getPrecio_nuevo_cop() {
		return precio_nuevo_cop;
	}

	public void setPrecio_nuevo_cop(float precio_nuevo_cop) {
		this.precio_nuevo_cop = precio_nuevo_cop;
	}

	public float getPrecio_usd() {
		return precio_usd;
	}

	public void setPrecio_usd(float precio_usd) {
		this.precio_usd = precio_usd;
	}

	public float getPrecio_nuevo_usd() {
		return precio_nuevo_usd;
	}

	public void setPrecio_nuevo_usd(float precio_nuevo_usd) {
		this.precio_nuevo_usd = precio_nuevo_usd;
	}

	public float getPrecio_pen() {
		return precio_pen;
	}

	public void setPrecio_pen(float precio_pen) {
		this.precio_pen = precio_pen;
	}

	public float getPrecio_nuevo_pen() {
		return precio_nuevo_pen;
	}

	public void setPrecio_nuevo_pen(float precio_nuevo_pen) {
		this.precio_nuevo_pen = precio_nuevo_pen;
	}

	public boolean isNuevo() {
		return nuevo;
	}

	public void setNuevo(boolean nuevo) {
		this.nuevo = nuevo;
	}

	public boolean isRecomendado() {
		return recomendado;
	}

	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getCodigo_marca() {
		return codigo_marca;
	}

	public void setCodigo_marca(int codigo_marca) {
		this.codigo_marca = codigo_marca;
	}

	public int getCodigo_categoria() {
		return codigo_categoria;
	}

	public void setCodigo_categoria(int codigo_categoria) {
		this.codigo_categoria = codigo_categoria;
	}

	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
}
