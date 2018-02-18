package view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cad.ProductoCad;
import javabeans.Item;
import javabeans.Producto;

@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Cart() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("action") != null) {
			String action = request.getParameter("action");
			int webid = 0;
			Producto producto = null;
			HttpSession session = request.getSession();
			
			if (action.equals("order")) {
				webid = Integer.parseInt(request.getParameter("id"));
				if (session.getAttribute("cart") == null) {
					ArrayList<Item> cart = new ArrayList<>();
					
					producto = ProductoCad.consultarProducto(session.getAttribute("moneda").toString(), webid);
					
					cart.add(new Item(producto, 1));
					
					session.setAttribute("cart", cart);
				} else {
					ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
					int indice = yaExisteProducto(webid, cart);
					
					if (indice == -1) {
						producto = ProductoCad.consultarProducto(session.getAttribute("moneda").toString(), webid);						
						cart.add(new Item(producto, 1));
					} else {
						int cantidad = cart.get(indice).getCantidad() + 1;
						cart.get(indice).setCantidad(cantidad);
					}	
					
					session.setAttribute("cart", cart);
				}
			} else if (action.equals("delete")) {
				webid = Integer.parseInt(request.getParameter("id"));
				ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
				int indice = yaExisteProducto(webid, cart);
				
				cart.remove(indice);
				session.setAttribute("cart", cart);
			} else if (action.equals("finish")) {
				ArrayList<Item> cart = (ArrayList<Item>) session.getAttribute("cart");
				int indice = yaExisteProducto(webid, cart);
				
				cart.clear();
				session.setAttribute("cart", cart);
				response.setContentType("text/html;charset=UTF-8");
				request.getRequestDispatcher("Inicio").forward(request, response);
			}
		}
		
		response.setContentType("text/html;charset=UTF-8");
		request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request, response);
	}

	private int yaExisteProducto(int p_webid, ArrayList<Item> item) {
		for (int i = 0; i < item.size(); i++) {
			if (item.get(i).getProducto().getWebid() == p_webid) {
				return i;
			}
		}
		
		return -1;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
