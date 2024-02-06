package Ex4_6513110;

import java.util.ArrayDeque;
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
        int customerCount = 0;
        Random rand = new Random();
        orderQueue = new PriorityQueue<>(new SortCustomerByOrder());
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
                System.out.print(orderQueue);
            }
            if(day == 1)System.out.println("===Simulation===");
            System.out.printf("Day %d\n",day);
            if(day % 2 != 0 ){
                item += itemsToRefill;
                System.out.printf("Refilling >> Remaining items = %d \n",item);
            }
            Customer newCustomer = new Customer(customerCount, rand.nextInt(20));
            System.out.println("New arrival >> " + newCustomer + "\n");
            orderQueue.add(newCustomer);
            customerCount++;

            for(int i = 1 ; i <= orderQueue.size() ; i++){
                if(orderQueue.peek().getOrder() > item){
                    System.out.printf("Packing %d >> " + orderQueue.peek() + "failure\n",i);
                    break;
                }else{
                    item -= orderQueue.peek().getOrder();
                    System.out.printf("Packing %d >> " + orderQueue.poll() + "Success     Remaining Items = %d\n",i,item);
                }
            }
            
        }
    }       
}