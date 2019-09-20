/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server1;
import java.io.*;
import java.util.*;
import java.net.ServerSocket;
import java.net.Socket;
import static server1.clienthandler.z;

class clients {
    public String accname;
public int password;
public int id; 
public int balance;
Vector transactions = new Vector();
    
}

class serverhandler implements Runnable{
    
    Socket c;
    public serverhandler(Socket z){c=z; }
    
    public void run(){
        try{
     DataOutputStream dos = new DataOutputStream(c.getOutputStream());
     DataInputStream dis = new DataInputStream(c.getInputStream());
      String id = dis.readUTF();
      //userchoice.equalsIgnoreCase("exit")
  int idd = Integer.parseInt(id);
boolean flag=true;//check id
int index;
        while(true)
        {
         for(index = 0; index < z.size(); index++)
            {if(idd==z.get(index).id){flag=false;break;}}
         if(flag==true){
         dos.writeUTF("qq");
         dis.readUTF();
         }
         else if(flag==false)break;
        }
          dos.writeUTF("yaarrrab");
        String amount=dis.readUTF();
      int t = Integer.parseInt(id);
      z.get(index).balance=+t;
         dos.writeUTF("yaarrrab");
        
      }   
      //check amount
    //z.get(index).balance+=;
        catch (IOException e)
        {
            
        }
  } 
   
}
class clienthandler implements Runnable{
Socket c;

clients g=new clients();


public clienthandler(Socket s){c=s; }
static public Vector<clients> z = new Vector<clients>();



public void run(){ 
     try
        {
           
          
                DataOutputStream dos = new DataOutputStream(c.getOutputStream());
                DataInputStream dis = new DataInputStream(c.getInputStream());
              
                    while(true)
                    { 
              
                    dos.writeUTF("create account or log in ");
                    String choice = dis.readUTF();
                     if(choice.equalsIgnoreCase("external"))
                     {
                      
           boolean flag=true;
           int index,idd;
        while(true)
        {
             String id = dis.readUTF();
             idd = Integer.parseInt(id);
         for(index = 0; index < z.size(); index++)
            {if(idd==z.get(index).id){flag=false;break;}
            }
          if(flag==false){dos.writeUTF("yaarrrab");break;}else dos.writeUTF("qq");
        }
          
        
        String amount=dis.readUTF();
      int t = Integer.parseInt(amount);
      z.get(index).balance=z.get(index).balance+t;
        z.get(index).transactions.add("receiving "+amount+"from "+idd+" in server2");
                          
         dos.writeUTF("yaarrrab");
                     }
                    
                     else if(choice.equalsIgnoreCase("log in"))
                        
                    {//check if ID is valid..done
                         int index;
                        while(true)
                        {
                            boolean flag=true;
                            dos.writeUTF("enter your ID:");
                            String id = dis.readUTF();
                            int idd = Integer.parseInt(id);
                            
                                for( index = 0; index < z.size(); index++)
                                {
                       
                                if(idd==z.get(index).id){
                                     while(true){
                                 dos.writeUTF("choose password or id");
                                 String nn = dis.readUTF();
                                 if(nn.equalsIgnoreCase("password")){
                                      dos.writeUTF("enter your password ");
                                        String password = dis.readUTF();
                                     int passd = Integer.parseInt(password);
                                 if(passd==z.get(index).password){flag=false;break;}}
                                 else if(nn.equalsIgnoreCase("id")) break;
                                    }
                                   
                                    break;}
                                }
                               
                                if(flag==false)break;
                                
                        }
                        
                    while (true)
                    {
                        
                        dos.writeUTF("please choose an operation from this list() \n"
                            + "Check balance/Deposit/Withdraw/transfer money"
                            + "/transaction history / exit");
                        String userchoice = dis.readUTF();
                   
                        if(userchoice.equalsIgnoreCase("check balance"))
                        {
                         //get balance
                            dos.writeUTF("your balance is..."+z.get(index).balance);
                            String nothing = dis.readUTF();
                        }
                        else if(userchoice.equalsIgnoreCase("Deposit"))
                        { String depositamount;int money;
                            while(true)
                            {
                                dos.writeUTF("Please enter valid amount:");
                                 depositamount = dis.readUTF();
                                money = Integer.parseInt(depositamount);
                                if(money > 0)
                                {
                                    break;
                                }
                              
                            }
                           
                             z.get(index).transactions.add("deposing "+depositamount);
                             z.get(index).balance=  z.get(index).balance+money;
                            dos.writeUTF("your balance is..."+z.get(index).balance);
                            String nothing = dis.readUTF();
                      
                        }
                        else if(userchoice.equalsIgnoreCase("Withdraw"))
                        { String depositamount;int money;
                           while(true)
                            {
                                dos.writeUTF("Please enter valid amount:");
                                 depositamount = dis.readUTF();
                                 money = Integer.parseInt(depositamount);
                                if(money < z.get(index).balance & money>0)
                                {
                                 break;   
                                }
                                
                            }  
                           z.get(index).transactions.add("withdrawing "+depositamount);
                            z.get(index).balance=  z.get(index).balance-money;
                          dos.writeUTF("your balance is.."+z.get(index).balance);
                           String nothing = dis.readUTF();
                         
                        }
                        else if(userchoice.equalsIgnoreCase("transfer money"))
                        {
                            while(true)
                            {
                            
                                 dos.writeUTF("internal or external transfer??");
                                  String transferchoice = dis.readUTF();
                                    if(transferchoice.equalsIgnoreCase("internal"))
                                        
                                    {boolean flag =true;  int dd,amm;String am;
                                    while(true){
                                    dos.writeUTF(" enter the ID you want ");
                                          
                                    String friend = dis.readUTF();
                                     dd = Integer.parseInt(friend);
                                   
                                   for(int d=0;d<z.size();d++){if(z.get(d).id==dd){flag=false;break;}
                                    }if(flag==false)break;
                                    }
                                       while(true){
                                    dos.writeUTF(" enter valid amount ");
                                          
                                     am = dis.readUTF();
                                     amm = Integer.parseInt(am);
                                     if(amm<z.get(index).balance & amm>0)break;
                                       
                                       }
                                   z.get(dd).balance=z.get(dd).balance+amm;
                                     z.get(index).balance=z.get(index).balance-amm;
                                     z.get(dd).transactions.add("receiving "+am+
                                             "from "+ z.get(index).id);
                                       z.get(index).transactions.add("transfering "+am+
                                             "to "+dd);
                                    dos.writeUTF("transaction done successfully");
                                    String nothing = dis.readUTF();
                                     
 break;                                 
                                    }
                                    else if(transferchoice.equalsIgnoreCase("external"))
                                    {
                  Socket n = new Socket("127.0.0.1", 1235);
                DataOutputStream o = new DataOutputStream(n.getOutputStream());
                DataInputStream i = new DataInputStream(n.getInputStream());
                    String ll,nothing,uu,r,amount;
                    int t;
                    i.readUTF();
                    o.writeUTF("external");
                                          do
                                           {  
                                           dos.writeUTF("enter the id");
                                            nothing = dis.readUTF(); 
                                            o.writeUTF(nothing);
                                            System.out.println(nothing);
                                            ll=i.readUTF();  System.out.println(ll);
                                           }while(ll.equalsIgnoreCase("qq"));
                                          
                                          while(true){
                                          dos.writeUTF("enter the amount");
                                         amount = dis.readUTF();
                                         t = Integer.parseInt(amount);
                                        if(z.get(index).balance>t & t>0)break;
                                           
                                                 }
                                         o.writeUTF(amount);
                                         uu=i.readUTF();
                                            
                          z.get(index).transactions.add("transfering "+amount
                                            + "to "+nothing+"in server2");
                          z.get(index).balance= z.get(index).balance-t;
                                     dos.writeUTF("transaction done successfully");
                                     r = dis.readUTF();
                                    break;
                                }
                            }
                        }
                        else if(userchoice.equalsIgnoreCase("transaction history"))
                        {        //get transaction history
                            String str = String.join("\n", z.get(index).transactions);
                            dos.writeUTF("transaction history\n"+str);
                            String nothing = dis.readUTF();
                           
                        }   
                        else if(userchoice.equalsIgnoreCase("exit"))
                        {
                            dos.writeUTF("Bye");
                            dos.close();
                            dis.close();
                        
                            c.close();
                        
                            break;
                        }   
                        else
                        {
                            continue;
                        }
                    }
            }
                    else if(choice.equalsIgnoreCase("create account"))
                    {
                    dos.writeUTF("enter full name: ");
                    String v = dis.readUTF();
                    
                    dos.writeUTF("enter password: ");
                    String pass = dis.readUTF();
                   int passd = Integer.parseInt(pass);
                    while(true)
                    {
                        dos.writeUTF("enter valid deposit: ");
                        String deposit = dis.readUTF();
                        int depositnum = Integer.parseInt(deposit);
                        if(depositnum > 0)
                         {
                           g.balance=depositnum; break;
                            
                         }
                        
                    }
                    g.accname=v;
                   
                    g.password=passd;
                    z.add(g);
                    g.id=z.indexOf(g);
                    dos.writeUTF("account created .. your id is "+g.id);
                    String nothing = dis.readUTF();
                }
                        
                   
                    }
                 
        } catch (IOException e)
        {
            System.out.println("Something went wrong");
        }



    }
}
public class Server1 {


    public static void main(String[] args) {
     try{
       
            ServerSocket s = new ServerSocket(1234);
          
     
            while (true)
            {
               
                Socket c = s.accept();
                 
                System.out.println("Client Arrived");
                clienthandler ch = new clienthandler(c);
              
                Thread t = new Thread(ch);
                t.start();
                 
            }
     } catch (IOException ex)
        {
            System.out.println("Something went wrong");
        }
    }
    
}
