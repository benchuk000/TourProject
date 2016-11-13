package runners;

import client.views.SignInView;

import java.util.Properties;

public class ClientRunner {
    public static void main( String args[] ) {
        Properties props = new Properties();
        props.put("useUnicode","true");
        props.put("characterEncoding", "Cp1251");

        SignInView loginView = new SignInView();
    }
}
