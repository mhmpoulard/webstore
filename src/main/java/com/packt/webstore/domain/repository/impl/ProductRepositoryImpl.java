package com.packt.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private List<Product> listOfProducts = new ArrayList<>();
	
	public ProductRepositoryImpl() {
		Product iphone = new Product("P1234", "iPhone 5s", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5s smartphone with 4.0 inch 640x1136 display and 8 megapixel rear camera");
		iphone.setCategory("Smart Phone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		
		
		Product laptop_dell = new Product("P1235", "Dell Inpiron", new BigDecimal(700));
		laptop_dell.setDescription("Dell Inspiron 14 inch Laptop (Black) with 3rd Generation Intel Core Processors");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(1000);
		
		Product tablet_Nexus = new Product("P1236", "Nexus 7", new BigDecimal(300));
		tablet_Nexus.setDescription("Google Nexus 7 is the lightest 7 inch tablet with a quad core Qualcomm Snapdragon S4 Pro Processor");
		tablet_Nexus.setCategory("Tablet");
		tablet_Nexus.setManufacturer("Google");
		tablet_Nexus.setUnitsInStock(1000);
		
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet_Nexus);
		
		
	}
	
	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return listOfProducts;
	}

	@Override
	public Product getProductById(String productID) {
		// TODO Auto-generated method stub
		Product productById = null;
		
		for(Product product :listOfProducts) {
			if(product != null && product.getProductId() != null && product.getProductId().equals(productID)) {
				productById = product;
				break;
			}
		}
		if(productById == null) {
			throw new IllegalArgumentException("No products found with the product id: "+productID);
		}
		return productById;
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		// TODO Auto-generated method stub
		List<Product> productsByCategory = new ArrayList<>();
		
		for(Product product: listOfProducts) {
			if(category.equalsIgnoreCase(product.getCategory())) {
				productsByCategory.add(product);
			}
		}
		return productsByCategory;
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		// TODO Auto-generated method stub
		Set<Product> productByBrand = new HashSet<>();
		Set<Product> productByCategory = new HashSet<>();
		
		Set<String> criterias = filterParams.keySet();
		
		if(criterias.contains("brand")) {
			for(String brandname: filterParams.get("brand")) {
				for(Product product: listOfProducts) {
					if(brandname.equals(product.getManufacturer())) {
						productByBrand.add(product);
					}
				}
			}
		}
		
		
		if(criterias.contains("category")) {
			for(String category: filterParams.get("category")) {
				productByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		productByCategory.retainAll(productByBrand);
		return productByCategory;
	}

	@Override
	public void addProduct(Product product) {
		// TODO Auto-generated method stub
		listOfProducts.add(product);
	}

}
