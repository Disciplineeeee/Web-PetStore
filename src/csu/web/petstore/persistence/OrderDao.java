package csu.web.petstore.persistence;

import csu.web.petstore.domain.Order;
import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {
//    void addOrder(String username, String itemId, int num, BigDecimal total_cost,String adress,String productname,String descn,String peoductid);
//
//    List<Order> getOrderList(String username);
//
//    void deleteOrder(int id);



    List<Order> getOrdersByUsername(String username);

    Order getOrder(int orderId);

    void insertOrder(Order order);

    void insertOrderStatus(Order order);

    void deleteOrder(int id);
}

