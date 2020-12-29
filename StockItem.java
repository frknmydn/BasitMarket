package SortedCollections;

import javax.xml.namespace.QName;

public class StockItem implements Comparable<StockItem>{

	private final String name;
	private double price;
	private int quantityInStock=0;
	private int reserved=0;
	
	
	
	public StockItem(String name, double price) {
		super();
		this.name = name;
		this.price = price;
		this.quantityInStock =0; 
	}
	
	
	
	



	public StockItem(String name, double price, int quantityStock) {
		super();
		this.name = name;
		this.price = price;
		this.quantityInStock = quantityStock;
	}







	public double getPrice() {
		return price;
	}





	public void setPrice(double price) {
		if(price>0.0) {
			this.price = price;
		}
		
	}





	public int quantityInStock() {
		return quantityInStock;
	}





	public void setQuantityStock(int quantityStock) {
		this.quantityInStock = quantityStock;
	}





	public String getName() {
		return name;
	}

	public int availableQuantity() {
		return quantityInStock- reserved;
	}




	@Override
	public int compareTo(StockItem o) {
		
		
		if(this==o) {
			return 0;
		}
		
		if(o!=null) {
			return this.name.compareTo(o.getName());
		}
		
		throw new NullPointerException();
	}
	
	
	
	
	public void adjustStock(int quantity) {
		int newQuantity= this.quantityInStock + quantity;
		if(newQuantity>=0) {
			this.quantityInStock = newQuantity;
		}
	}
	
	public int reserveStock(int quantity) {
		if(quantity>=availableQuantity()) {
			reserved+= quantity; //metodun içindeki quantity sayýsý kadar reserved'in içine ekleme yapýlýr
			return quantity;
		}
		return 0;
	}
	
	public int unreserveStock(int quantity) {
		if(quantity<=reserved) { //Eðer unreserved edilecek sayý reserved'den azsa
			reserved -= quantity; //metodun içindeki quantity sayýsý kadar unreserved yapýlacak. 
			return quantity;
		}
		return 0;
	}	
	
	public int finaliseStock(int quantity) {
		if(quantity<=reserved) {
			quantityInStock -= quantity;
			reserved-=quantity;
			return quantity;
		}
		return 0;
	}





	@Override
	public boolean equals(Object obj) {
		
		if(obj==this) {
			return true;
		}
		
		if((obj==null) || obj.getClass()!=this.getClass()) {
			return false;
		}
		
		String objName = ((StockItem) obj).getName();
		return this.name.equals(objName);
	}





	@Override
	public int hashCode() {
		return this.name.hashCode()+31;
		
	}





	@Override
	public String toString() {
		return this.name + " : deðeri " + this.price + ". reserve sayýsý: " + this.reserved;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}


