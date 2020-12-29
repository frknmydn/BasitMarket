package SortedCollections;

import java.util.Map;

public class Main {
	private static StockList stockList = new StockList();
	public static void main(String[] args) {
		 StockItem temp = new StockItem("ekmek", 0.86, 100);
	        stockList.addStock(temp);

	        temp = new StockItem("kek", 1.10, 7);
	        stockList.addStock(temp);

	        temp = new StockItem("kalem", 12.50, 2);
	        stockList.addStock(temp);

	        temp = new StockItem("sandalye", 62.0, 10);
	        stockList.addStock(temp);

	        temp = new StockItem("kupa", 0.50, 200);
	        stockList.addStock(temp);
	        temp = new StockItem("kupa", 0.45, 7);
	        stockList.addStock(temp);

	        temp = new StockItem("kapi", 72.95, 4);
	        stockList.addStock(temp);

	        temp = new StockItem("meyve suyu", 2.50, 36);
	        stockList.addStock(temp);

	        temp = new StockItem("telefon", 96.99, 35);
	        stockList.addStock(temp);

	        temp = new StockItem("havlu", 2.40, 80);
	        stockList.addStock(temp);

	        temp = new StockItem("vazo", 8.76, 40);
	        stockList.addStock(temp);

	        System.out.println(stockList); //toString metodunun tanýmlanmýţ hali gelir.

	        for(String s: stockList.Items().keySet()) {
	            System.out.println(s);
	        }

	        Basket furkansBasket = new Basket("Furkan");

	        sellItem(furkansBasket, "kalem", 1);
	        System.out.println(furkansBasket);

	        sellItem(furkansBasket, "kalem", 1);
	        System.out.println(furkansBasket);

	        if(sellItem(furkansBasket, "kalem", 1) != 1) {
	            System.out.println("Stoklarýmýzda daha fazla kalem bulunmamakta");
	        }

	       


	        sellItem(furkansBasket, "mevye suyu", 4);
	        sellItem(furkansBasket, "kupa", 12);
	        sellItem(furkansBasket, "ekmek", 1);




	        Basket basket = new Basket("customer");
	        sellItem(basket, "kupa", 100);
	        sellItem(basket, "meyve suyu", 5);
	        removeItem(basket, "kupa", 1);
	        System.out.println(basket);

	        removeItem(furkansBasket, "kalem", 1);
	        removeItem(furkansBasket, "kupa", 9);
	        removeItem(furkansBasket, "kalem", 1);
	        System.out.println("kalan kalem: " + removeItem(furkansBasket, "kalem", 1));  // should not remove any

	        System.out.println(furkansBasket);

	        // Bütün itemlarý liste içinden kaldýrýlmasý için.
	        removeItem(furkansBasket, "ekmek", 1);
	        removeItem(furkansBasket, "kupa", 3);
	        removeItem(furkansBasket, "meyve suyu", 4);
	        removeItem(furkansBasket, "kupa", 3);
	        System.out.println(furkansBasket);

	        System.out.println("\stok listesinin checkout öncesi ve sonrasý görüntüsü:");
	        System.out.println(basket);
	        System.out.println(stockList);
	        checkOut(basket);
	        System.out.println(basket);
	        System.out.println(stockList);

//	        temp = new StockItem("pen", 1.12);
//	        stockList.Items().put(temp.getName(), temp);
	        StockItem kalem = stockList.Items().get("kalem");
	        if(kalem != null) {
	            kalem.adjustStock(2000);
	        }
	        if(kalem != null) {
	            stockList.get("kalem").adjustStock(-1000);
	        }

	        System.out.println(stockList);


	        checkOut(furkansBasket);
	        System.out.println(furkansBasket);
        
	}
	
	public static int sellItem(Basket basket , String item, int quantity) {
		StockItem stockItem = stockList.get(item);
		
		if(stockItem==null) {
			System.out.println("We can't sell " + item);
			return 0;
		}
		
		if(stockList.sellStock(item, quantity)!=0) {
			basket.addToBasket(stockItem, quantity);
			return quantity;
		}
		
		return 0;
		
	}
	
	
	public static int removeItem(Basket basket , String item , int quantity) {
		
		StockItem stockItem = stockList.get(item);
		
		if(stockItem==null) {
			System.out.println("We don't sell " + item);
			return 0;
		}
		
		if(basket.removeFromBasket(stockItem, quantity)==quantity) {
			return stockList.unreserveStock(item, quantity);
		}
		return 0;
	}
	
	public static void checkOut(Basket basket) {
		for(Map.Entry<StockItem, Integer> item : basket.Items().entrySet()) {
			stockList.sellStock(item.getKey().getName(), item.getValue());
		}
		basket.clearBasket();
	}

}
