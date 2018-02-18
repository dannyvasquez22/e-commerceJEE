<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="java.text.SimpleDateFormat" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.UUID" %>
<%@page import="control.Payu" %>

<c:set var="total" value="${total + (total * 0.19)}" scope="session"/>
<c:set var="moneda" value="${moneda}" scope="session"/>

<%
	SimpleDateFormat formato = new SimpleDateFormat("ddMyyyyhhmmss");
	String referencia = formato.format(new Date()) + UUID.randomUUID();
	
	String signature = "apiKey" + "~" + "idCommerce" + "~" + referencia + "~" + pageContext.getAttribute("total") + "~" + pageContext.getAttribute("moneda");
%>

<form method="post" action="https://sandbox.checkout.payulatam.com/ppp-web-gateway-payu/">
  <input name="merchantId"    type="hidden"  value="508029"   >
  <input name="accountId"     type="hidden"  value="512321" >
  <input name="description"   type="hidden"  value="Test PAYU"  >
  <input name="referenceCode" type="hidden"  value="<%= referencia %>" >
  <input name="amount"        type="hidden"  value="<%= pageContext.getAttribute("total") %>"   >
  <input name="tax"           type="hidden"  value=<fmt:formatNumber currencySymbol="" value="${total * 0.19}" type="currency" />  >
  <input name="taxReturnBase" type="hidden"  value="${0.19}" >
  <input name="currency"      type="hidden"  value="${moneda}" >
  <input name="signature"     type="hidden"  value="7ee7cf808ce6a39b17481c54f2c57acc"  >
  <input name="test"          type="hidden"  value="1" >
  <input name="buyerEmail"    type="hidden"  value="test@test.com" >
  <input name="responseUrl"    type="hidden"  value="http://www.test.com/response" >
  <input name="responseUrlDev"    type="hidden"  value="<%= "http://localhost:8080" + request.getContextPath() + "/Success" %>" >
  <input name="confirmationUrl"    type="hidden"  value="http://www.test.com/confirmation" >
  <input name="confirmationUrlDev"    type="hidden"  value="<%= "http://localhost:8080" + request.getContextPath() + "/Confirmation" %>" >
  <input name="Submit"        type="submit"  value="Enviar" alt="Pagar con PayU" title="Pagar con PayU">
</form>