package SortedCollections;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class StockList {
	private final Map<String, StockItem> list;

	public StockList() {
		
		this.list = new LinkedHashMap<>(); //linkedHashmap kullanýlmasýnýn sebebi listenin içinde sýralý þekilde gözükmesi için.
	}
	
	public int addStock(StockItem item) {
		if(item!=null) {
			//check if already have quantities of this item
			StockItem inStock = list.getOrDefault(item.getName(), item);
			//StockItem inStock = list.get(item.getName());
			//if tehere are already stocks on this item, adjust the quantity
			if(inStock!=item) {
				item.adjustStock(inStock.quantityInStock());
				
			}
			list.put(item.getName(), item);
			return item.quantityInStock();
		}
		return 0;
	}
	
	public int sellStock(String item, int quantity) {
		
		StockItem inStock = list.get(item);
		
		if((inStock!=null)&& (quantity>0)) {
			return inStock.finaliseStock(quantity);
			
		}
		return 0;
//		StockItem inStock = list.getOrDefault(item, null);
//		
//		if((inStock!=null) && (inStock.quantityInStock() >=quantity) && (quantity)>0) {
//			//instock objesinin boþ olmamasý kontrolü && satýlacak miktarýn toplam miktardan az olmasý kontrolü &&
//			//satýlacak miktarýn 0'dan küðçük olmamasý kontrolü olmamasý kontrolü.
//			inStock.adjustStock(-quantity);
//			return quantity;
//		}
//		return 0;
	}
	
	public int reserveStock(String item, int quantity) {
		StockItem inStock = list.get(item);
		
		if((inStock!=null) && (quantity>0)) {
			return inStock.reserveStock(quantity);
		}
		return 0;
	}
	
	public int unreserveStock(String item, int quantity) {
		StockItem inStock = list.get(item);
		
		if((inStock!=null ) && (quantity>0)) {
			return inStock.unreserveStock(quantity);
		}
		return 0;
	}
	
	
	public Map<String, Double> priceList(){
		Map<String, Double> prices = new LinkedHashMap<>();
		
		for(Map.Entry<String, StockItem> item : list.entrySet()) {
			prices.put(item.getKey(), item.getValue().getPrice());
		}
		return Collections.unmodifiableMap(prices);
	}
	
	
	public StockItem get(String key) {
		return list.get(key);
	}
	
	public Map<String,StockItem> Items(){
		return Collections.unmodifiableMap(list);
	}

	@Override
	public String toString() {
		
		String s = "";
		double totalCost = 0.0;
		
		for(Map.Entry<String, StockItem> item : list.entrySet()) {
			StockItem stockItem = item.getValue();
			
			double itemValue = stockItem.getPrice() * stockItem.quantityInStock();
			
			s=s + stockItem.getName() + " ürününün stoktaki deðeri " + stockItem.quantityInStock() + " ";
			s = s+ String.format("%.2f",itemValue)+"\n";
			totalCost += itemValue;
		}
		return s+ "Stoðun toplam ederi: " + totalCost;
	}
	
	
	
}
