package csu.web.petstore.service;

import csu.web.petstore.domain.Item;
import csu.web.petstore.domain.LineItem;
import csu.web.petstore.domain.Order;
import csu.web.petstore.domain.Sequence;
import csu.web.petstore.persistence.ItemDao;
import csu.web.petstore.persistence.LineItemDao;
import csu.web.petstore.persistence.OrderDao;
import csu.web.petstore.persistence.SequenceDao;
import csu.web.petstore.persistence.impl.ItemDaoImpl;
import csu.web.petstore.persistence.impl.LineItemDaoImpl;
import csu.web.petstore.persistence.impl.OrderDaoImpl;
import csu.web.petstore.persistence.impl.SequenceDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderService {

    private ItemDao itemDao;
    private OrderDao orderDao;
    private SequenceDao sequenceDao;
    private LineItemDao lineItemDao;

    public OrderService(){
        itemDao = new ItemDaoImpl();
        orderDao = new OrderDaoImpl();
        sequenceDao =new SequenceDaoImpl();
        lineItemDao = new LineItemDaoImpl();
    }
    public void insertOrder(Order order) {
        order.setOrderId(getNextId("ordernum"));
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            String itemId = lineItem.getItemId();
            Integer increment = new Integer(lineItem.getQuantity());
            Map<String, Object> param = new HashMap<String, Object>(2);
            param.put("itemId", itemId);
            param.put("increment", increment);
            itemDao.updateInventoryQuantity(param);
        }

        orderDao.insertOrder(order);
        orderDao.insertOrderStatus(order);
        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            lineItem.setOrderId(order.getOrderId());
            lineItemDao.insertLineItem(lineItem);
        }
    }


    public Order getOrder(int orderId) {
        Order order = orderDao.getOrder(orderId);
        order.setLineItems(lineItemDao.getLineItemsByOrderId(orderId));

        for (int i = 0; i < order.getLineItems().size(); i++) {
            LineItem lineItem = (LineItem) order.getLineItems().get(i);
            Item item = itemDao.getItem(lineItem.getItemId());
            item.setQuantity(itemDao.getInventoryQuantity(lineItem.getItemId()));
            lineItem.setItem(item);
        }

        return order;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderDao.getOrdersByUsername(username);
    }

    public int getNextId(String name) {
        Sequence sequence = new Sequence(name, -1);
        System.out.println(sequence.getName());
        sequence = (Sequence) sequenceDao.getSequence(sequence);
        if (sequence == null) {
            throw new RuntimeException("Error: A null sequence was returned from the database (could not get next " + name
                    + " sequence).");
        }
        Sequence parameterObject = new Sequence(name, sequence.getNextId() + 1);
        sequenceDao.updateSequence(parameterObject);
        return sequence.getNextId();
    }
}
