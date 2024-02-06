package Ex4_6513110;
import java.util.*;


public class Customer implements Comparable<Customer> { 
    private static int runningID = 1;   // for running customer ID 
    private int ID; 
    private int order;   // order amount (random value 1-20) 


    Customer(int ID,int order){
        this.ID = ID;
        this.order = order;
    }

    public void setRunningID(int n){
        runningID = n;
    }

    public int getRunningID(){
        return runningID;
    }

    public int getID(){
        return ID;
    }

    public int getOrder(){
        return order;
    }

    @Override
    public String toString(){
        return String.format("[Customer : %d      Order : %d]",ID,order);   
    }

    @Override
    public int compareTo(Customer other) {
        return order - other.order;
    }
}

class SortCustomerByOrder implements Comparator<Customer>{
    public int compare(Customer c1,Customer c2){
        return c2.getOrder() - c1.getOrder();
    }
}

class SortCustomerByID implements Comparator<Customer>{
    public int compare(Customer c1,Customer c2){
        return c1.getID() - c2.getID();
    }
}

