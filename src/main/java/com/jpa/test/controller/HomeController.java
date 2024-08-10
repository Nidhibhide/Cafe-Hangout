package com.jpa.test.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jpa.test.entities.bill;
import com.jpa.test.entities.customer;
import com.jpa.test.entities.product;
import com.jpa.test.entities.user;
import com.jpa.test.repo.BillCountRepo;
import com.jpa.test.repo.BillRepo;
import com.jpa.test.repo.BillUniqueRepo;
import com.jpa.test.repo.CategoryRepo;
import com.jpa.test.repo.CustomerRepo;
import com.jpa.test.repo.GrantBillRepo;
import com.jpa.test.repo.IdBillRepo;
import com.jpa.test.repo.ProductCountRepo;
import com.jpa.test.repo.ProductRepo;
import com.jpa.test.repo.ReportRepo;
import com.jpa.test.repo.SearchProductRepo;
import com.jpa.test.repo.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@Autowired
	private ProductRepo repo;

	@Autowired
	private CategoryRepo repo1;

	@Autowired
	private CustomerRepo repo2;

	@Autowired
	private SearchProductRepo repo3;

	@Autowired
	private UserRepo repo4;

	@Autowired
	private BillRepo repo5;

	@Autowired
	private IdBillRepo repo6;

	@Autowired
	private BillUniqueRepo repo7;

	@Autowired
	private GrantBillRepo repo8;

	@Autowired
	private ProductCountRepo repo9;

	@Autowired
	private BillCountRepo repo10;

	@Autowired
	private ReportRepo repo11;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	private int newid;
	private String cust_name;

	@GetMapping("/")
	public String home() {
		return "home";
	}

	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@GetMapping("/base")
	public String base() {
		return "base";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/forgotpass")
	public String forgotpass() {
		return "forgotpass";
	}

	@GetMapping("/user/frame")
	public String base1() {
		return "base1";
	}

	// reports
	@GetMapping("/user/report")
	public String report(Model model) {
		Iterable<com.jpa.test.entities.grantbill> res=repo11.findAll();
		model.addAttribute("res",res);
		return "report";
	}

	@GetMapping("/user/dashboard")
	public String dashboard(Model model) {
		int c1 = repo9.ProductCount();
		int c2 = repo10.CountBill();
		model.addAttribute("c1", c1);
		model.addAttribute("c2", c2);
		return "dashboard";
	}

	@GetMapping("/user/add_customer")
	public String cust() {
		return "add_customer";
	}

	@GetMapping("/user/add_product")
	public String addproduct() {
		return "add_product";
	}

	@GetMapping("/user/update_product")
	public String updateproduct() {
		return "update_product";
	}

	@GetMapping("/user/place_order")
	public String order(Model model) {

		model.addAttribute("customers", repo2.findAll());
		Integer lastbillid = repo6.findMaxbillId();

		if (lastbillid != null) {
			System.out.println(lastbillid);
			newid = lastbillid + 1;
		} else

		{
			newid = 1;
			System.out.println(lastbillid);

		}
		model.addAttribute("newid", newid);
		return "place_order";
	}

	// product insert
	@PostMapping("/user/register")
	public String register(@ModelAttribute product p, Model model) {
		System.out.println(p);
		repo.save(p);
		// session.setAttribute("message","Product Added Sucessfully...");
		model.addAttribute("success", true);
		return "add_product";
		// return "redirect:/";
	}

	// save grant bill
	@PostMapping("/user/grantbill")
	public String grantbill(@ModelAttribute com.jpa.test.entities.grantbill g) {
		// System.out.println(g);
		repo8.save(g);

		return "home";
		// return "redirect:/";
	}

	// user insert
	@PostMapping("/signup")
	public String userreg(@ModelAttribute user p, Model model) {
		System.out.println(p);
		String password = passwordEncoder.encode(p.getPass());
		p.setPass(password);
		repo4.save(p);
		// session.setAttribute("message","Product Added Sucessfully...");
		model.addAttribute("success", true);
		return "signup";
		// return "redirect:/";
	}

	// customer insert
	@PostMapping("/user/customer")
	public String cust(@ModelAttribute customer p, Model model) {
		System.out.println(p);
		repo2.save(p);
		// session.setAttribute("message","Customer Added Sucessfully...");
		model.addAttribute("success", true);
		return "add_customer";
		// return "redirect:/";
	}

	// product update
	@PostMapping("/user/update/{id}")
	public String update(@PathVariable int id, @ModelAttribute product p, Model model) {
		System.out.println("123");
		Optional<product> optional = repo.findById(id);
		product product1 = optional.get();
		product1.setCategory(p.getCategory());
		product1.setPrice(p.getPrice());
		product1.setProductName(p.getProductName());
		product result = repo.save(product1);
		System.out.println(result);
		model.addAttribute("success", true);
		return "update_product";
	}

	@PostMapping("/user/get_products")
	public String getAllProduct(Model model, @RequestParam("category") String category,
			@RequestParam("custname") String name, HttpSession session) {
		model.addAttribute("cust_name", name);
		System.out.println(name);
		cust_name = name;
		Iterable<product> pro = repo1.findByCategory(category);
		System.out.println(pro);
		model.addAttribute("pro", pro);
		session.setAttribute("selectedCategory", category);
		model.addAttribute("newid", newid);
		model.addAttribute("customers", repo2.findAll());
		return "place_order";
		// Thymeleaf template name
	}

	// enter sub bill and fetch it
	@PostMapping("/user/bill")
	public String subbill(@ModelAttribute bill b, Model model) {
		model.addAttribute("cust_name", cust_name);
		model.addAttribute("newid", newid);
		model.addAttribute("customers", repo2.findAll());
		System.out.println(b);
		repo5.save(b);
		List<bill> pro1 = repo7.findByCustname(cust_name);
		double total = pro1.stream().mapToDouble(bill::getTotal).sum();
		System.out.println(pro1);
		model.addAttribute("pro1", pro1);
		model.addAttribute("total", total);
		return "place_order";
		// return "redirect:/";
	}

	// display info product wise for update
	@PostMapping("/user/search")
	public String getAlldata(Model model, @RequestParam("productName") String productName) {

		System.out.println(productName);
		List<product> res = repo3.findByProductName(productName);
		System.out.println(res);

		if (res.isEmpty()) {
			model.addAttribute("notFound", true); // Set the flag indicating data not found
		} else {
			model.addAttribute("res", res);
		}
		return "update_product"; // Thymeleaf template name
	}

	// display info category wise for delete
	@PostMapping("/user/searchcategory")
	public String getAll(Model model, @RequestParam("category") String category) {

		System.out.println(category);
		List<product> res = repo1.findByCategory(category);
		System.out.println(res);
		if (res.isEmpty()) {
			model.addAttribute("notFound", true); // Set the flag indicating data not found
		} else {
			model.addAttribute("res", res);
		}
		return "delete_product"; // Thymeleaf template name
	}

	// display product into table for delete
	@GetMapping("/user/delete_product")
	public String getdata(Model model) {
		Iterable<product> products = repo.findAll();
		model.addAttribute("products", products);
		return "delete_product"; // Thymeleaf template name
	}

	// product delete
	@GetMapping("/user/delete/{id}")
	public String delete(@PathVariable int id, Model model) {
		repo.deleteById(id);
		// System.out.println(result);
		model.addAttribute("success", true);
		return "delete_product";
	}

}
