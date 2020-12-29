package SortedCollections;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Basket {
	private final String name;
	private final Map<StockItem,Integer> list;
	
	public Basket(String name) {
		this.name = name;
		this.list= new TreeMap<>();
	}
	
	public int addToBasket(StockItem item , int quantity) {
		if((item!=null) && (quantity>0)) {
			int inBasket = list.getOrDefault(item, 0);
			list.put(item, inBasket+quantity);
			return inBasket;
		}
		return 0;
	}
	
	
	public int removeFromBasket(StockItem item , int quantity) {
		if((item!=null) && (quantity>0)) {
			//basket i�inde item olup olmamas� kontrol� yap�l�yor.
			int inBasket = list.getOrDefault(item, 0);
			int newQuantity = inBasket-quantity;
			
			if(newQuantity>0) {
				list.put(item, newQuantity);
				return newQuantity;
			}
			else if(newQuantity==0) {
				list.remove(item);
				return quantity;
			}
		}
		return 0;
	}
	
	public void clearBasket() {
		this.list.clear();
	}
	
	public Map<StockItem,Integer> Items(){
		return Collections.unmodifiableMap(list);
	}

	@Override
	public String toString() {
		String s= "\nSepet ad�=" + name + " sepetteki �r�n adeti" + list.size() + ((list.size()==1)  ? "item " : "items");
		double totalCost = 0.0;
		
		for(Map.Entry<StockItem, Integer> item : list.entrySet()) {
			s=s+item.getKey() + ". " + item.getValue() + " sat�n al�nd�\n";
			totalCost += item.getKey().getPrice() * item.getValue();
		}
		return s + " Total Cost" + totalCost;
	}
	

}
