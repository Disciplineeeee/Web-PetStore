package csu.web.petstore.persistence;


import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;

import java.math.BigDecimal;
import java.util.List;


public interface CartDao {
    int insertCart(String username, String itemId,int quantity);
    int deleteCart(String username, String itemId);
    int deleteCartAll(String username);
    int updateCart(String username, String itemId,int quantity);
    List<CartItem> selectCart(String username);
}





//public interface CartDao {
//    List<Cart> getCartList(String username);
//
//    Cart getCart(String itemId,String username);
//
//    void InsterToCart(String itemId, String descn, String username, String productId, BigDecimal lastprice,BigDecimal totalcost,String productid);
//
//    void DeleteCart(String itemId,String username);
//
//    void UpdateCart(String itemId,String username,int num);
//}
