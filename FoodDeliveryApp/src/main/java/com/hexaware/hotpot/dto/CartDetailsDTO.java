package com.hexaware.hotpot.dto;


public class CartDetailsDTO {

//    private long cartMenuItemId;
//    private long cartId;
    private long menuItemId;
    private int quantity;
    private double price;
    private double total;
    private double individualTotal;
    private String itemName;


    public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public CartDetailsDTO() {

        // Default constructor
    }

    // Constructor with parameters

//    public CartDetailsDTO( long menuItemId, int quantity,double price) {
//
////        this.cartMenuItemId = cartMenuItemId;
////        this.cartId = cartId;
//        this.menuItemId = menuItemId;
//        this.quantity = quantity;
//        this.price=price;
//    }
    
    public CartDetailsDTO( long menuItemId,String itemName, int quantity,double price,double total,double individualTotal) {

//      this.cartMenuItemId = cartMenuItemId;
//      this.cartId = cartId;
      this.menuItemId = menuItemId;
      this.itemName=itemName;
      this.quantity = quantity;
      this.price=price;
      this.total=total;
      this.individualTotal=individualTotal;
     
  }



    public double getIndividualTotal() {
		return individualTotal;
	}

	public void setIndividualTotal(double individualTotal) {
		this.individualTotal = individualTotal;
	}

	public long getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

	@Override
	public String toString() {
		return "CartDetailsDTO [menuItemId=" + menuItemId + ", quantity=" + quantity + ", price=" + price + "]";
	}


}
