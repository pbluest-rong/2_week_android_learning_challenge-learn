package model.service;

import model.bean.Image;
import model.dao.ImageDAO;
import vn.fit.nlu.gk.db.JDBIConnector;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ImageService {
    public static ImageService instance;

    public static ImageService getInstance() {
        if (instance == null) instance = new ImageService();
        return instance;
    }

    public static String getLogoImagePath() {
        Optional<String> path = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("select path from image where name = 'Logo'")
                        .mapTo(String.class)  // Sử dụng mapTo thay vì mapToBean vì bạn đang truy vấn một cột duy nhất
                        .findFirst()
        );
        return path.orElse("");  // Sử dụng orElse để trả về giá trị mặc định nếu Optional rỗng
    }

    public static String getBackgroundImagePath() {
        Optional<String> path = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("select path from image where name = 'Background'")
                        .mapTo(String.class)  // Sử dụng mapTo thay vì mapToBean vì bạn đang truy vấn một cột duy nhất
                        .findFirst()
        );
        return path.orElse("");  // Sử dụng orElse để trả về giá trị mặc định nếu Optional rỗng
    }

    public static boolean isExternalPath(String path) {
        try {
            URL url = new URL(path);
            // Kiểm tra xem đường dẫn có sử dụng giao thức HTTP hoặc HTTPS không
            return "http".equals(url.getProtocol()) || "https".equals(url.getProtocol());
        } catch (MalformedURLException e) {
            // Nếu có lỗi khi tạo URL, có thể coi đường dẫn không phải là từ bên ngoài
            return false;
        }
    }

    public static String pathImageOnly(int productId) {
        return ImageDAO.pathImage(productId);
    }

    public static void deleteProductImage(String productId) {
        JDBIConnector.get().useHandle(handle ->
                handle.createUpdate("DELETE FROM image WHERE productId=?")
                        .bind(0, productId)
                        .execute()
        );
    }

    public static List<Image> getImagesForProduct(String productId) {
        List<Image> imageList = JDBIConnector.get().withHandle(handle ->
                handle.createQuery("SELECT * from image where productId = :productId ")
                        .bind("productId", productId)
                        .mapToBean(Image.class)
                        .stream().collect(Collectors.toList()));

        return imageList;
    }

    public static void deleteImageInServer(ServletContext servletContext, String imagePath) {
        //vì path image bắt đầu: images/.....
        String absolutePath = servletContext.getRealPath(new File(servletContext.getContextPath()).getParent());

        try {
            Path filePath = Path.of(absolutePath, imagePath);
            System.out.println(filePath.toString());
            try {
                // Kiểm tra xem tệp tin tồn tại trước khi xóa
                if (Files.exists(filePath)) {
                    Files.delete(filePath);
                    System.out.println("deleted file: " + filePath.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (
                InvalidPathException e) {

        }
    }

    //reference: ChatGPT - vì muốn lưu xuống  file multiple xuống local thui ... nên... write được thì ok :)))
    private static String getSubmittedFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "unknown";
    }

    // Phương thức để lấy phần mở rộng của file
    private static String getFileExtension(String fileName) {
        String extension = "";
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0 && lastDotIndex < fileName.length() - 1) {
            extension = fileName.substring(lastDotIndex);
        }
        return extension;
    }

    public static List<String> writeProductImagesFromClient(String productName, HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws ServletException, IOException {
        List<String> uploadedFiles = new ArrayList<>();

        String relativePath = "images/products"; // Đường dẫn tới thư mục images trong ứng dụng
        String absolutePath = servletContext.getRealPath(relativePath);

        // Tạo thư mục nếu chưa tồn tại
        Files.createDirectories(Path.of(absolutePath));
        int count = 0;
        for (Part part : req.getParts()) {
            String fileName = getSubmittedFileName(part);
            if (!(fileName.endsWith("unknown"))) {
                count++;
                String newFileName = count + "_"
                        + (productName.replaceAll("\\s", ""))
                        + getFileExtension(fileName);
                String filePath = Path.of(absolutePath, newFileName).toString();
                System.out.println("filePath: " + filePath);
                part.write(filePath);
                uploadedFiles.add("images/products/" + newFileName);
            }
        }
        return uploadedFiles;
    }

    public static String writeBannerTipImagesFromClient(Part part, HttpServletRequest req, HttpServletResponse resp, ServletContext servletContext) throws ServletException, IOException {
        String uploadedFile = null;

        String relativePath = "images/banner_tip"; // Đường dẫn tới thư mục images trong ứng dụng
        String absolutePath = servletContext.getRealPath(relativePath);

        // Tạo thư mục nếu chưa tồn tại
        Files.createDirectories(Path.of(absolutePath));

        String fileName = getSubmittedFileName(part);
        if (!(fileName.endsWith("unknown"))) {
            String newFileName = (fileName.replaceAll("\\s", ""));

            File file = new File("newFileName");

            int count = 0;
            while (file.exists()) {
                newFileName = count + newFileName;
            }
            String filePath = Path.of(absolutePath, newFileName).toString();
            part.write(filePath);
            System.out.println("filePath: " + filePath);

            uploadedFile = ("images/banner_tip/" + newFileName);
        }
        return uploadedFile;
    }
}
