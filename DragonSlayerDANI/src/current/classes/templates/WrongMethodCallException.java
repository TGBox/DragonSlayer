package current.classes.templates;

/**
 * Created by
 * Daniel Roesch
 * as "Nils Darmstrong".
 * -----------------
 * For "DragonSlayerDANI",
 * on 13.06.2017, 00:10.
 */
public class WrongMethodCallException extends Exception {

  /**
   * constructor method to create a new wrong method call exception object.
   *
   * @param wrongMethodBeingCalled String name of the wrong method that was called.
   */
  public WrongMethodCallException(String wrongMethodBeingCalled) {
    super("A wrong method was called during the execution of the program!\nMethod called:\t\t\t\""
        + wrongMethodBeingCalled + "\"\nYou need to check on that!");
  }

  /**
   * constructor method to create a new wrong method call exception object.
   *
   * @param wrongMethodBeingCalled String name of the wrong method that was called.
   * @param eventThatCalledTheMethod String name of the event that caused the method invocation.
   */
  public WrongMethodCallException(String wrongMethodBeingCalled, String eventThatCalledTheMethod) {
    super("A wrong method was called during the execution of the program!\nMethod called:\t\t\t\""
        + wrongMethodBeingCalled + "\"\nEvent that called the method:\t" + eventThatCalledTheMethod
        + "!\nYou need to check on that!");
  }

  /**
   * constructor method to create a new wrong method call exception object.
   *
   * @param wrongMethodBeingCalled String name of the wrong method that was called.
   * @param eventThatCalledTheMethod String name of the event that caused the method invocation.
   * @param methodThatShouldHaveBeenCalled String name of the method that should have been called.
   */
  public WrongMethodCallException(String wrongMethodBeingCalled, String eventThatCalledTheMethod,
      String methodThatShouldHaveBeenCalled) {
    super("A wrong method was called during the execution of the program!\nMethod called:\t\t\t\t\""
        + wrongMethodBeingCalled + "\"\nEvent that called the method:\t\t"
        + eventThatCalledTheMethod + "!\nMethod that should have been called:\t\""
        + methodThatShouldHaveBeenCalled + "\"\nYou need to check on that!");
  }
}
