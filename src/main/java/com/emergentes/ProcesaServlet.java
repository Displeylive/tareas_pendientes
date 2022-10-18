
package com.emergentes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ProcesaServlet", urlPatterns = {"/ProcesaServlet"})
public class ProcesaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String op = request.getParameter("op");
        
        if(op.equals("vaciar")){
            //vaciar listado
            HttpSession ses = request.getSession();
            ses.invalidate();
            response.sendRedirect("index.jsp");
        }
        //eliminar uno de la lista
        if(op.equals("eliminar")){
            int pos = -1;
            int buscado = 0;
            int id = Integer.parseInt(request.getParameter("id"));
            
            HttpSession ses = request.getSession();
            ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("lista");
            
            for(Tareas p: lista){
                pos++;
                if(p.getId() == id){
                    buscado = pos; 
                }
            }
            lista.remove(buscado);
            response.sendRedirect("index.jsp");
        }
        
        if(op.equals("check")){
       
            int id = Integer.parseInt(request.getParameter("id"));
            
            HttpSession ses = request.getSession();
            ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("lista");
            
            for(Tareas p: lista){
       
                if(p.getId() == id){
                    if(p.isCompletado()){
                        p.setCompletado(false);
                    }else{
                        p.setCompletado(true);
                    }
                   
                }
            }
            response.sendRedirect("index.jsp");
            
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       int id = Integer.parseInt( request.getParameter("id"));
        String tarea = request.getParameter("tarea");
  
        Tareas t = new Tareas();
        
        t.setId(id);
        t.setTarea(tarea);
        t.setCompletado(false);
        
        HttpSession ses = request.getSession();
        
        ArrayList<Tareas> lista = (ArrayList<Tareas>)ses.getAttribute("lista");
        
        lista.add(t);
        
        response.sendRedirect("index.jsp");
    }

}
