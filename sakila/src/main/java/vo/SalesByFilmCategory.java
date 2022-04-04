package vo;

public class SalesByFilmCategory {
	private String category;
	private int totalSales;
	
	@Override
	public String toString() {
		return "SalesByFilmCategory [category=" + category + ", totalSales=" + totalSales + "]";
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getTotalSales() {
		return totalSales;
	}
	public void setTotalSales(int totalSales) {
		this.totalSales = totalSales;
	}
}
