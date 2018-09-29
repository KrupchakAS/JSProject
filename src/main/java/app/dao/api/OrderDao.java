package app.dao.api;

import app.dao.GenericDao;
import app.entity.Order;
import app.entity.Product;
import app.entity.User;

import java.util.List;

public interface OrderDao extends GenericDao<Order>{

    List<Order> getOrdersByUserId(Integer userId);

    List<User> getTop10Users();

    List<Product> getTop10Products();

    Integer getProceedsForMonth();

    Integer getProceedsForWeek();
}
