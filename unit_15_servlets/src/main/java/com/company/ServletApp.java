package com.company;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@WebServlet(name = "servletApp", urlPatterns = {"/servletApp"})
public class ServletApp extends HttpServlet {
    private static final long serialVersionUID = -8948379822734246956L;

    private static final Map<String, String> ipAndHeaders = new ConcurrentHashMap<>();

    @Override
    public void init() {
        System.out.println(getServletName() + "initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String currentIP = req.getRemoteHost();
        String currentHead = req.getHeader("User-Agent");
        ipAndHeaders.put(currentIP, currentHead);

        PrintWriter responseBody = resp.getWriter();
        resp.setContentType("text/html");
        responseBody.println("<h1 align=center>List unique ip addresses and matching User-Agent http-header values");

        for (Map.Entry<String, String> entry : ipAndHeaders.entrySet()) {
            if (entry.getKey().equals(currentIP)) {
                responseBody.println("<p align=center> <b>" + entry.getKey() + " :: " + entry.getValue() + "</b> </p>");
            } else {
                responseBody.println("<p align=center>" + entry.getKey() + " :: " + entry.getValue() + "</p>");
            }
        }
    }

    @Override
    public void destroy() {
        System.out.println(getServletName() + " destroyed");
    }
}
