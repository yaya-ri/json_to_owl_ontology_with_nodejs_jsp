/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serverlet;

import JsonToOntology.BuildFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yaya_aye
 */
@WebServlet(urlPatterns = {"/response"})
public class response extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, FileNotFoundException {
        BuildFile myClass = new BuildFile();
        String button = request.getParameter("button");
        if ("button".equalsIgnoreCase(button)) {
            // try {
            //myClass.main();
            request.getRequestDispatcher("/result.html").forward(request, response);

        }else{
            System.out.println("gagal");
        }
        //Main.main();
        //request.getRequestDispatcher("/result.html").forward(request, response);

    }


}
