/////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: This class has marketItems and methods that can be run on shopping cart
// Course: CS 300 Spring 2022
//
// Author: Madhu Vuyyuru
// Email: mvuyyuru@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// no pair programming was used or allowed
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// no outside source code was used
//
//
///////////////////////////////////////////////////////////////////////////////
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;

/**
 * This class has marketItems and methods that can be run on a shopping cart
 * 
 * @author mvuyyuru
 *
 */
public class ExceptionalShoppingCart {

  // Define final parameters (constants)
  private static final double TAX_RATE = 0.05; // sales tax
  // a perfect-size two-dimensional array that stores the list of available items in a given market
  // MarketItems[i][0] refers to a String representation of the item key (unique identifier)
  // MarketItems[i][1] refers the item name
  // MarketItems[i][2] a String representation of the unit price of the item in dollars
  private static String[][] marketItems =
      new String[][] {{"4390", "Apple", "$1.59"}, {"4046", "Avocado", "$0.59"},
          {"4011", "Banana", "$0.49"}, {"4500", "Beef", "$3.79"}, {"4033", "Blueberry", "$6.89"},
          {"4129", "Broccoli", "$1.79"}, {"4131", "Butter", "$4.59"}, {"4017", "Carrot", "$1.19"},
          {"3240", "Cereal", "$3.69"}, {"3560", "Cheese", "$3.49"}, {"3294", "Chicken", "$5.09"},
          {"4071", "Chocolate", "$3.19"}, {"4363", "Cookie", "$9.5"}, {"4232", "Cucumber", "$0.79"},
          {"3033", "Eggs", "$3.09"}, {"4770", "Grape", "$2.29"}, {"3553", "Ice Cream", "$5.39"},
          {"3117", "Milk", "$2.09"}, {"3437", "Mushroom", "$1.79"}, {"4663", "Onion", "$0.79"},
          {"4030", "Pepper", "$1.99"}, {"3890", "Pizza", "$11.5"}, {"4139", "Potato", "$0.69"},
          {"3044", "Spinach", "$3.09"}, {"4688", "Tomato", "$1.79"}, null, null, null, null};

