package view;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Inicio() {
        super();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	HttpSession sesion = request.getSession();
		if (sesion.getAttribute("moneda") == null) {
			sesion.setAttribute("moneda", "MXN");
			sesion.setAttribute("nom_moneda", "$ Pesos Mexicanos");
		}
		
		if (request.getParameter("category") != null) {
			sesion.setAttribute("category", Integer.parseInt(request.getParameter("category")));
		} else if (request.getParameter("brand") != null) {
			sesion.setAttribute("brand", Integer.parseInt(request.getParameter("brand")));
		} else {
			sesion.setAttribute("category", 0);
			sesion.setAttribute("brand", 0);
		}
    	request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
