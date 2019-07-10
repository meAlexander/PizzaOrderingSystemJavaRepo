package commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;

public class AddProductMenuCommand implements Command {
	private Connection connection;
	private PrintStream printOut;
	private BufferedReader buffReader;
	
	public AddProductMenuCommand(Connection connection, PrintStream printOut, BufferedReader buffReader) {
		this.connection = connection;
		this.printOut = printOut;
		this.buffReader = buffReader;
	}

	@Override
	public Command execute(Command parent) {
		printOut.println("Add product menu: 1.Pizza 2.Salad 3.Drink 4.Main menu");
		printOut.println("Your input please: ");
		printOut.flush();

		try {
			String buyProductAnswer = buffReader.readLine();
			return getNextCommand(buyProductAnswer);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (UnsupportedOperationException e) {
			printOut.flush();
			return new AddProductMenuCommand(connection, printOut, buffReader);
		}
		return null;
	}

	private Command getNextCommand(String buyProductAnswer) {
		System.out.println("Returning: " + buyProductAnswer);
		switch (buyProductAnswer) {
		case "Pizza":
			return new AddProductPizzaCommand(connection, printOut, buffReader);
		case "Salad":
			return new AddProductSaladCommand(connection, printOut, buffReader);
		case "Drink":
			return new AddProductDrinkCommand(connection, printOut, buffReader);
		case "Main menu":
			return new MainMenuCommand(connection, printOut, buffReader);
		default:
			throw new UnsupportedOperationException();
		}
	}	
}