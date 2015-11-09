package eventbrite.operation;

/**
 * Represents the base class for Eventbrite API results.
 *
 * @Author: yummin
 * Date: 15/11/6
 */
public abstract class BaseResult {

    /**
     *
     * @return next page number. Return 0 if there is no next page.
     */
    public abstract int nextPage();

    public abstract BaseResult deserialize();
}
