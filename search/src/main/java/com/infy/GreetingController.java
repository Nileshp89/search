package com.infy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {

	
	@Autowired
	private CustomerRepository repository;
	
    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        
        repository.save(new Customer("Alice", "Smith"));
		repository.save(new Customer("Bob", "Smith"));
		repository.save(new Customer(name, name));
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		
		
		System.out.println(repository.findByFirstName("Alice"));
		
        
        return "greeting";
    }

}