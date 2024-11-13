package csu.web.petstore.persistence.impl;

import csu.web.petstore.domain.Order;
import csu.web.petstore.persistence.DBUtil;
import csu.web.petstore.persistence.OrderDao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private static final String GET_ORDERLIST_BY_USERNAME= "SELECT" +
            "      BILLADDR1 AS billAddress1," +
            "      BILLADDR2 AS billAddress2," +
            "      BILLCITY," +
            "      BILLCOUNTRY," +
            "      BILLSTATE," +
            "      BILLTOFIRSTNAME," +
            "      BILLTOLASTNAME," +
            "      BILLZIP," +
            "      SHIPADDR1 AS shipAddress1," +
            "      SHIPADDR2 AS shipAddress2," +
            "      SHIPCITY," +
            "      SHIPCOUNTRY," +
            "      SHIPSTATE," +
            "      SHIPTOFIRSTNAME," +
            "      SHIPTOLASTNAME," +
            "      SHIPZIP," +
            "      CARDTYPE," +
            "      COURIER," +
            "      CREDITCARD," +
            "      EXPRDATE AS expiryDate," +
            "      LOCALE," +
            "      ORDERDATE," +
            "      ORDERS.ORDERID," +
            "      TOTALPRICE," +
            "      USERID AS username," +
            "      STATUS" +
            "    FROM ORDERS, ORDERSTATUS" +
            "    WHERE ORDERS.USERID =?" +
            "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID" +
            "    ORDER BY ORDERDATE";
    private static final String GETORDER="select" +
            "      BILLADDR1 AS billAddress1," +
            "      BILLADDR2 AS billAddress2," +
            "      BILLCITY," +
            "      BILLCOUNTRY," +
            "      BILLSTATE," +
            "      BILLTOFIRSTNAME," +
            "      BILLTOLASTNAME," +
            "      BILLZIP," +
            "      SHIPADDR1 AS shipAddress1," +
            "      SHIPADDR2 AS shipAddress2," +
            "      SHIPCITY," +
            "      SHIPCOUNTRY," +
            "      SHIPSTATE," +
            "      SHIPTOFIRSTNAME," +
            "      SHIPTOLASTNAME," +
            "      SHIPZIP," +
            "      CARDTYPE," +
            "      COURIER," +
            "      CREDITCARD," +
            "      EXPRDATE AS expiryDate," +
            "      LOCALE," +
            "      ORDERDATE," +
            "      ORDERS.ORDERID," +
            "      TOTALPRICE," +
            "      USERID AS username," +
            "      STATUS" +
            "    FROM ORDERS, ORDERSTATUS" +
            "    WHERE ORDERS.ORDERID = ?" +
            "      AND ORDERS.ORDERID = ORDERSTATUS.ORDERID";
    private static final String INSERTORDER="INSERT INTO ORDERS (ORDERID, USERID, ORDERDATE, SHIPADDR1, SHIPADDR2, SHIPCITY, SHIPSTATE," +
            "      SHIPZIP, SHIPCOUNTRY, BILLADDR1, BILLADDR2, BILLCITY, BILLSTATE, BILLZIP, BILLCOUNTRY," +
            "      COURIER, TOTALPRICE, BILLTOFIRSTNAME, BILLTOLASTNAME, SHIPTOFIRSTNAME, SHIPTOLASTNAME," +
            "      CREDITCARD, EXPRDATE, CARDTYPE, LOCALE)" +
            "    VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String INSERTORDERSTATUS="INSERT INTO ORDERSTATUS (ORDERID, LINENUM, TIMESTAMP, STATUS)" +
            "    VALUES (?,?,?,?)";

    public List<Order> getOrdersByUsername(String username){
        List<Order> orderList= new ArrayList<>();
        Order order=null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDERLIST_BY_USERNAME);
            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                order = new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));
                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));
                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));
                order.setShipZip(resultSet.getString(16));
                order.setCardType(resultSet.getString(17));
                order.setCourier(resultSet.getString(18));
                order.setCreditCard(resultSet.getString(19));
                order.setExpiryDate(resultSet.getString(20));
                order.setLocale(resultSet.getString(21));
                order.setOrderDate(resultSet.getTimestamp(22));
                order.setOrderId(resultSet.getInt(23));
                order.setTotalPrice(resultSet.getBigDecimal(24));
                order.setUsername(resultSet.getString(25));
                order.setStatus(resultSet.getString(26));
                orderList.add(order);
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }


    public Order getOrder(int orderId){
        Order order=null;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GETORDER);
            preparedStatement.setInt(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                order = new Order();
                order.setBillAddress1(resultSet.getString(1));
                order.setBillAddress2(resultSet.getString(2));
                order.setBillCity(resultSet.getString(3));
                order.setBillCountry(resultSet.getString(4));
                order.setBillState(resultSet.getString(5));
                order.setBillToFirstName(resultSet.getString(6));
                order.setBillToLastName(resultSet.getString(7));
                order.setBillZip(resultSet.getString(8));
                order.setShipAddress1(resultSet.getString(9));
                order.setShipAddress2(resultSet.getString(10));
                order.setShipCity(resultSet.getString(11));
                order.setShipCountry(resultSet.getString(12));
                order.setShipState(resultSet.getString(13));
                order.setShipToFirstName(resultSet.getString(14));
                order.setShipToLastName(resultSet.getString(15));
                order.setShipZip(resultSet.getString(16));
                order.setCardType(resultSet.getString(17));
                order.setCourier(resultSet.getString(18));
                order.setCreditCard(resultSet.getString(19));
                order.setExpiryDate(resultSet.getString(20));
                order.setLocale(resultSet.getString(21));
                order.setOrderDate(resultSet.getTimestamp(22));
                order.setOrderId(resultSet.getInt(23));
                order.setTotalPrice(resultSet.getBigDecimal(24));
                order.setUsername(resultSet.getString(25));
                order.setStatus(resultSet.getString(26));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return order;
    }


    public void insertOrder(Order order){
        int result;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTORDER);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setString(2,order.getUsername());
            System.out.println(new java.sql.Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setTimestamp(3,new java.sql.Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setString(4,order.getShipAddress1());
            preparedStatement.setString(5,order.getShipAddress2());
            preparedStatement.setString(6,order.getShipCity());
            preparedStatement.setString(7,order.getShipState());
            preparedStatement.setString(8,order.getShipZip());
            preparedStatement.setString(9,order.getShipCountry());
            preparedStatement.setString(10,order.getBillAddress1());
            preparedStatement.setString(11,order.getBillAddress2());
            preparedStatement.setString(12,order.getBillCity());
            preparedStatement.setString(13,order.getBillState());
            preparedStatement.setString(14,order.getBillZip());
            preparedStatement.setString(15,order.getBillCountry());
            preparedStatement.setString(16,order.getCourier());
            preparedStatement.setBigDecimal(17,order.getTotalPrice());
            preparedStatement.setString(18,order.getBillToFirstName());
            preparedStatement.setString(19,order.getBillToLastName());
            preparedStatement.setString(20,order.getShipToFirstName());
            preparedStatement.setString(21,order.getShipToLastName());
            preparedStatement.setString(22,order.getCreditCard());
            preparedStatement.setString(23,order.getExpiryDate());
            preparedStatement.setString(24,order.getCardType());
            preparedStatement.setString(25,order.getLocale());
            result = preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertOrderStatus(Order order){
        int result;
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERTORDERSTATUS);
            preparedStatement.setInt(1,order.getOrderId());
            preparedStatement.setInt(2,order.getLineItems().size());
            preparedStatement.setTimestamp(3,new java.sql.Timestamp(order.getOrderDate().getTime()));
            preparedStatement.setString(4,order.getStatus());
            result = preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static final String DeleteOrder = "DELETE FROM userorder WHERE id=?";

    @Override
    public void deleteOrder(int id) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(DeleteOrder);
            pStatement.setInt(1,id);
            pStatement.execute();

            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}














//public class OrderDaoImpl implements OrderDao {
//    private static final String AddOrder = "insert INTO userorder(username,descn,productid,itemId,num,total_cost,address,productname) VALUES(?,?,?,?,?,?,?,?)";
//
//    private static final String GetOrderList = "SELECT * FROM userorder WHERE username = ?";
//
//    private static final String DeleteOrder = "DELETE FROM userorder WHERE id=?";
//
//    @Override
//    public void addOrder(String username, String itemId, int num, BigDecimal listprice, String adress,String producrname,String descn,String productid) {
//        try{
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(AddOrder);
//            BigDecimal listprice1=listprice;
//            BigDecimal totalcost=null;
//            for(int i=0;i<num-1;i++){
//                totalcost=listprice.add(listprice1);
//            }
//
//            pStatement.setString(1,username);
//            pStatement.setString(2,descn);
//            pStatement.setString(3,productid);
//            pStatement.setString(4,itemId);
//            pStatement.setInt(5,num);
//            pStatement.setBigDecimal(6,listprice);
//            pStatement.setString(7,adress);
//            pStatement.setString(8,producrname);
//            pStatement.execute();
//
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public List<Order> getOrderList(String username) {
//        List<Order> orderList=new ArrayList<>();
//        try {
//            Connection connection = DBUtil.getConnection();
//
//            PreparedStatement pStatement = connection.prepareStatement(GetOrderList);
//            pStatement.setString(1,username);
//            ResultSet resultSet = pStatement.executeQuery();
//            while (resultSet.next()) {
//                Order order = new Order();
//                order.setId(resultSet.getInt(1));
//                order.setDescn(resultSet.getString(3));
//                order.setProductid(resultSet.getString(4));
//                order.setItemId(resultSet.getString(5));
//                order.setNum(resultSet.getInt(6));
//                order.setTotal_cost(resultSet.getBigDecimal(7));
//                order.setAddress(resultSet.getString(8));
//                order.setProductname(resultSet.getString(9));
//                orderList.add(order);
//
//            }
//            DBUtil.closeResultSet(resultSet);
//            DBUtil.closeStatement(pStatement);
//            DBUtil.closeConnection(connection);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return orderList;
//    }
//
//    @Override
//    public void deleteOrder(int id) {
//        try{
//            Connection connection = DBUtil.getConnection();
//            PreparedStatement pStatement = connection.prepareStatement(DeleteOrder);
//            pStatement.setInt(1,id);
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
//    public static void main(String[] args) {
//        OrderDao orderDao = new OrderDaoImpl();
//        BigDecimal bigDecimal =new BigDecimal(123);
//        orderDao.deleteOrder(0);
//        System.out.println("success");
//    }
//}

