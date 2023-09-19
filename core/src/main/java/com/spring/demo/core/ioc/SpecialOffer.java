package com.spring.demo.core.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SpecialOffer {

	Product masterProduct;
	Product variantProduct;

	// The bean name can be used as a qualifier and also the explicitly specified qualifier
	// If no qualifier specified it will user property name to find bean
	public SpecialOffer(@Qualifier("variantProduct") Product masterProduct,
			@Qualifier("masterProductBean") Product variantProduct) {
		this.masterProduct = masterProduct;
		this.variantProduct = variantProduct;
	}

	public Product getMasterProduct() {
		return masterProduct;
	}

	public void setMasterProduct(Product masterProduct) {
		this.masterProduct = masterProduct;
	}

	public Product getVariantProduct() {
		return variantProduct;
	}

	public void setVariantProduct(Product variantProduct) {
		this.variantProduct = variantProduct;
	}

}
