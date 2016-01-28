package pizzeria.menu;

public class Pizza extends Food {
	private static final String SIZE_MESSAGE_ERROR = "Size is not correct";
	private static final int MIN_SIZE_PIZZA = 0;
	private int size;

	public Pizza(double price, short quantity, String name, int grammage, int size) {
		super(price, quantity, name, grammage);
		setSize(size);
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.validateSize(size);
		this.size = size;
	}

	private void validateSize(int size) {
		if (size < MIN_SIZE_PIZZA) {
			throw new IllegalArgumentException(SIZE_MESSAGE_ERROR);
		}
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}
	
//	public double priceForPizza (Pizza p){
//		return p.getSize()*0.2 + p.getPrice() + p.
//	}

}
