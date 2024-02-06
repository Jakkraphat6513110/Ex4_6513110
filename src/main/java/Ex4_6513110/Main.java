package Ex4_6513110;

import java.util.*;

public class Main {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter refill items");
        int reItems = scan.nextInt();
        System.out.println("Enter max days");
        int maxDays = scan.nextInt();
        Shop inilizeShop = new Shop(reItems, maxDays);
        inilizeShop.simulation();
    }
    
}