import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Client extends Thread {
    @Override
    public void run() {
        super.run();

        URL url;
        HttpURLConnection con = null;
        try {
            url = new URL("http://server/server.php");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("value", "80");
            parameters.put("payment", "Credit Card");
            parameters.put("code", "1x21");

            con.setDoOutput(true);

            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }

    }
}
