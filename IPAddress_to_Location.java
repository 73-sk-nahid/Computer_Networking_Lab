import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class IPAddress_to_Location {
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        IPAddress_to_Location objmain = new IPAddress_to_Location();
        objmain.get_response();
    }
    public void get_response() throws IOException, ParserConfigurationException, SAXException {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the IP address which location you want to get: ");
        String scan = scanner.nextLine();
        InetAddress myIP = InetAddress.getByName(scan);
        System.out.println(scan+" IP address is: "+myIP);
        String IP = String.valueOf(myIP);
        String API = "at_59vQUIHsn9UlFs8VTgV9ElrxK0rk9";  //getting this api from https://ip-geolocation.whoisxmlapi.com/api
        String url = "https://www.whoisxmlapi.com/whoisserver/WhoisService?apiKey="+API+"&ipAddress="+IP;
        System.out.println("Request dynamic URL is: "+url);
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) !=null) response.append(inputLine);
        in.close();
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(response.toString())));
        NodeList errNodes = doc.getElementsByTagName("registrant");
        if (errNodes.getLength() > 0) {
            Element err = (Element)errNodes.item(0);
            System.out.println("Organization : "+err.getElementsByTagName("organization").item(0).getTextContent());
            System.out.println("Street       : "+err.getElementsByTagName("street1").item(0).getTextContent());
            System.out.println("City         : "+err.getElementsByTagName("city").item(0).getTextContent());
            System.out.println("State        : "+err.getElementsByTagName("state").item(0).getTextContent());
            System.out.println("Postal Code  : "+err.getElementsByTagName("postalCode").item(0).getTextContent());
            System.out.println("Country      : "+err.getElementsByTagName("country").item(0).getTextContent());
        } else {
            System.out.println("Check XML file again");
        }
    }
}