  /**
   * Creates a deep copy of the market catalog
   * 
   * @return Returns a deep copy of the market catalog 2D array of strings
   */
  public static String[][] getCopyOfMarketItems() {
    String[][] copy = new String[marketItems.length][];
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        copy[i] = new String[marketItems[i].length];
        for (int j = 0; j < marketItems[i].length; j++)
          copy[i][j] = marketItems[i][j];
      }
    }
    return copy;
  }

  /**
   * Returns a string representation of the item whose name is provided as input
   *
   * @param name name of the item to find
   * @return "itemId name itemPrice" if an item with the provided name was found
   * @throws NoSuchElementException with descriptive error message if no match found
   */
  public static String lookupProductByName(String name) {
    // throws NoSuchElementException with descriptive error message if no match found
    String s = "No match found";
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null && name.equals(marketItems[i][1])) {
        return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
      }
    }
    throw new NoSuchElementException("No match found");
  }


  /**
   * Returns a string representation of the item whose id is provided as input
   *
   * @param key id of the item to find
   * @return "itemId name itemPrice" if an item with the provided name was found
   * @throws IllegalArgumentException with descriptive error message if key is not a 4-digits int
   * @throws NoSuchElementException   with descriptive error message if no match found
   */
  public static String lookupProductById(int key) {
    // throws IllegalArgumentException with descriptive error message if key is not a 4-digits int
    // throws NoSuchElementException with descriptive error message if no match found
    if (String.valueOf(key).length() != 4) {
      throw new IllegalArgumentException("the key that was entered is not a 4 digit int");
    }
    String s = "No match found";
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null) {
        if (marketItems[i][0].equals(String.valueOf(key)))
          return marketItems[i][0] + " " + marketItems[i][1] + " " + marketItems[i][2];
      }
    }
    throw new NoSuchElementException("No match was found ");
  }

  /**
   * Returns the index of the first null position that can be used to add new market items returns
   * the length of MarketItems if no available null position is found
   * 
   * @return returns an available position to add new market items or the length of market items if
   *         no available positions are found
   */
  private static int indexOfInsertionPos() {
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] == null)
        return i;
    }
    return marketItems.length;
  }

  /**
   * add a new item to market items array, expand the capacity of marketitems if it is full when
   * trying to add new item, use indexofInsertionPos() to find the position to add
   * 
   * @param id    id of the item to add
   * @param name  name of the item to add
   * @param price price of the item to add
   * @throws IllegalArgumentException with descriptive error message if id not parsable to 4-digits
   * 
   */
  public static void addItemToMarketCatalog(String id, String name, String price) {
    // throws IllegalArgumentException with descriptive error message if id not parsable to 4-digits
    // int, name is null or empty string, and price not parsable to double
    int next = indexOfInsertionPos();
    try {
      if (String.valueOf(Integer.parseInt(id)).length() != 4) {
        throw new IllegalArgumentException("The id That was entered is not 4 digits");
      }
      if (name == null) {
        throw new IllegalArgumentException("the id that was entered is null");
      }
      if (name == "") {
        throw new IllegalArgumentException("the id that was entered is empty");
      }
      if (!price.substring(0, 1).equals("$")) {
        throw new IllegalArgumentException("the id either does not start with '$'");
      }
      if (Double.parseDouble(price.substring(1)) < 0) {
        throw new NumberFormatException("the price is negative");
      }
    } catch (NumberFormatException e) {
      if (e.getMessage() == null) {
        throw new IllegalArgumentException(e.getMessage());
      } else {
        throw new IllegalArgumentException(
            "the entered price or id was not able to be converted into a double");
      }
    }
    if (next == marketItems.length) {
      String[][] newMarketItems = new String[marketItems.length * 2][];
      for (int i = 0; i < newMarketItems.length; i++) {
        for (int j = 0; j < newMarketItems[i].length; j++) {
          newMarketItems[i][j] = marketItems[i][j];
        }
      }
      newMarketItems[next] = new String[] {id, name, price};
      marketItems = newMarketItems;
    } else {
      marketItems[next] = new String[] {id, name, price};
    }
  }

  /**
   * Returns the price in dollars (a double value) of a market item given its name.
   * 
   * @param name name of the item to get the price
   * @return the price of the item
   * @throws NoSuchElementException with descriptive error message if price not found
   * 
   */
  public static double getProductPrice(String name) {
    // throws NoSuchElementException with descriptive error message if price not found
    for (int i = 0; i < marketItems.length; i++) {
      if (marketItems[i] != null && name.equals(marketItems[i][1])) {
        return Double.valueOf(marketItems[i][2].substring(1));
      }
    }
    throw new NoSuchElementException("the price that was entered was not found");
  }

  /**
   * Appends an item to a given cart (appends means adding to the end). If the cart is already full
   * (meaning its size equals its length), IllegalStateException wil be thrown.
   * 
   * @param item the name of the product to be added to the cart
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the size of the oversize array cart after trying to add item to the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws IllegalStateException    with descriptive error message if this cart is full
   */
  public static int addItemToCart(String item, String[] cart, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    // throws IllegalStateException with descriptive error message if this cart is full
    if (size < 0) {
      throw new IllegalArgumentException("the size can not be zero");
    }
    if (size == cart.length) {
      throw new IllegalStateException("the cart if full");
    }
    cart[size] = item;
    size++;
    return size;
  }

  /**
   * Returns the number of occurrences of a given item within a cart. This method must not make any
   * changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return the number of occurrences of item (exact match) within the oversize array cart. Zero or
   *         more occurrences of item can be present in the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * 
   */
  public static int nbOccurrences(String item, String[] cart, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");

    }
    int count = 0;
    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        count++;
      }
    }
    return count;
  }

  /**
   * Checks whether a cart contains at least one occurrence of a given item. This method must not
   * make any changes to the contents of the cart.
   * 
   * @param item the name of the item to search
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns true if there is a match (exact match) of item within the provided cart, and
   *         false otherwise.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * 
   */
  public static boolean contains(String item, String[] cart, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Removes one occurrence of item from a given cart.
   * 
   * @param item the name of the item to remove
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the oversize array cart after trying to remove item from the cart.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws NoSuchElementException   with descriptive error message if item not found in the cart
   * 
   */
  public static int removeItem(String[] cart, String item, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    // throws NoSuchElementException with descriptive error message if item not found in the cart
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    for (int i = 0; i < size; i++) {
      if (cart[i].equals(item)) {
        cart[i] = cart[size - 1];
        cart[size - 1] = null;
        return size - 1;
      }
    }
    throw new NoSuchElementException("The item that was entered is not found in the cart");
  }


  /**
   * Removes all items from a given cart. The array cart must be empty (contains only null
   * references) after this method returns.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after removing all its items.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws NullPointerException     with descriptive error message if cart is null
   */
  public static int emptyCart(String[] cart, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    // throws NullPointerException with descriptive error message if cart is null
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    if (cart == null) {
      throw new NullPointerException("the cart is null");
    }
    for (int i = 0; i < cart.length; i++) {
      cart[i] = null;
    }
    return 0;
  }


  /**
   * This method returns the total value in dollars of the cart. All products in the market are
   * taxable (subject to TAX_RATE).
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the total value in dollars of the cart accounting taxes.
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static double checkout(String[] cart, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    double total = 0.0;
    for (int i = 0; i < size; i++) {
      total += getProductPrice(cart[i]) * (1 + TAX_RATE);
    }
    return total;
  }

  /**
   * Returns a string representation of the summary of the contents of a given cart. The format of
   * the returned string contains a set of lines where each line contains the number of occurrences
   * of a given item, between spaces and parentheses, followed by one space followed by the name of
   * a unique item in the cart. ( #occurrences ) name1 ( #occurrences ) name2 etc.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns a string representation of the summary of the contents of the cart
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static String getCartSummary(String[] cart, int size) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    String s = "";
    for (int i = 0; i < size; i++) {
      if (!contains(cart[i], cart, i)) {
        s = s + "( " + nbOccurrences(cart[i], cart, size) + " ) " + cart[i] + "\n";
      }
    }
    return s.trim();
  }


  /**
   * Save the cart summary to a file.
   * 
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @param file the file to save the cart summary
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   */
  public static void saveCartSummary(String[] cart, int size, File file) {
    // throws IllegalArgumentException with descriptive error message if size is less than zero
    // NO other exception should be thrown by this method
    // Use finally block to close any resource used to write the cart summary into file
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    FileWriter w = null;
    try {
      String output = getCartSummary(cart, size);
      w = new FileWriter(file);
      w.write(output);
    } catch (IOException e) {
    } finally {
      try {
        w.close();
      } catch (IOException e) {
      }
    }
  }

  /**
   * Parse one line of cart summary and add nbOccurrences of item to cart correct formatting for
   * line:"( " + nbOccurrences + " ) " + itemName delimiter: one space (multiple spaces: wrong
   * formatting)
   *
   * @param line a line of the cart summart to be parsed into one item to be added
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @throws DataFormatException      with descriptive error message if wrong formatting (including
   *                                  nbOccurrences not parsable to a positive integer less or equal
   *                                  to 10)
   * @throws IllegalArgumentException with descriptive error message if itemName not found in
   *                                  marketItems
   * @throws IllegalStateException    with descriptive error message if cart reaches its capacity
   */
  protected static int parseCartSummaryLine(String line, String[] cart, int size)
      throws DataFormatException {
    // throws DataFormatException with descriptive error message if wrong formatting (including
    // nbOccurrences not parsable to a positive integer less or equal to 10)
    try {
      int startOccurrences = line.indexOf('(') + 1;
      int endOccurrences = line.indexOf(')');
      int nbOccurrences =
          Integer.parseInt(line.substring(startOccurrences + 1, endOccurrences - 1));
      if (nbOccurrences < 0 || nbOccurrences > 10) {
        throw new DataFormatException(
            "nbOccurrences is not parsable to a positive integer less than or equal to 10");
      }
      String item = line.substring(line.indexOf(')') + 2);
      lookupProductByName(item);
      for (int i = 0; i < nbOccurrences; i++) {
        if (size >= cart.length) {
          throw new IllegalStateException("the cart has reached its maximum capacity");
        }
        if (item != null) {
          cart[size] = item;
          size++;
        }
      }
    } catch (NumberFormatException e) {
      throw new DataFormatException(
          "nbOccurrences is not able to be parsed to a positive integer that is less than "
              + "or equal to ten");
    } catch (StringIndexOutOfBoundsException e) {
      throw new IllegalArgumentException(" the format of the line entered is incorrect");
    } catch (NoSuchElementException e) {

    }
    // throws IllegalArgumentException with descriptive error message if itemName not found in
    // marketItems

    // throws IllegalStateException with descriptive error message if cart reaches its capacity
    // NO other exception should be thrown by this method

    return size; // default return statement added to avoid compiler errors
  }

  /**
   * Load the cart summary from the file. For each line of summary, add nbOccurrences of item to
   * cart. Must call parseCartSummaryLine to operate
   *
   * @param file file to load the cart summary from
   * @param cart an array of strings which contains the names of items in the cart
   * @param size the number of items in the cart
   * @return Returns the size of the cart after adding items to the cart
   * @throws IllegalArgumentException with descriptive error message if size is less than zero
   * @throws IllegalStateException    with descriptive error message if cart reaches its capacity
   */
  public static int loadCartSummary(File file, String[] cart, int size) {
    // This method MUST call parseCartSummaryLine to operate (to parse each line in file)

    // throws IllegalArgumentException with descriptive error message if size is less than zero
    if (size < 0) {
      throw new IllegalArgumentException("the size entered is less than zero");
    }
    Scanner s = null;
    try {
      s = new Scanner(file);
      while (s.hasNextLine()) {
        try {
          String line = s.nextLine();
          size = parseCartSummaryLine(line, cart, size);
          if (size >= cart.length) {
            throw new IllegalStateException("the cart is at maximum capacity");
          }

        } catch (DataFormatException e) {

        } catch (IllegalArgumentException e) {

        }
      }
    } catch (FileNotFoundException e) {

    } finally {
      if (s != null) {
        s.close();
      }

    }
    // throws IllegalStateException with descriptive error message if cart reaches its capacity
    // NO other exception should be thrown by this method
    // Use finally block to close any resource used to read from file

    return size; // default return statement added to avoid compiler errors
  }


}
