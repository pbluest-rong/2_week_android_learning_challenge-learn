import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import model.bean.Image;
import model.bean.Product;
import model.service.ImageService;
import model.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/api-admin-product")
public class ProductAPIs extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        Gson gson = new Gson();
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        if (action != null && action.equals("getImage")) {
            String productId = req.getParameter("productId");
            List<Image> images = ImageService.getInstance().getImagesForProduct(productId);
            String jsonResponse = gson.toJson(images);
            resp.getWriter().write(jsonResponse);
        } else {
            List<Product> products = ProductService.getInstance().getAll();
            String jsonResponse = gson.toJson(products);
            resp.getWriter().write(jsonResponse);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
