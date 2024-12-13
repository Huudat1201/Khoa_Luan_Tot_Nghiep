package com.KLTN.Service.Impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.KLTN.Entity.Category;
import com.KLTN.Entity.Manufacturer;
import com.KLTN.Entity.Product;
import com.KLTN.Entity.User;
import com.KLTN.Model.ProductModel;
import com.KLTN.Model.ShowProduct;
import com.KLTN.Repository.CategoryRepository;
import com.KLTN.Repository.ManufacturerRepository;
import com.KLTN.Repository.ProductRepository;
import com.KLTN.Repository.UserRepository;
import com.KLTN.Service.CommentService;
import com.KLTN.Service.ProductService;

@Service
public class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ManufacturerRepository manufacturerRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CommentService commentService;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private UserRepository uesrRepository;

	
	/*
	 *  Danh sách tất cả các sản phẩm.
	 */
	@Override
	public List<Product> findAll() {
		return productRepository.getListProduct();
	}

	
	/*
	 *  Danh sách các sản phẩm mới nhất.
	 */
	@Override
	public List<Product> getListLatestProduct() {
		return productRepository.getListLatestProduct();
	}

	
	/*
	 *  Danh sách các sản phẩm được xem nhiều nhất.
	 */
	@Override
	public List<Product> getListViewsProduct() {
		return productRepository.getListViewsProduct();
	}

	
	/*
	 *  Danh sách phân trang các sản phẩm dựa trên tên tìm kiếm.
	 */
	@Override
	public Page<Product> getListProductByNameSearch(String nameSearch, Pageable pageable) {
		return productRepository.getListProductByNameSearch(nameSearch, pageable);
	}

	
	/*
	 *  Danh sách sản phẩm demo dựa trên tên tìm kiếm.
	 */
	@Override
	public List<Product> getDemo(String nameSearch) {
		return productRepository.getListDemo(nameSearch);
	}

	
	/*
	 *  Danh sách phân trang các sản phẩm trong khoảng giá dựa trên tên tìm kiếm.
	 */
	@Override
	public Page<Product> getListProductByPrice(String nameSearch, int minPrice, int maxPrice, Pageable pageable) {
		return productRepository.getListProductByPrice(nameSearch, minPrice, maxPrice, pageable);
	}

	
	/*
	 * Danh sách phân trang các sản phẩm dựa trên các tiêu chí lọc như 
	 * giá, nhà sản xuất, thứ tự, trạng thái, tên, và danh mục.
	 */
	@Override
	public Page<ShowProduct> getListProductByFilter(String nameSearch, String price, String manu, String sort,
			Pageable pageable, String status, String name, String category) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		Root<Product> from = cq.from(Product.class);

		Predicate preStatus;

		if (status.equals("danh-sach")) {
			preStatus = cb.like(from.get("category").get("Namesearch"), "%" + nameSearch + "%");
		} else if (status.equals("tim-kiem")) {
			Predicate preCategory = null;
			if (name.length() != 0) {
				name = "%" + name + "%";
			}
			preStatus = cb.like(from.get("name"), name);
			if (category.length() != 0) {
				preCategory = cb.like(from.get("category").get("Namesearch"), "%" + category + "%");
				preStatus = cb.and(preStatus, preCategory);
			}
		} else {
			preStatus = cb.notEqual(from.get("sales"), 0);
		}

		Predicate preActive = cb.equal(from.get("active"), 1);
		// Predicate preActive = cb.equal(from.get("active"), 1);
		Predicate preDeleteDay = cb.isNull(from.get("Deleteday"));

		int check = 0;
		Predicate prePrice = null;
		Predicate preManu = null;

		if (price != null) {
			int min = 0;
			int max = 999999999;
			if (price.equals("1")) {
				max = 50000;
			} else if (price.equals("2")) {
				min = 50000;
				max = 150000;
			} else if (price.equals("3")) {
				min = 150000;
				max = 300000;
			} else if (price.equals("4")) {
				min = 300000;
				max = 500000;
			} else {
				min = 500000;
			}
			check = 1;
			prePrice = cb.between(from.get("price"), min, max);
		}

		if (manu != null) {
			preManu = cb.equal(from.get("manufacturer").get("id"), Integer.parseInt(manu));
			if (check == 1) {
				check = 3;
			} else {
				check = 2;
			}
		}

		if (check == 1) {
			cq.where(prePrice, preActive, preDeleteDay, preStatus);
		} else if (check == 2) {
			cq.where(preManu, preActive, preDeleteDay, preStatus);
		} else if (check == 3) {
			cq.where(prePrice, preManu, preActive, preDeleteDay, preStatus);
		} else {
			cq.where(preActive, preDeleteDay, preStatus);
		}

		if (sort != null) {
			if (sort.equals("0")) {
				cq.orderBy(cb.desc(from.get("Createday")));
			}
			if (sort.equals("1")) {
				cq.orderBy(cb.asc(from.get("name")));
			}
			if (sort.equals("2")) {
				cq.orderBy(cb.desc(from.get("name")));
			}
			if (sort.equals("3")) {
				cq.orderBy(cb.asc(from.get("price")));
			}
			if (sort.equals("4")) {
				cq.orderBy(cb.desc(from.get("price")));
			}
		} else {
			cq.orderBy(cb.desc(from.get("Createday")));
		}

		TypedQuery<Product> q = em.createQuery(cq);

		List<Product> countAllItems = q.getResultList();

		q.setFirstResult(Math.toIntExact(pageable.getOffset()));
		q.setMaxResults(pageable.getPageSize());

		List<Product> getAllItems = q.getResultList();

		List<ShowProduct> listProduct = new ArrayList<ShowProduct>();

		for (Product product : getAllItems) {
			ShowProduct showProduct = new ShowProduct();
			int totalStar = commentService.getAllStarCommentByProductNameSearch(product.getNamesearch());
			showProduct.setProduct(product);
			showProduct.setTotalStar(totalStar);
			listProduct.add(showProduct);
		}

		Page<ShowProduct> page = new PageImpl<ShowProduct>(listProduct, pageable, countAllItems.size());

		return page;
	}

	
	/*
	 *  Danh sách các sản phẩm liên quan dựa trên ID sản phẩm.
	 */
	@Override
	public List<Product> getListProductRelated(int id) {
		return productRepository.getListProductRelated(id);
	}

	
	/*
	 *  Danh sách các sản phẩm đang giảm giá.
	 */
	@Override
	public List<Product> getListProductSales() {
		return productRepository.getListProductSales();
	}

	
	/*
	 *  Tạo một sản phẩm.
	 */
	@Override
	public ProductModel createProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = uesrRepository.findUserByEmail(username);

		Product product = new Product();
		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setImage4(productModel.getImage4());
		product.setImage5(productModel.getImage5());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setCreateday(timestamp.toString());
		product.setPersoncreate(temp.getId());
		product.setSales(productModel.getSales());

		Manufacturer manufacturer = manufacturerRepository.findById(productModel.getManuId()).get();
		Category category = categoryRepository.findById(productModel.getCateId()).get();

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productRepository.save(product);

		return productModel;
	}

	
	/*
	 *  Cập nhật sản phẩm mới.
	 */
	@Override
	public ProductModel updateProduct(ProductModel productModel) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		User temp = uesrRepository.findUserByEmail(username);

		Product product = productRepository.findById(productModel.getId()).get();

		product.setCode(productModel.getCode());
		product.setName(productModel.getName());
		product.setPrice(productModel.getPrice());
		product.setQuality(productModel.getQuality());
		product.setDescription(productModel.getDescription());
		product.setSpecification(productModel.getSpecification());
		product.setImage1(productModel.getImage1());
		product.setImage2(productModel.getImage2());
		product.setImage3(productModel.getImage3());
		product.setImage4(productModel.getImage4());
		product.setImage5(productModel.getImage5());
		product.setActive(productModel.isActive());
		product.setNamesearch(productModel.getNameSearch());
		product.setUpdateday(timestamp.toString());
		product.setPersonupdate(temp.getId());
		product.setSales(productModel.getSales());

		Manufacturer manufacturer = manufacturerRepository.findById(productModel.getManuId()).get();
		Category category = categoryRepository.findById(productModel.getCateId()).get();

		product.setCategory(category);
		product.setManufacturer(manufacturer);

		productRepository.save(product);
		return productModel;
	}

	
	/*
	 *  Lấy một sản phẩm theo cái id truyền vào.
	 */
	@Override
	public ProductModel getOneProductById(Integer id) {
		Product product = productRepository.findById(id).get();
		ProductModel productModel = new ProductModel();
		productModel.setId(product.getId());
		productModel.setCode(product.getCode());
		productModel.setName(product.getName());
		productModel.setPrice(product.getPrice());
		productModel.setQuality(product.getQuality());
		productModel.setImage1(product.getImage1());
		productModel.setImage2(product.getImage2());
		productModel.setImage3(product.getImage3());
		productModel.setImage4(product.getImage4());
		productModel.setImage5(product.getImage5());
		productModel.setNameSearch(product.getNamesearch());
		productModel.setActive(product.isActive());
		productModel.setManuId(product.getManufacturer().getId());
		productModel.setCateId(product.getCategory().getId());
		productModel.setDescription(product.getDescription());
		productModel.setSpecification(product.getSpecification());
		productModel.setSales(product.getSales());
		return productModel;
	}

	
	/*
	 *  Lấy một sản phẩm dựa trên tên tìm kiếm.
	 */
	@Override
	public Product getProductByNameSearch(String nameSearch) {
		return productRepository.getProductByNameSearch(nameSearch);
	}
	

	/*
	 *  Cập nhật số lượt xem dựa trên tên tìm kiếm.
	 */
	@Override
	public void updateView(String nameSearch) {
		Product product = productRepository.getProductByNameSearch(nameSearch);
		int view = product.getViews();
		product.setViews(view + 1);
		productRepository.save(product);
	}

	
	/*
	 *  Lưu sản phẩm xuống cơ sở dữ liệu.
	 */
	@Override
	public void saveProduct(Product product) {
		productRepository.save(product);
	}

	
	/*
	 *  Xóa một sản phẩm dựa trên cái id truyền vào.
	 */
	@Override
	public void deleteProduct(Integer id) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails) principal).getUsername();
		User temp = uesrRepository.findUserByEmail(username);
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		Product product = productRepository.findById(id).get();
		product.setDeleteday(timestamp.toString());
		product.setPersondelete(temp.getId());
		productRepository.save(product);
	}

}
