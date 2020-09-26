package com.jsoft.ams.gl.enums;

public enum AccountType {
	
	ASSETS(1), 
		QUICK_ASSETS(10), 
			CASH(100), 
			BANK(101),
		CURRENT_ASSET(11), 
			RECEIVABLE(110),
			INVENTORY(111),
		FIXED_ASSETS(12),
			ACCUMULATED_DEPRECIATION(121),
	LIABILITIES(2),
		CURRENT_LIABILITIES(20),		
			PAYABLE(200),
		LONG_TERM_LIABILITIES(21),
	EQUITY(3),
		CAPITAL(30),
		DRAWING(31),
		RETAINED_EARNING(32),
	EXPENSE(4),
	//EXPENSE_PURCHASES = 400;
	//EXPENSE_COST_OF_GOODS_SOLD = 401;
		OPERATING(40),
		NON_OPERATING(41),
	REVENUE(5),
		SALES(50),
		INCOME(51);

	
	private Integer code;
	
	private AccountType(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return this.code;
	}
}
