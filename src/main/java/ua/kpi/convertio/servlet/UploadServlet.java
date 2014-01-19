package ua.kpi.convertio.servlet;

import ua.kpi.convertio.Converter;
import ua.kpi.convertio.FileMessageDigest;
import ua.kpi.convertio.db.MongoHashDB;
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
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@MultipartConfig
public class UploadServlet extends javax.servlet.http.HttpServlet {
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private MongoHashDB db;

    public UploadServlet() throws UnknownHostException {
        db = new MongoHashDB();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
        final InputStream fileContent = filePart.getInputStream();
        fileContent.mark(10 * 10_000_000);
        final String md5;
        try {
            md5 = FileMessageDigest.getDigest(fileContent);
        } catch (NoSuchAlgorithmException e) {
            log("Hashing error");
            response.sendError(500);
            return;
        }

        String path = null;
        try {
            path = db.read(md5);
        } catch (InterruptedException e) {
            log("DB error", e);
        }
        if (path == null) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        fileContent.reset();
                        File src = new File(md5);
                        log("AsyncConverting...");
                        FileUtils.copyInputStreamToFile(fileContent, src);
                        Converter.convert(src);
                        src.delete();
                        db.write(md5, md5 + ".mp3");
                    } catch (IOException | EncoderException | InterruptedException e) {
                        log("oops", e);
                    }
                }
            });
        }
        response.sendRedirect("/download?id=" + md5);
    }
}
