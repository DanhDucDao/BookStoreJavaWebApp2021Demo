package dao.order;

import java.util.Date;

import javax.persistence.EntityManager;

import dao.DBUtil;
import model.customer.Customer;
import model.order.*;
import model.order.status.*;

public class OrderService implements OrderDAO{
	private EntityManager en;

	public OrderService() {
		en = DBUtil.getEntityManager();
	}
	
	
	/**
	 * use {@code CartService } implemented countTotal() method to count total
	 */
	@Override
	public Order addOrder(Order order) {
		
		CartService cartService = new CartService();
		try {
			en.getTransaction().begin();
			
			Cart cart = en.find(Cart.class, order.getCart().getId());
			Customer customer = en.find(Customer.class, order.getCustomer().getId());
			
			order.setCustomer(customer);
			order.setCart(cart);
			order.setCreateDate(new Date());		
			order.setStatus(OrderStatus.WAIT_FOR_COMFIRM);

			en.persist(order);
			
			cart.setAssociationTargetOrder(order);
			
			float amount = cartService.countTotal(cart);
			
			Payment payment = order.getPayment();
			payment.setAssociationTargetOrder(order);
			payment.setAmount(amount);
			en.persist(payment);
			
			OrderInfo info = order.getOrderInfo();
			info.setAssociationTargetOrder(order);
			en.persist(info);
			
			Shipment shipment = order.getShipment();
			shipment.setAssociationTargetOrder(order);
			en.persist(shipment.getAddress());
			en.persist(shipment);
			
			en.getTransaction().commit();
			return order;
		} finally {
			en.clear();
			cartService.endSession();
		}
		
	}

	

	@Override
	public Order updateOrder(Order order) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemeted yet");
	}


	@Override
	public void deleteOrder(Order order) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("not implemeted yet");
	}
	
	@Override
	public void endSession() {
		if(en != null && en.isOpen())
			en.close();
		
	}

}
