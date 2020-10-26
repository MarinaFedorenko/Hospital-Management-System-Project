package nix.util;

public class DataValidator {

    public static boolean isName(String input) {

        String name = "^[A-Za-z ]*$";

        if (input.matches(name)) {
            return true;
        } else {
            return false;
        }
    }

        public static boolean isPhoneNumber(String input) {
//            String regex = "^[7-9][0-9]{9}$";
            String regex = "^\\d{10}$";
            if (input.matches(regex)) {
                return true;
            } else {
                return false;
            }
        }

    public static boolean isEmail(String input) {

//        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
            String regex = "^(.+)@(.+)$";
            try {
                return input.matches(regex);
            } catch (NumberFormatException e) {
                return false;
            }


    }

}
