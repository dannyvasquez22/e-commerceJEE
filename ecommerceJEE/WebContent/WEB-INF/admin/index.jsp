<%@page import="javabeans.Categoria"%>
<%@page import="cad.CategoriaCad"%>
<%@page import="javabeans.Marca"%>
<%@page import="cad.MarcaCad"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Gestion de Productos |  Crea e-Commerce JAVA EE con pagos Online Paypal y Payu</title>
    <%@include file="../../WEB-INF/css.jsp" %>
</head><!--/head-->

<body>
	<%@include file="../../WEB-INF/header.jsp" %>	
    <section>
		<div class="container">
			<div class="row">
				<div class="col-sm-3">					
				</div>
				
				<div class="col-sm-10 clearfix">
				${mensaje}
					<h3>Gestionar Producto</h3>
					<form action="ControlProducto" enctype="multipart/form-data" method="POST">
						<div class="form-one">
							Nombre: <br/> 
							<input type="text" name="nombre" placeholder="Nombre Producto" value="" required /> <hr/>
							Precio(MXN): <br/> 
							<input type="number" name="precio" placeholder="Precio" value="0" min="0" /> <br/>
							Precio promocion(MXN): <br/> 
							<input type="number" name="precio_nuevo" placeholder="Precio" value="0" min="0" /> <hr/>
							Precio(USD): <br/> 
							<input type="number" name="precio_usd" placeholder="Precio" value="0" min="0" /> <br/>
							Precio promocion(USD): <br/> 
							<input type="number" name="precio_nuevo_usd" placeholder="Precio" value="0" min="0" /> <hr/>
							Precio(COP): <br/> 
							<input type="number" name="precio_cop" placeholder="Precio" value="0" min="0" /> <br/>
							Precio promocion(COP): <br/> 
							<input type="number" name="precio_nuevo_cop" placeholder="Precio" value="0" min="0" /> <hr/>
							Precio(PEN): <br/> 
							<input type="number" name="precio_pen" placeholder="Precio" value="0" min="0" /> <br/>
							Precio promocion(PEN): <br/> 
							<input type="number" name="precio_nuevo_pen" placeholder="Precio" value="0" min="0" /> <hr/>
							Stock: <br/>
							<input type="number" name="cantidad" placeholder="Cantidad" value="1" min="1" /> <br/>
							Marca: <br/>
							<select name="marca">
								<option>Seleccionar Marca</option>
								<% for(Marca m: MarcaCad.listarTodoMarca()) { %>
								<option value="<%= m.getCodigo() %>"><%= m.getNombre() %></option>
								<% } %>
							</select> <br/>
							Categoria: <br/>
							<select name="categoria">
								<option>Seleccionar categoria</option>
								<% for(Categoria c: CategoriaCad.listarTodoCategoria()) { %>
								<option value="<%= c.getCodigo() %>"><%= c.getNombre() %></option>
								<% } %>
							</select> <br/>
							Descripcion: <br/>
							<textarea rows="4" cols="20" name="descripcion" placeholder="Descripcion" required>
							</textarea> <br/>
							Nuevo?: <br/>
							<input type="checkbox" name="nuevo" value="ON" checked="checked" /> <br/>
							Recomendado?: <br/> 
							<input type="checkbox" name="recomendado" value="ON" /> <br/>
							Visible?: <br/> 
							<input type="checkbox" name="visible" value="ON" checked="checked" /> <hr/>
							<br/>
							<input type="file" name="imagen" value="Seleccionar una imagen" required /> <hr/>
							
							<input class="btn btn-success" type="submit" name="accion" value="Registrar" ></input>
							<input class="btn btn-default" type="submit" name="accion" value="Consultar" ></input>
							<input class="btn btn-warning" type="submit" name="accion" value="Actualizar" ></input>
							<input class="btn btn-danger" type="submit" name="accion" value="Borrar" ></input>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	
	<%@include file="../../WEB-INF/footer.jsp" %>
		  
    <%@include file="../../WEB-INF/js.jsp" %>
</body>
</html>
