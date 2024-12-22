package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;
import csu.web.petstore.service.CartService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartQuantitiesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        Account account = (Account) session.getAttribute("loginAccount");
        Iterator<CartItem> cartItems =cart.getAllCartItems();

        while(cartItems.hasNext()){
            CartItem cartItem= cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try{
                CartService cartService = new CartService();
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);
                cartService.updateCart(account,cartItem);
                if(quantity<1){
                    cartService.deleteCart(account,itemId);
                }
            }catch(Exception e){
                //忽略异常
            }
        }
        resp.sendRedirect("cartForm");
    }
}
