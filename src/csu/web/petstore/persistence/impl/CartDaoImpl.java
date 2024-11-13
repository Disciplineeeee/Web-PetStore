package csu.web.petstore.persistence.impl;


import csu.web.petstore.domain.Cart;
import csu.web.petstore.domain.CartItem;
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
    private static final String GET_CART_LIST_BY_USERNAME="SELECT itemId,quantity FROM cart WHERE username = ?";//查看item具体
    private static final String INSERT_CART="INSERT INTO cart(username,itemId,quantity) VALUES (?,?,?)";//商品到具体表
    private static final String UPDATE_CART_BY_USERNAME_ITEMID="UPDATE cart SET quantity = ? WHERE username = ? AND itemId=?";//商品到具体表
    private static final String DELETE_CART_BY_USERNAME_ITEMID="DELETE FROM cart WHERE username = ? AND itemId=?";//商品到具体表
    private static final String DELETE_CART_ALL="DELETE FROM cart WHERE username = ? ";//商品到具体表
    @Override
    public int insertCart(String username, String itemId,int quantity){
        int result = 0;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CART);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,itemId);
            preparedStatement.setInt(3,quantity);
            //占位符类型一定要对应
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int deleteCart(String username, String itemId){//可以直接用整形数据传进删除
        int result = 0;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_BY_USERNAME_ITEMID);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,itemId);
            //占位符类型一定要对应
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public int deleteCartAll(String username){//可以直接用整形数据传进删除
        int result = 0;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CART_ALL);
            preparedStatement.setString(1,username);
            //占位符类型一定要对应
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public  int  updateCart(String username, String itemId,int quantity){
        int result = 0;
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CART_BY_USERNAME_ITEMID);
            preparedStatement.setInt(1,quantity);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,itemId);
            //占位符类型一定要对应
            result = preparedStatement.executeUpdate();
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public List<CartItem> selectCart(String username){
        int result = 0;
        Connection connection = null;
        List<CartItem> cartItemList = new ArrayList<>();
        try {
            connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CART_LIST_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                CartItem cartItem =new CartItem();
                String itemId =resultSet.getString(1);
                Item item = new Item();
                item.setItemId(itemId);
                cartItem.setQuantity(resultSet.getInt(2));
                cartItem.setItem(item);
                cartItemList.add(cartItem);
            }
            DBUtil.closeResultSet(resultSet);//关闭结果集
            DBUtil.closeStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cartItemList;
    }
}






//public class CartDaoImpl implements CartDao {
//    private static final String GetCartList = "SELECT * FROM cart WHERE username = ?";
//
//    private static final String GetCart = "SELECT * FROM cart WHERE username=? and itemId= ?";
//
//    private static final String AddCart = "insert INTO cart(username,descn,itemId,productName,num,listprice,total_cost,productId) VALUES(?,?,?,?,?,?,?,?)" ;
//
//    private static final String DeleteCart = "DELETE FROM cart WHERE username= ? and itemId= ?";
//
//    private static final String UpdateCart = "UPDATE cart SET num = ? , total_cost = ? WHERE username=? and itemId= ?";
//
//    @Override
//    public  List<Cart> getCartList(String username) {
//        List<Cart> cartList = new ArrayList<>();
//        try {
//            Connection connection = DBUtil.getConnection();
//
//            PreparedStatement pStatement = connection.prepareStatement(GetCartList);
//            pStatement.setString(1,username);
//            ResultSet resultSet = pStatement.executeQuery();
//            while (resultSet.next()) {
//                Cart cart = new Cart();
//                cart.setUsername(resultSet.getString(1));
//                cart.setDesc(resultSet.getString(2));
//                cart.setItemId(resultSet.getString(3));
//                cart.setProductId(resultSet.getString(4));
//                cart.setNum(resultSet.getInt(5));
//                cart.setListprice(resultSet.getBigDecimal(6));
//                cart.setTotal_cost(resultSet.getBigDecimal(7));
//                cart.setProductid(resultSet.getString(8));
//                cartList.add(cart);
//
//            }
//            DBUtil.closeResultSet(resultSet);
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return cartList;
//    }
//
//    @Override
//    public Cart getCart(String itemId, String username) {
//        Cart cart = null;
//        try {
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(GetCart);
//            pStatement.setString(1, username);
//            pStatement.setString(2, itemId);
//            ResultSet resultSet = pStatement.executeQuery();
//            while (resultSet.next()) {
//                cart = new Cart();
//                cart.setUsername(resultSet.getString(1));
//                cart.setDesc(resultSet.getString(2));
//                cart.setItemId(resultSet.getString(3));
//                cart.setProductId(resultSet.getString(4));
//                cart.setNum(resultSet.getInt(5));
//                cart.setListprice(resultSet.getBigDecimal(6));
//                cart.setTotal_cost(resultSet.getBigDecimal(7));
//                cart.setProductid(resultSet.getString(8));
//            }
//            DBUtil.closeResultSet(resultSet);
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cart;
//    }
//
//    @Override
//    public void InsterToCart(String itemId, String descn, String username, String productId, BigDecimal listprice,BigDecimal totalcost,String producid) {
//        try{
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(AddCart);
//            pStatement.setString(1,username);
//            pStatement.setString(2,descn);
//            pStatement.setString(3,itemId);
//            pStatement.setString(4,productId);
//            pStatement.setInt(5,1);
//            pStatement.setBigDecimal(6,listprice);
//            pStatement.setBigDecimal(7,listprice);
//            pStatement.setString(8,producid);
//            pStatement.execute();
//
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    public void DeleteCart(String itemId, String username) {
//        try{
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(DeleteCart);
//            pStatement.setString(1,username);
//            pStatement.setString(2,itemId);
//            pStatement.execute();
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void UpdateCart(String itemId, String username, int num) {
//        try{
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(UpdateCart);
//            pStatement.setInt(1,num);
//            pStatement.setString(3,username);
//            pStatement.setString(4,itemId);
//
//            ItemDao itemDao =new ItemDaoImpl();
//            Item item = itemDao.getItem(itemId);
//            BigDecimal li= item.getListPrice();
//
//            BigDecimal listprice  = BigDecimal.valueOf((int)num).multiply(li);
//            pStatement.setBigDecimal(2,listprice);
//            pStatement.executeUpdate();
//
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
// /*   public static void main(String[] args) {
//        CartDao cartDao = new CartDaoImpl();
//        cartDao.UpdateCart("EST-18","j2ee",2);
//        System.out.println("success");
//    }*/
//
//}


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




