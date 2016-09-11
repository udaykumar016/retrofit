package uday.retrofitu;

/**
 * Created by Lakki on 08-Sep-16.
 */

public class Contributor {

    String login;

    int contributions;

    @Override
    public String toString() {
        return login + " (" + contributions + ")";
    }
}
