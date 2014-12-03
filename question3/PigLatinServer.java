/**
 * PigLatinServer
 * Accepts strings and returns their Pig Latin equivalent.  
 * 
 * Invoke as: java PigLatinServer [-port=<number>] 
 * Default port is 1200, if not specified 
 * 
 * @author (Lior Shahverdi) 
 * @version (Version 1.1 12/03/2014)
 */
import java.io.*; 
import java.net.*; 
public class PigLatinServer
{
    public static void main (String [] args)
    {
        ServerSocket ss = null; //Server socket to offer service
        Socket s; //Socket to accept connection                             //used to establish connection
        int serverPort = 1200; //Use port 1200 by default
        InetAddress local, remote; //Local and remote addresses
        int localPort, remotePort; //Local and remote ports 
        
        //For input and output
        BufferedReader socketIn; //Socket input
        PrintWriter socketOut; //Socket output
        String clientInput; //String sent by client
        String[] clientInputTokens;
        String serverResult; //String to be sent back
        
        //Process command line arguments
        //Error if more than one command line argument
        if (args.length > 1) 
        {
            System.out.println("Usage: java PigLatinServer "
                               + "[-port=<number>]");
            System.exit(-1); 
        }
        //Set the server port suitably 
        if ((args.length == 1) && 
           (args[0].startsWith("-port=")))
                serverPort = Integer.parseInt(args[0].substring(6)); 
        //Print greeting 
        System.out.println("Hello, you have invoked a Pig Latin Server"); 
        System.out.println("You have requested that service be started at port " + serverPort); 
        System.out.println(); //Blank line
        //Establish requested service
        try 
        {
            ss = new ServerSocket(serverPort);
        }
        catch (Exception e) 
        {
            e.printStackTrace(); 
        }
        //Report status 
        System.out.println("Pig Latin Server is ready"
                           + " for service at port " + serverPort);
        System.out.println(); //Blank line
        //Server stops only when the process is terminated 
        //Enter a forever loop to offer service        
        for (;;) 
        {
            try 
            {
                //Wait and accept connection 
                s = ss.accept(); //Accept connection on server socket
                //Report connection status
                System.out.println("Accepted connection");
                local = s.getLocalAddress(); remote = s.getInetAddress(); 
                localPort = s.getLocalPort(); remotePort = s.getPort(); 
                System.out.println ("Server side"); 
                System.out.println ("   " + local.getHostName() 
                                + "[" + local.getHostAddress() + "]" 
                                + " at port " + localPort);
                System.out.println ("Client side"); 
                System.out.println ("   " + remote.getHostName()
                                + "[" + remote.getHostAddress() + "]" 
                                + " at port " + remotePort); 
                System.out.println(); //Blank line
                //TCP provides a full-duplex byte stream service
                //For UNICODE and line support, we need BufferedReader 
                //and PrintWriter objects
                socketIn = new BufferedReader(new InputStreamReader(s.getInputStream())); 
                socketOut = new PrintWriter(s.getOutputStream(), true);
                //Interact with client
                //Read string from client
                clientInput = socketIn.readLine(); 
                clientInputTokens = clientInput.split(" ");

                //Loop until client indicates termination 
                while (!clientInput.equalsIgnoreCase("bye"))
                {
                    serverResult = translate(clientInputTokens);
                    
                    //Log work done 
                    System.out.println("Server received: " 
                                       + clientInput); 
                    System.out.println("Server responded: "
                                       + serverResult);
                    System.out.println(); //Blank line
                    //Send response to client
                    socketOut.println(serverResult); 
                    //Get new string
                    clientInput = socketIn.readLine(); 
                }  
                //Close all streams, socket and report 
                socketIn.close(); 
                socketOut.close(); 
                s.close();
                System.out.println("Closed connection"); 
                System.out.println(); //Blank line
            } 
            catch (Exception e) 
            { 
                e.printStackTrace();
            }
        } 
    }  

    public static String translate (String[] tokens){
        String temp = null;

        return temp;
    }  
}