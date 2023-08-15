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
import java.util.NoSuchElementException;
import java.io.File;


/**
 * This class has marketItems and methods that can be run on shopping cart
 * 
 * @author mvuyyuru
 *
 */
public class ExceptionalShoppingCartTester {
  /**
   * 
   * @param args
   */
  public static void main(String[] args) {
    runAllTests();
  }

  /**
   * this tester checks the correctness of the LookupupMethods() method. this test method will
   * return true if all the try blocks do not return false and have a correct functionality. if a
   * try block returns false and the test scenario does not work, it will catch the assigned error
   * and print out an error message describing the error.
   * 
   * @return returns true if all tests are passed and false otherwise and will also return the
   *         assigned error message
   */
  public static boolean testLookupMethods() {
    try {
      ExceptionalShoppingCart.lookupProductByName("rooster");
      return false;
    } catch (NoSuchElementException ne) {
      System.out.println(ne.getMessage());
    }
    try {
      ExceptionalShoppingCart.lookupProductById(000);
      return false;
    } catch (IllegalArgumentException ie) {
      System.out.println(ie.getMessage());
    }
    try {
      ExceptionalShoppingCart.lookupProductById(1234);
      return false;
    } catch (NoSuchElementException nsee) {
      System.out.println(nsee.getMessage());
    }
    return true;
  }

  /**
   * this tester checks the correctness of AddItemToMarketCatalog() method. this test method will
   * return true if all the try blocks do not return false and have a correct functionality. if a
   * try block returns false and the test scenario does not work, it will catch the assigned error
   * and then print out an error message describing the error.
   * 
   * @return true if all scenarios work and will return false if one or more of the scenarios do not
   *         work and will also return the assigned error message
   */
  public static boolean testAddItemToMarketCatalog() {
    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("12345", "Jam", "$0.25");
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }

    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("9876", null, "$5.00");
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("9876", "", "$5.00");
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("9876", "rooster", "5.00");
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    try {
      ExceptionalShoppingCart.addItemToMarketCatalog("9876", "rooster", "$-5.00");
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    return true;
  }

  /**
   * checks whether SaveCartSummary runs
   * 
   * @return
   */
  public static boolean testSaveCartSummary() {
    String[] cart = new String[] {"Avacado", "Apple", "Apple", "Pizza", "Avacdo", "Spinach",
        "Pizza", "Apple", "Banana"};
    File file = new File("file.txt");
    try {
      ExceptionalShoppingCart.saveCartSummary(cart, -1, file);
      return false;
    } catch (IllegalArgumentException iae) {
      System.out.println(iae.getMessage());
    }
    return true;
  }

  /**
   * testLoadCartSummary will test
   * 
   * @return false if any of the tester methods fails, and true if all the tests are passed.
   */
  public static boolean testLoadCartSummary() {

    String[] cart = new String[] {"Apple", "Avacado", "Avacado", "Beef", "Apple", "Potato", "Beef",
        "Avacado", "Banana"};
    File file = new File("file.txt");
    try {
      ExceptionalShoppingCart.saveCartSummary(cart, -5, file);
      ExceptionalShoppingCart.loadCartSummary(file, cart, -5);
      return false;
    } catch (IllegalArgumentException ie) {
      System.out.println(ie.getMessage());
    }
    try {
      ExceptionalShoppingCart.saveCartSummary(cart, 5, file);
      ExceptionalShoppingCart.loadCartSummary(file, cart, 5);
      return false;
    } catch (IllegalStateException ie) {
      System.out.println(ie.getMessage());

      cart = new String[] {"Apple", "Avacado", null, null, null, null, null, null, null};
      file = new File("file.txt");
      try {
        ExceptionalShoppingCart.saveCartSummary(cart, 2, file);
        ExceptionalShoppingCart.loadCartSummary(file, cart, 2);
        return false;
      } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
      }
      return true;
    }
  }

  /**
   * runAllTests() will call all the tester methods in the ExceptionalShoppingCartTester class and
   * it will return true if all the tests pass to true and false if one or more tests fail.
   * 
   * @return false if any of the tester methods fails, and true if all the tests are passed.
   */
  public static boolean runAllTests() {
    // Your runAllTests() must call all the tester methods that you are going to implement
    // in your tester class, return true if all tests pass, and false if any of your tests fails.

    if (testLookupMethods() && testAddItemToMarketCatalog() && testSaveCartSummary()
        && testLoadCartSummary()) {
      return true;
    }
    testLoadCartSummary();
    return false;
  }
}

