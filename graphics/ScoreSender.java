package graphics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

public class ScoreSender {

	private final String USER_AGENT = "Mozilla/5.0";
	
	public void sendPost(int score){
		 try {
			//1. Create the frame.
//			 JFrame frame = new JFrame("FrameDemo");
//			 JOptionPane.showMessageDialog(frame, "Eggs are not supposed to be green.");
			
			 String name = JOptionPane.showInputDialog("Please enter your name: ");
			 
//			 System.out.println(name);
			 
			
			 
			    // open a connection to the site
			    URL url = new URL("http://ec2-52-10-70-15.us-west-2.compute.amazonaws.com/~wilsonz/ehacks/addGame1.php");
			    URLConnection con = url.openConnection();
			    // activate the output
			    con.setDoOutput(true);
			    PrintStream ps = new PrintStream(con.getOutputStream());
			    // send your parameters to your site
			    String sendString = "name=" + name + "&score=" + score;
			    ps.print(sendString);
//			    ps.print("score=10");
			 
			    // we have to get the input stream in order to actually send the request
			    con.getInputStream();
			    
			    // close the print stream
			    ps.close();
			    
			
			    } catch (MalformedURLException e) {
			        e.printStackTrace();
			    } catch (IOException e) {
			        e.printStackTrace();
			    }

	 }
	 
	 public void receive(){
		 try{
			 URL stringURL = new URL("http://ec2-52-10-70-15.us-west-2.compute.amazonaws.com/~wilsonz/ehacks/viewGame1Scores.php");
		        URLConnection yc = stringURL.openConnection();
		        BufferedReader in = new BufferedReader(new InputStreamReader(
		                                    yc.getInputStream()));
		        String inputLine;
//		        while ((inputLine = in.readLine()) != null){ 
//		            System.out.println(inputLine);
//		        }
//		        in.close();
		        inputLine = in.readLine();
//		        System.out.println(inputLine);
		        inputLine = "{ \"topscores\": " +inputLine + "}";
		        
//		        String y = inputLine.substring(294);
//		        System.out.println(y);
		        
		        JSONObject idk = new JSONObject(inputLine);
		        System.out.println("hold on");
		        System.out.println(idk);
		        
		        String names[] = new String[10];
		        int scores[] = new int[10];
		        
		       System.out.println("here we go");
		        final JSONArray topscores = idk.getJSONArray("topscores");
		        for(int i=1; i<11; i++){
		        	
		        	final JSONObject person = topscores.getJSONObject(i);
//		        	System.out.println(person);
		        	System.out.print((i-1) + "\t name: "+ person.getString("name"));
		        	names[i-1] = person.getString("name");
		        	System.out.println("\tscore: " + person.getInt ("score") );
		        	scores[i-1] = person.getInt("score");
		        }
		        
		        

		        
		 } catch (MalformedURLException e) {
		        e.printStackTrace();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	 }
	
}


