package pizzeria.menu;

import exceptions.InvalidArgumentValueException;

public interface IProduct {
	void setId(int id);

	int getId();

	String getName();

	double getPrice();

	String getImage();

	void setName(String name) throws InvalidArgumentValueException;

	void setPrice(double price) throws InvalidArgumentValueException;

	void setImage(String image) throws InvalidArgumentValueException;
}
