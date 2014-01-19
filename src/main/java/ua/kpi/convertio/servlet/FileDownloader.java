package ua.kpi.convertio.servlet;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

public class FileDownloader extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println();
        resp.setHeader("Content-Type", "application/octet-stream");
        resp.setHeader("Content-Disposition", "filename=\"" + req.getParameter("name") + "\"");
        IOUtils.copy(new FileInputStream(req.getParameter("name")), resp.getOutputStream());
    }
}
