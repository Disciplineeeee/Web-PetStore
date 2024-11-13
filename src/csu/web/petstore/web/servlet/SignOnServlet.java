package csu.web.petstore.web.servlet;

import csu.web.petstore.domain.Account;
import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;
import csu.web.petstore.domain.Product;
import csu.web.petstore.service.AccountService;
import csu.web.petstore.service.CartService;
import csu.web.petstore.service.CatalogService;
import csu.web.petstore.service.UserLogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";

    private String username;
    private String password;

    private String checkcode;

    private Cart cart;
    private List<CartItem> cartItemList;

    private String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");

        this.checkcode = req.getParameter("checkcode");

        //校验用户输入的正确性
        if(!validate()){
            req.setAttribute("signOnMsg", this.msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }else{
            HttpSession session1 = req.getSession();
            String checkcode_session = (String) session1.getAttribute("checkcode_session");
            session1.removeAttribute("checkcode_session");

            if (checkcode_session != null && checkcode_session.equalsIgnoreCase(checkcode)) {//验证码正确

                AccountService accountService = new AccountService();
                Account loginAccount = accountService.getAccount(username, password);
                if(loginAccount == null){
                    this.msg = "用户名或密码错误";
                    req.setAttribute("signOnMsg", this.msg);
                    req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
                }
                else {
                    loginAccount.setPassword(null);
                    HttpSession session = req.getSession();
                    session.setAttribute("loginAccount" , loginAccount);

                    UserLogService userLogService =new UserLogService();
                    String viewpage="login";
                    userLogService.insertUserLog(loginAccount,viewpage);


                    //根据账号更新购物车
                    CartService cartService=new CartService();
                    cartItemList=cartService.getCartItems(loginAccount);
                    cart=new Cart();
                    CatalogService catalogService = new CatalogService();

                    if (cartItemList!=null){
                        for (CartItem cartItem:cartItemList) {
                            boolean isInstock = catalogService.isItemInStock(cartItem.getItem().getItemId());
                            cart.addItemFromSQL(cartItem,isInstock);
                        }

                    }
                    session.setAttribute("cart",cart);

                    if(loginAccount.isListOption()){
                        //CatalogService catalogService = new CatalogService();
                        List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                        session.setAttribute("myList", myList);
                    }

                    resp.sendRedirect("mainForm");
                }
            }
            else { //验证码错误
                this.msg = "验证码错误";
                req.setAttribute("signOnMsg", this.msg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
            }
        }
    }

    private boolean validate(){ //输入是否为空
        if(this.username == null || this.username.equals("")){
            this.msg = "用户名不能为空";
            return false;
        }
        if(this.password == null || this.password.equals("")){
            this.msg = "密码不能为空";
            return false;
        }
        if(this.checkcode == null || this.checkcode.equals("")){
            this.msg = "验证码不能为空";
            return false;
        }
        return true;
    }
}