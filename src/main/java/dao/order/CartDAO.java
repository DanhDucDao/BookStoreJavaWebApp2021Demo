package dao.order;

import model.order.Cart;
import model.order.LineItem;

public interface CartDAO {
	/**
	 * add a line item to cart, if already exist, update the quantity
	 * @param lineItem lineItem instance adding to cart, must not exist in database (not have an id)
	 * @param cart the target cart instance (the current cart)
	 * @return updated cart
	 */
	Cart addToCart(LineItem lineItem, Cart cart);
	
	/**
	 * 
	 * @param cart cart of a customer
	 * @param lineItem new line Item, must have bookId, and new quantity
	 * @return updated Cart
	 */
	Cart updateLineItem(Cart cart, LineItem lineItem) throws IllegalArgumentException;
	
	/**
	 * end session and clear resoure 
	 */
	
	/**
	 * 
	 * @param cart cart and id
	 * @param lineItem lineItem need to delete
	 * @return updated cart
	 * @throws IllegalArgumentException if not found cart, or line item
	 */
	Cart deleteItem(Cart cart, LineItem lineItem) throws IllegalArgumentException;
	
	/**
	 * count total money of a cart
	 * @param cart the cart
	 * @return money of the cart
	 */
	float countTotal(Cart cart);
	
	/**
	 * count subtotal money of a cart
	 * @param cart the cart
	 * @return money of the cart
	 */
	float calSubTotal(Cart cart);
	
	/**
	 * count discount money of a cart
	 * @param cart the cart
	 * @return money of the cart
	 */
	float calDiscount(Cart cart);
	
	void endSession();
}
