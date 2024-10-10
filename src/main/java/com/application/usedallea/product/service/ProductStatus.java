package com.application.usedallea.product.service;

public enum ProductStatus {
	판매중("해당 상품이 '판매중'으로 변경되었습니다."),
	판매완료("해당 상품이 '판매완료'로 변경되었습니다."),
	삭제("해당 상품이 '삭제'되었습니다.");

	private final String message;

	ProductStatus(String message){
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
