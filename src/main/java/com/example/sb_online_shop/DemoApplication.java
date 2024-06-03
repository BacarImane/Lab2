package com.example.sb_online_shop;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.sb_online_shop.Domaine.CustomerRepository;
import com.example.sb_online_shop.Domaine.Item;
import com.example.sb_online_shop.Domaine.ItemRepository;
import com.example.sb_online_shop.Domaine.OrderRepository;
import com.example.sb_online_shop.Domaine.Product;
import com.example.sb_online_shop.Domaine.ProductRepository;
import com.example.sb_online_shop.Domaine.Order;
import com.example.sb_online_shop.Domaine.Customer;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {
private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	private final CustomerRepository crepository;
	private final OrderRepository orepository;
	private final ItemRepository iRepository;
	private final ProductRepository pRepository;


	public DemoApplication(CustomerRepository cRepository, OrderRepository oRepository,ItemRepository iRepository,ProductRepository pRepository){
		this.crepository = cRepository;
		this.orepository = oRepository;
		this.iRepository=iRepository;
		this.pRepository=pRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		logger.info("Online Shop started at http://localhost:8080/");
	}

	@Override
	public void run(String... args) throws Exception {
		Customer c1 = new Customer("Bugs Bunny", "New York", 59000);
		Customer c2 = new Customer("Daffy Duck", "Los Angeles", 37000);
		Customer c3 = new Customer("Porky Pig", "Miami", 28000 );
		crepository.saveAll(Arrays.asList(c1,c2, c3));
		
		Order o1 = new Order(100, c1);
		Order o2 = new Order(300, c1);
		Order o3 = new Order(200, c2);
		Order o4 = new Order(140, c3);
		orepository.saveAll(Arrays.asList(o1, o2, o3, o4));

		Product p1 = new Product("TV", "plasma led ", "wow belle image", 100);
		Product p2 = new Product("Phone", " ipgone 15 ", "wow belle image", 300);
		Product p3 = new Product("Shirt", "Coton ", "wow belle image", 500);
		Product p4 = new Product("Shoes", "ptaille 45", "wow belle image", 760);
		Product p5 = new Product("TV", "plasma led ", "wow belle image", 50);
		pRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

	
		Item i1 = new Item(1, 100, o1, p1);
		Item i2 = new Item(1, 300, o4, p2);
		Item i3 = new Item(1, 500, o2, p3);
		Item i4 = new Item(1, 760, o3, p4);
		Item i5 = new Item(1, 50, o3, p5);

		iRepository.saveAll(Arrays.asList(i1, i2, i3, i4,i5));

		int i_order = 0;
		int p_price=0;

		System.out.println("----- All Orders ------");
		for (Order o : orepository.findAll()) {
			i_order++;
			System.out.print("customer "+o.getCustomer().getFullname()+":\t  "+" order "+i_order+" : \t ");
			System.out.print("[");
				for(Item item : o.getItems()){
					System.out.print(item.getProduct().getName()+"("+item.getPrice()+"€)");
					p_price+=item.getPrice();
				}
				System.out.print("] \t Total : "+p_price) ;
			    p_price=0;

			
			System.out.println(" ");
		}
		System.out.println("-----             ------");

	}

}
