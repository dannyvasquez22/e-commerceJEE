package control;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cad.ProductoCad;
import javabeans.Producto;
import javabeans.ProductoMoneda;

/**
 * Servlet implementation class ControlProducto
 */
@WebServlet("/ControlProducto")
public class ControlProducto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControlProducto() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void recibirDatos(HttpServletRequest request)  {
		try {
			FileItemFactory fileFactory = new DiskFileItemFactory();
			
			ServletFileUpload servletUpload = new ServletFileUpload(fileFactory);
			
			List<?> items = servletUpload.parseRequest(request);
			String nombre = "";
			for (int i = 0; i < items.size(); i++) {
				FileItem item = (FileItem) items.get(i);
				
				if (!item.isFormField()) {
					String ruta = request.getServletContext().getRealPath("/") + "foto/";
					SimpleDateFormat sdf = new SimpleDateFormat("ddMyyyyhhmmss");
					String fecha = sdf.format(new Date());
					nombre = fecha + new Random().nextLong() + item.getName();
					String nuevo_nombre = ruta + nombre;
					File folder = new File(ruta);
					
					if (!folder.exists()) {
						folder.mkdirs();
					}
					
					File imagen = new File(nuevo_nombre);
					
					if (item.getContentType().contains("image" )) {
						item.write(imagen);
						request.setAttribute(item.getFieldName(), nombre);
					}
					
				} else {
					request.setAttribute(item.getFieldName(), item.getString());
				}
			}
		} catch(FileUploadException ex) {
			request.setAttribute("subida", false);
		} catch(Exception ex) {
			request.setAttribute("subida", false);
		}		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		recibirDatos(request);
		
		String url = request.getAttribute("imagen").toString();
		String nombre = request.getAttribute("nombre").toString();
		Float precio = Float.parseFloat(request.getAttribute("precio").toString());
		Float precio_nuevo = Float.parseFloat(request.getAttribute("precio_nuevo").toString());
		Float precio_cop = Float.parseFloat(request.getAttribute("precio_cop").toString());
		Float precio_nuevo_cop = Float.parseFloat(request.getAttribute("precio_nuevo_cop").toString());
		Float precio_usd = Float.parseFloat(request.getAttribute("precio_usd").toString());
		Float precio_nuevo_usd = Float.parseFloat(request.getAttribute("precio_nuevo_usd").toString());
		Float precio_pen = Float.parseFloat(request.getAttribute("precio_pen").toString());
		Float precio_nuevo_pen = Float.parseFloat(request.getAttribute("precio_nuevo_pen").toString());
		int cantidad = Integer.parseInt(request.getAttribute("cantidad").toString());
		int categoria = Integer.parseInt(request.getAttribute("categoria").toString());
		int marca = Integer.parseInt(request.getAttribute("marca").toString());
		String descripcion = request.getAttribute("descripcion").toString();
		
		boolean nuevo, visible, recomendado;
		try {
			nuevo = (request.getAttribute("nuevo").toString().equalsIgnoreCase("on")) ? true : false;
		} catch(Exception e) {
			nuevo = false;
		}
		
		try {
			visible = (request.getAttribute("visible").toString().equalsIgnoreCase("on"));
		} catch (Exception e) {
			visible = false;
		} 
		
		try {		
			recomendado = (request.getAttribute("recomendado").toString().equalsIgnoreCase("on"));
		} catch (Exception e) {
			recomendado = false;
		}
		String accion = request.getAttribute("accion").toString();
		
		Producto producto = new Producto();
		producto.setNombre(nombre);
		producto.setPrecio(precio);
		producto.setPrecio_nuevo(precio_nuevo);
		producto.setCodigo_categoria(categoria);
		producto.setCodigo_marca(marca);
		producto.setDescripcion(descripcion);
		producto.setImg(url);
		producto.setNuevo(nuevo);
		producto.setRecomendado(recomendado);
		producto.setStock(cantidad);
		producto.setVisible(visible);
		
		ProductoMoneda cop = new ProductoMoneda();
		cop.setMoneda("COP");
		cop.setPrecio(precio_cop);
		cop.setPrecio_nuevo(precio_nuevo_cop);
				
		ProductoMoneda usd = new ProductoMoneda();
		usd.setMoneda("USD");
		usd.setPrecio(precio_usd);
		usd.setPrecio_nuevo(precio_nuevo_usd);
		
		ProductoMoneda pen = new ProductoMoneda();
		pen.setMoneda("PEN");
		pen.setPrecio(precio_pen);
		pen.setPrecio_nuevo(precio_nuevo_pen);
		
		if (accion.equalsIgnoreCase("REGISTRAR")) {
			if (ProductoCad.registrarProducto(producto, cop, usd, pen)) {
				request.setAttribute("mensaje", "<p style='color:green'> Producto registrado con exito. </p>");
			} else {
				request.setAttribute("mensaje", "<p style='color:red'> Producto no registrado. </p>");				
			}			
		} else {
			request.setAttribute("mensaje", "<p style='color:red'> Acción desconocida. </p>");
		}
		request.getRequestDispatcher("admin").forward(request, response);
	}

}
