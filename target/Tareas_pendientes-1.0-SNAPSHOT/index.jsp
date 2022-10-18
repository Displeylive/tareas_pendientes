 <%@page import="java.util.ArrayList"%>
<%@page import="com.emergentes.Tareas"%>
<%
    if(session.getAttribute("lista") == null){
        ArrayList<Tareas> listaAux = new ArrayList<Tareas>();
        session.setAttribute("lista", listaAux);
        
    }
    
    ArrayList<Tareas> lista = (ArrayList<Tareas>)session.getAttribute("lista");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body bgcolor="#B0C4DE"><center>
        <h1>LISTADO DE TAREAS PENDIENTES</h1>
    
        <form action="ProcesaServlet" method="POST">
            <table>
                <tr>
                    <th>ID</th>
                    <th><input type="text" name="id" value=""> </th>
                </tr>
                <tr>
                    <td>TAREAS</td>
                    <td><input type="text" name="tarea" value=""> </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="NUEVA TAREAS "></td>
                </tr>
            </table>
        </form>
        </center>
        <a href="ProcesaServlet?op=vaciar">Vaciar lista tareas</a>
        <h2>lista de pendientes</h2>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Tarea</th>
                <th>Completado</th>
                <th></th>
            </tr>
            <tr>
            <%
            if(lista != null){
                for(Tareas p: lista){
            %>
                <td><%= p.getId() %></td>
                <td><%= p.getTarea() %></td>
                <td><a href="ProcesaServlet?op=check&id=<%= p.getId() %>"><input type="checkbox" name="" <%= (p.isCompletado())? "checked":"" %>/>check</a> </td>
                <td><a href="ProcesaServlet?op=eliminar&id=<%= p.getId() %>">eliminar</a></td>
            </tr>
            <%
                }    
            }
            %>
        </table>
    </body>
</html>