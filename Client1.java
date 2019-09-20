
package client1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
 

public class Client1 {

   
    public static void main(String[] args) {
          try
        {
            Scanner sc = new Scanner(System.in);
            Socket c = new Socket("127.0.0.1", 1234);//1234 port no of server app ip of current machine
            DataOutputStream dos = new DataOutputStream(c.getOutputStream());
            DataInputStream dis = new DataInputStream(c.getInputStream());

           
            while (true)
            {
                String servermsg = dis.readUTF();
                System.out.println(servermsg);
                String userresp = sc.nextLine();
                dos.writeUTF(userresp);
                if(userresp.equalsIgnoreCase("N"))
                    break;
            }

            
            dos.close();
            dis.close();
            c.close();
        } catch (IOException ex)
        {

        }
    }
    
}
