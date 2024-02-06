package Ex4_6513110;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

class Shop { 
    private int itemsToRefill;      // items to refill in odd day    
    private int maxDays;       // max day for simulation 
    private PriorityQueue<Customer>  orderQueue; 
    private ArrayDeque<Customer> billingQueue; 
    
    public Shop(int itemsToRefill,int maxDays){
        this.itemsToRefill = itemsToRefill;
        this.maxDays = maxDays;
    }

    public void simulation(){
        int order , item = 0;
        int customerCount = 1;
        Random rand = new Random();
        orderQueue = new PriorityQueue<>(new SortCustomerByOrder());
        billingQueue = new ArrayDeque<Customer>();
        for(int day = 0 ; day <= maxDays ; day++){
            if(day == 0){
                orderQueue = new PriorityQueue<>(new SortCustomerByOrder()
                                                    .thenComparing(new SortCustomerByID()));
                System.out.println("=== Day 0 : Customer arrival ===");
                for(int i = 1 ; i <= 5 ; i++){
                    order = rand.nextInt(20);
                    System.out.printf("[Customer : %d      Order : %d]\n",i,order);
                    orderQueue.add(new Customer(i,order));
                    customerCount++;
                }
                continue;
            }
            if(day == 1)System.out.println("\n===Simulation===");
            System.out.printf("\nDay %d\n",day);
            if(day % 2 != 0 ){
                item += itemsToRefill;
                System.out.printf("Refilling >> Remaining items = %d \n",item);
            }
            Customer newCustomer = new Customer(customerCount, rand.nextInt(20));
            System.out.println("New arrival >> " + newCustomer);
            orderQueue.add(newCustomer);
            customerCount++;

            ArrayList <Customer> Failpack = new ArrayList<Customer>();

            for(int i = 0 ; i < 2 ; i++){
                if(orderQueue.peek().getOrder() > item){    
                    System.out.printf("Packing %d >> " + orderQueue.peek()+ "   failure\n",i+1);
                    Failpack.add(orderQueue.poll());
                }else{
                    item -= orderQueue.peek().getOrder();
                    System.out.printf("Packing %d >> " + orderQueue.peek() + "   Success     Remaining Items = %d lots\n",i+1,item);
                    billingQueue.add(orderQueue.poll());
                }
                
            }
            for (int i = 0; i < Failpack.size(); i++) {
                orderQueue.add(Failpack.get(i));
            }
            Failpack.clear();
                

            if(day %2 == 0){
                System.out.println("Billing    >> " + billingQueue.pollFirst());
            }
            System.out.println();

            if(day == maxDays){
                System.out.println("===Remaining customers in order queue===");
                while (orderQueue.size() > 0 ) orderQueue.poll().print();
                System.out.println("\n===Remaining customer in billing queue (Latest to earliest) ===");
                while (billingQueue.size() > 0 ) billingQueue.pollLast().print();
            }
            
        }
    }       
}