package csu.web.petstore.service;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;
import csu.web.petstore.domain.Item;
import csu.web.petstore.persistence.CartDao;
import csu.web.petstore.persistence.ItemDao;
import csu.web.petstore.persistence.impl.CartDaoImpl;
import csu.web.petstore.persistence.impl.ItemDaoImpl;

import java.math.BigDecimal;
import java.util.List;


public class CartService {
    private CartDao cartDao;
    private ItemDao itemDao;
    public CartService(){
        cartDao=new CartDaoImpl();
        itemDao=new ItemDaoImpl();
    }
    public void insertCart(Account account, CartItem cartItem){
        cartDao.insertCart(account.getUsername(),cartItem.getItem().getItemId(),cartItem.getQuantity());
    }
    public void updateCart(Account account,CartItem cartItem){
        cartDao.updateCart(account.getUsername(),cartItem.getItem().getItemId(),cartItem.getQuantity());
    }
    public void updateCart(Account account, Item item){
        cartDao.updateCart(account.getUsername(),item.getItemId(),item.getQuantity());
    }
    public void deleteCart(Account account,String itemId){
        cartDao.deleteCart(account.getUsername(),itemId);
    }
    public void deleteCartAll(Account account){
        cartDao.deleteCartAll(account.getUsername());
    }
    public List<CartItem> getCartItems(Account account){
        List<CartItem> CartList=cartDao.selectCart(account.getUsername());
        for (CartItem cartItem:CartList
        ) {
            String itemId=cartItem.getItem().getItemId();
            cartItem.setItem(itemDao.getItem(itemId));
        }
        return CartList;
    }

}








//public class CartService  {
//    private CartDao cartDao;
//
//    public CartService(){
//        cartDao = new CartDaoImpl();
//    }
//
//    public List<Cart> getCartList(String username){
//        return cartDao.getCartList(username);
//    }
//
//    public Cart getCart(String itemId,String username){
//        return cartDao.getCart(itemId,username);
//    }
//
//    public void addCart(String itemId,String desc, String username, String productId,BigDecimal listprice ,BigDecimal totalcost,String productid){
//        cartDao.InsterToCart(itemId,desc,username,productId,listprice,totalcost,productid);
//    }
//    public void DeleteCart(String itemId, String username){
//        cartDao.DeleteCart(itemId,username);
//    }
//
//
//
//}

