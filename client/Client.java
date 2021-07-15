import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.*;
import org.w3c.dom.*;

public class Client extends Thread {
    @Override
    public void run() {
        super.run();

        String [] possiblePayments={"Credit Card","PayPal","Transfer","ApplePay","MyBank","GooglePay","Stripe","Braintree","Wepay"};
        URL url, sandbox;
        HttpURLConnection con = null, conn2 = null;
        
        try {
            // Connection to Server
            url = new URL("http://server/server.php");
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            Map<String, String> parameters = new HashMap<>();
            parameters.put("code", "1x21");
            con.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            // Recover Sandbox URL
            BufferedReader in = new BufferedReader(new java.io.InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            inputLine = in.readLine();
            content.append(inputLine);
            sandbox = new URL("http://" + inputLine);
            conn2 = (HttpURLConnection) sandbox.openConnection();
            conn2.setRequestMethod("POST");
            parameters.clear();
            parameters.put("value", "80");
            parameters.put("payment", possiblePayments[(int)(System.currentTimeMillis() % possiblePayments.length)]);
            parameters.put("code", "1x21");
            parameters.put("transactionID", Thread.currentThread().getName());
            conn2.setDoOutput(true);
            out = new DataOutputStream(conn2.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            // Process Sandbox Response
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(conn2.getInputStream());
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("response");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println(eElement.getElementsByTagName("transactionID").item(0).getTextContent());
                    System.out.println(eElement.getElementsByTagName("message").item(0).getTextContent());
                }
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }

    }
}
