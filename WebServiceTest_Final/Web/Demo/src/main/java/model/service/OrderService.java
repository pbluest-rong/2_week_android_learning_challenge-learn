package model.service;

import model.bean.Order;
import model.bean.OrderDetail;
import model.dao.OrderDAO;

import java.util.List;

public class OrderService {
    private static OrderService instance;

    public static OrderService getInstance() {
        if (instance == null) instance = new OrderService();
        return instance;
    }


    public List<Order> getAllOrder() {
        return OrderDAO.getAllOrder();
    }

    public Order getOrderById(String orderId) {
        return OrderDAO.getOrderById(orderId);
    }

    public List<Order> getOrderByCustomerId(String customerId) {
        return OrderDAO.getOrderByCustomerId(customerId);
    }

    public List<Order> getOrderByCustomerNamePart(String customerNamePart) {
        return OrderDAO.getOrderByCustomerNamePart(customerNamePart);
    }

    public List<OrderDetail> getOrderDetailsByOrderId(String orderId) {
        return OrderDAO.getOrderDetailsByOrderId(orderId);
    }

    public void cancelOrder(String orderId) {
        OrderDAO.cancelOrder(orderId);
    }

    public void confirmOrder(String orderId) {
        OrderDAO.confirmOrder(orderId);
    }

    public List<Order> getWaitConfirmOrders() {
        return OrderDAO.getWaitConfirmOrders();
    }

    public List<Order> getDeliveringOrders() {
        return OrderDAO.getDeliveringOrders();
    }

    public List<Order> getCanceledOrders() {
        return OrderDAO.getCanceledOrders();
    }

    public List<Order> getSucccessfulOrders() {
        return OrderDAO.getSucccessfulOrders();
    }

    public double getExactlyTotalPriceNoShippingFee(String orderId) {
        return OrderDAO.getExactlyTotalPriceNoShippingFee(orderId);
    }
}
