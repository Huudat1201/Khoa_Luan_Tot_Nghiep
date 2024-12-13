package com.KLTN.Controller.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.KLTN.Entity.Category;
import com.KLTN.Entity.Manufacturer;
import com.KLTN.Model.BestSellerModel;
import com.KLTN.Model.ShowProduct;
import com.KLTN.Service.CategoryService;
import com.KLTN.Service.CommentService;
import com.KLTN.Service.ManufacturerService;
import com.KLTN.Service.OrderService;
import com.KLTN.Service.ProductService;
import com.KLTN.Service.SessionService;

@Controller
public class ListProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ManufacturerService manufacturerService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private CommentService commentService;

	@Autowired
	SessionService sessionService;

	@GetMapping("/danh-sach/{nameSearch}")
	public String index(@PathVariable("nameSearch") String nameSearch, Model model,
			@RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu,
			@RequestParam(name = "xep", required = false) String sort) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);

			Page<ShowProduct> listProduct = productService.getListProductByFilter(nameSearch, price, manu, sort,
					pageable, "danh-sach", "", "");

			model.addAttribute("listProduct", listProduct);
			model.addAttribute("price", price);
			model.addAttribute("manu", manu);
			model.addAttribute("sort", sort);
			model.addAttribute("nameSearch", nameSearch);

			Category category = categoryService.getCategoryByNameSearch(nameSearch);
			model.addAttribute("inforCategory", category);
		} catch (Exception e) {
			return "redirect:/index";
		}

		return "user/list/list_by_category";
	}

	@GetMapping("/uu-dai")
	public String sales(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu,
			@RequestParam(name = "xep", required = false) String sort) {

		Pageable pageable = PageRequest.of(p.orElse(0), 15);

		Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable, "uu-dai",
				"", "");
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("price", price);
		model.addAttribute("manu", manu);
		model.addAttribute("sort", sort);

		return "user/list/list_by_sales";
	}

	@GetMapping("/tim-kiem")
	public String search(Model model, @RequestParam(name = "q", required = false) String name,
			@RequestParam("p") Optional<Integer> p, @RequestParam(name = "gia", required = false) String price,
			@RequestParam(name = "hang", required = false) String manu,
			@RequestParam(name = "xep", required = false) String sort,
			@RequestParam(name = "category", required = false) String category) {
		try {
			Pageable pageable = PageRequest.of(p.orElse(0), 15);

			if (category == null) {
				category = "";
			}

			if (name == null) {
				name = "";
			}

			Page<ShowProduct> listProduct = productService.getListProductByFilter("", price, manu, sort, pageable,
					"tim-kiem", name.trim(), category);
			if (!name.trim().isEmpty()) {
				model.addAttribute("title", "- " + name.trim());
				model.addAttribute("name", name.trim());
			}
			model.addAttribute("cate", category);
			model.addAttribute("listProduct", listProduct);
			model.addAttribute("price", price);
			model.addAttribute("manu", manu);
			model.addAttribute("sort", sort);

			List<Category> listCategory = categoryService.findAll();
			model.addAttribute("listCategory", listCategory);

		} catch (Exception e) {
			return "redirect:/index";
		}

		return "user/list/list_by_search";
	}

	@ModelAttribute("listManu")
	public List<Manufacturer> listManu() {
		List<Manufacturer> list = manufacturerService.findAll();
		return list;
	}

	@ModelAttribute("listBestSeller")
	public List<ShowProduct> getListBestSeller(Model model) {
		Pageable topFour = PageRequest.of(0, 4);

		List<BestSellerModel> list = orderService.getListBestSellerProduct(topFour);

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (BestSellerModel bestSeller : list) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService
					.getAllStarCommentByProductNameSearch(bestSeller.getProduct().getNamesearch());
			showProduct.setProduct(bestSeller.getProduct());
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}
		return listProduct;
	}

}
