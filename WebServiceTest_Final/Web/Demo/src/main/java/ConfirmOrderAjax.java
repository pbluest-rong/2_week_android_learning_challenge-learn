import model.dao.OrderDAO;
import model.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/confirmOrder")
public class ConfirmOrderAjax extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String orderId = req.getParameter("orderId");
        if (action != null && orderId != null) {
            if (action.equals("deliveredOrder")) {
                OrderDAO.deliveredOrder(orderId);
            } else if (action.equals("confirm")) {
                OrderService.getInstance().confirmOrder(orderId);
            }
        }
    }
}
