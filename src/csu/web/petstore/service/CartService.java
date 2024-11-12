package csu.web.petstore.service;

import csu.web.petstore.domain.Cart;
import csu.web.petstore.persistence.CartDao;
import csu.web.petstore.persistence.impl.CartDaoImpl;

import java.math.BigDecimal;
import java.util.List;

public class CartService  {
    private CartDao cartDao;

    public CartService(){
        cartDao = new CartDaoImpl();
    }

    public List<Cart> getCartList(String username){
        return cartDao.getCartList(username);
    }

    public Cart getCart(String itemId,String username){
        return cartDao.getCart(itemId,username);
    }

    public void addCart(String itemId,String desc, String username, String productId,BigDecimal listprice ,BigDecimal totalcost,String productid){
        cartDao.InsterToCart(itemId,desc,username,productId,listprice,totalcost,productid);
    }
    public void DeleteCart(String itemId, String username){
        cartDao.DeleteCart(itemId,username);
    }



}

