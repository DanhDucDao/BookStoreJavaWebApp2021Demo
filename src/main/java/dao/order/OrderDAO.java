package dao.order;

import model.order.Order;

public interface OrderDAO {
	/**
	 * add a order to database
	 * 
	 * @param order order instance must have customerID, cartID, shipment, payment, orderInfo
	 * @return new created order
	 */
	Order addOrder(Order order);
	
	/**
	 * update status and info of an order
	 * 
	 * @param order order needs to update
	 * @return updated order
	 * @throws IllegalArgumentException if the id of the Order not found
	 * 		or some info not correct
	 */
	Order updateOrder(Order order);
	
	
	/**
	 * delete a WAIT_FOR_CONFIRM ORDER or a CANCEL order
	 * 
	 * @param order need to be delete
	 * @throws IllegalArgumentException if not found the order, or wrong information
	 */
	void deleteOrder(Order order);
	
	/**
	 * clear any resource
	 */
	void endSession();
}
