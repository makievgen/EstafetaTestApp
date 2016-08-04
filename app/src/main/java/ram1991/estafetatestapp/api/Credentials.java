package ram1991.estafetatestapp.api;

import java.io.UnsupportedEncodingException;

import okio.ByteString;

public class Credentials {
    /**
     * Returns an auth credential for the YOUR scheme.
     */
    public static String basic(String username, String companyId, String password) {
        try {
            String header = username + "@" + companyId + ":" + password;
            byte[] bytes = header.getBytes("ISO-8859-1");
            String encoded = ByteString.of(bytes).base64();
            return "Basic " + encoded;
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }
}
