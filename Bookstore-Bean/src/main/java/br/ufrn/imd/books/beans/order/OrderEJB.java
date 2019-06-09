package br.ufrn.imd.books.beans.order;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.ufrn.imd.books.data.OrderDao;
import br.ufrn.imd.books.entity.OrderEntity;
import br.ufrn.imd.books.entity.OrderItemEntity;
import br.ufrn.imd.books.exceptions.BookstoreUnknownException;

/**
 * OrderEJB
 */
@Stateless(name = "OrderEJB")
public class OrderEJB implements OrderRemoteEJB, OrderLocalEJB {

  @EJB
  OrderDao orderDAO;

  @Override
  public OrderEntity createNew(OrderEntity order) throws BookstoreUnknownException {
    ArrayList<OrderItemEntity> items = new ArrayList<OrderItemEntity>();
    items.add(new OrderItemEntity());
    items.add(new OrderItemEntity());
    order.setItems(items);
    return orderDAO.addNew(order);
  }

}