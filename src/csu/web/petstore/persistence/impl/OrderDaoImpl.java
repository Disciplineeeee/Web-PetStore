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
    private static final String AddOrder = "insert INTO userorder(username,descn,productid,itemId,num,total_cost,address,productname) VALUES(?,?,?,?,?,?,?,?)";

    private static final String GetOrderList = "SELECT * FROM userorder WHERE username = ?";

    private static final String DeleteOrder = "DELETE FROM userorder WHERE id=?";

    @Override
    public void addOrder(String username, String itemId, int num, BigDecimal listprice, String adress,String producrname,String descn,String productid) {
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(AddOrder);
            BigDecimal listprice1=listprice;
            BigDecimal totalcost=null;
            for(int i=0;i<num-1;i++){
                totalcost=listprice.add(listprice1);
            }

            pStatement.setString(1,username);
            pStatement.setString(2,descn);
            pStatement.setString(3,productid);
            pStatement.setString(4,itemId);
            pStatement.setInt(5,num);
            pStatement.setBigDecimal(6,listprice);
            pStatement.setString(7,adress);
            pStatement.setString(8,producrname);
            pStatement.execute();

            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getOrderList(String username) {
        List<Order> orderList=new ArrayList<>();
        try {
            Connection connection = DBUtil.getConnection();

            PreparedStatement pStatement = connection.prepareStatement(GetOrderList);
            pStatement.setString(1,username);
            ResultSet resultSet = pStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setDescn(resultSet.getString(3));
                order.setProductid(resultSet.getString(4));
                order.setItemId(resultSet.getString(5));
                order.setNum(resultSet.getInt(6));
                order.setTotal_cost(resultSet.getBigDecimal(7));
                order.setAddress(resultSet.getString(8));
                order.setProductname(resultSet.getString(9));
                orderList.add(order);

            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closeStatement(pStatement);
            DBUtil.closeConnection(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;
    }

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

    public static void main(String[] args) {
        OrderDao orderDao = new OrderDaoImpl();
        BigDecimal bigDecimal =new BigDecimal(123);
        orderDao.deleteOrder(0);
        System.out.println("success");
    }
}

