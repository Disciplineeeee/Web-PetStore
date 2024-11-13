package csu.web.petstore.persistence;

import csu.web.petstore.domain.LineItem;

import java.util.List;

public interface LineItemDao {
    List<LineItem> getLineItemsByOrderId(int orderId);

    void insertLineItem(LineItem lineItem);
}
