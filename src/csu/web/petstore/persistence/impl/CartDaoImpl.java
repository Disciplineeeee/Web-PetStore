package csu.web.petstore.persistence.impl;


import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.Item;
import csu.web.petstore.persistence.CartDao;
import csu.web.petstore.persistence.DBUtil;
import csu.web.petstore.persistence.ItemDao;
import csu.web.petstore.persistence.impl.ItemDaoImpl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartDaoImpl implements CartDao {
    private static final String GetCartList = "SELECT * FROM cart WHERE username = ?";

    private static final String GetCart = "SELECT * FROM cart WHERE username=? and itemId= ?";

    private static final String AddCart = "insert INTO cart(username,descn,itemId,productName,num,listprice,total_cost,productId) VALUES(?,?,?,?,?,?,?,?)" ;

    private static final String DeleteCart = "DELETE FROM cart WHERE username= ? and itemId= ?";

    private static final String UpdateCart = "UPDATE cart SET num = ? , total_cost = ? WHERE username=? and itemId= ?";

    @Override
    public  List<Cart> getCartList(String username) {
        List<Cart> cartList = new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(GetCartList);
            pStatement.setString(1,username);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Cart cart = new Cart();
                cart.setUsername(resultSet.getString(1));
                cart.setDesc(resultSet.getString(2));
                cart.setItemId(resultSet.getString(3));
                cart.setProductId(resultSet.getString(4));
                cart.setNum(resultSet.getInt(5));
                cart.setListprice(resultSet.getBigDecimal(6));
                cart.setTotal_cost(resultSet.getBigDecimal(7));
                cart.setProductid(resultSet.getString(8));
                cartList.add(cart);

            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cartList;
    }

    @Override
    public Cart getCart(String itemId, String username) {
        Cart cart = null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GetCart);
            pStatement.setString(1, username);
            pStatement.setString(2, itemId);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                cart = new Cart();
                cart.setUsername(resultSet.getString(1));
                cart.setDesc(resultSet.getString(2));
                cart.setItemId(resultSet.getString(3));
                cart.setProductId(resultSet.getString(4));
                cart.setNum(resultSet.getInt(5));
                cart.setListprice(resultSet.getBigDecimal(6));
                cart.setTotal_cost(resultSet.getBigDecimal(7));
                cart.setProductid(resultSet.getString(8));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cart;
    }

    @Override
    public void InsterToCart(String itemId, String descn, String username, String productId, BigDecimal listprice,BigDecimal totalcost,String producid) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(AddCart);
            pStatement.setString(1,username);
            pStatement.setString(2,descn);
            pStatement.setString(3,itemId);
            pStatement.setString(4,productId);
            pStatement.setInt(5,1);
            pStatement.setBigDecimal(6,listprice);
            pStatement.setBigDecimal(7,listprice);
            pStatement.setString(8,producid);
            pStatement.execute();

            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void DeleteCart(String itemId, String username) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DeleteCart);
            pStatement.setString(1,username);
            pStatement.setString(2,itemId);
            pStatement.execute();
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void UpdateCart(String itemId, String username, int num) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(UpdateCart);
            pStatement.setInt(1,num);
            pStatement.setString(3,username);
            pStatement.setString(4,itemId);

            ItemDao itemDao =new ItemDaoImpl();
            Item item = itemDao.getItem(itemId);
            BigDecimal li= item.getListPrice();

            BigDecimal listprice  = BigDecimal.valueOf((int)num).multiply(li);
            pStatement.setBigDecimal(2,listprice);
            pStatement.executeUpdate();

            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

 /*   public static void main(String[] args) {
        CartDao cartDao = new CartDaoImpl();
        cartDao.UpdateCart("EST-18","j2ee",2);
        System.out.println("success");
    }*/

}


//    private static final String GetCartList="SELECT * FROM ?_cart";
//    @Override
//    public List<Cart> getCartList(String username) {
//        List<Cart> cartList =new ArrayList<>();
//
//
//        try{
//                Connection connection = DBUtil.getConnectionCartAndOrder();
//                Statement statement = connection.createStatement();
//                ResultSet resultSet = statement.executeQuery(GetCartList);
//                while(resultSet.next()){
//                    Category category = new Category();
//                    Cart cart =new Cart();
//                    cart.setProductId(resultSet.getString(1));
//                    cart.setCategory(resultSet.getString(2));
//
//
//                    cartList.add(cart);
//
//                }
//                DBUtil.closeResultSet(resultSet);
//                DBUtil.closeStatement(statement);
//                DBUtil.closeConnection(connection);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//
//    @Override
//    public Cart getCartList(String productId) {
//        return null;
//    }




