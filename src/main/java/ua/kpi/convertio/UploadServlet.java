package ua.kpi.convertio;

import it.sauronsoftware.jave.EncoderException;
import org.apache.commons.io.FileUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;

@MultipartConfig
public class UploadServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
        log(description);
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        InputStream fileContent = filePart.getInputStream();
        fileContent.mark(10 * 10_000_000);
        String md5 = null;
        try {
            md5 = FileMessageDigest.getDigest(fileContent);
        } catch (NoSuchAlgorithmException e) {
            log("Hashing error");
            response.sendError(500);
            return;
        }
        fileContent.reset();
        File src = new File(md5);
        FileUtils.copyInputStreamToFile(fileContent, src);
        try {
            Converter.convert(src);
        } catch (EncoderException e) {
            log("Converter error", e);
        }
        src.delete();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().print("Hello World");
    }
}
