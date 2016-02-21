package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public interface IProduct {
//	void setPrice(int price);
	void setId(int id);
	int getId();
	String getName();
	double getPrice();
	int getQuantity();
	void setName(String name) throws InvalidArgumentValueException;
	void setPrice(double price) throws InvalidArgumentValueException;
	void setQuantity(int quantity) throws InvalidArgumentValueException;
	void setGrammage(int grammage) throws InvalidArgumentValueException;
//	void setSize(int size);
	int getGrammage();
}
