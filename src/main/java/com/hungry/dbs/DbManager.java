package com.hungry.dbs;

public enum DbManager {
	USERS("users"), ORDERS("orders"), PRODUCTS("products"), USERS_TYPES("users_types");
	private final String PREFIX = "hungry_";
	private final String DB = "hungry.";
	private String table, SQL;

	public String value() {
		return PREFIX + this.table;
	}

	public String getQuery() {

		if (name().equalsIgnoreCase(USERS.toString()))
			return this.users();

		if (name().equalsIgnoreCase(ORDERS.toString()))
			return this.orders();
		if (name().equalsIgnoreCase(PRODUCTS.toString()))
			return this.products();
		if (name().equalsIgnoreCase(USERS_TYPES.toString()))
			return this.user_types();
		return "user " + DB;
	}

	private String users() {

		SQL = "create table if not exists " + DB + PREFIX + this.table
				+ "(user_id int(22) auto_increment not null primary key,";
		SQL += "first_name varchar(100),last_name varchar(100),user_password text,user_img text, user_img_location text,";
		SQL += "phone_number text,email_address varchar(500),registration_date date,";
		SQL += "access_token text,expires int,access_date timestamp, order_ids json)";
		return SQL;
	}

	private String orders() {
		SQL = "create table if not exists " + DB + PREFIX + this.table
				+ "(order_id int(22) auto_increment not null primary key,";
		SQL += "products json,total_products int,total_price double,";
		SQL += "delevery_type varchar(100),delevery_user_id int(22),";
		SQL += "order_date date,promo_code varchar(200),destinatio json,pickup json)";
		return SQL;

	}

	private String products() {
		SQL = "create table if not exists " + DB + PREFIX + this.table
				+ "(product_id int(22) auto_increment not null primary key,";
		SQL += "product_name varchar(300),product_type varchar(50),product_imgs json,product_local_imgs json,product_detail json,";
		SQL += "price double DEFAULT 0 ,rating double DEFAULT 0,total_raters int DEFAULT 0,raters json,";
		SQL += "discount double DEFAULT 0,discounted_sold_price double DEFAULT 0,discounted_sold_peices int DEFAULT 0,buyers_in_discount json,";
		SQL += "total_sold_price double DEFAULT 0,total_sold_peices int DEFAULT 0,";
		SQL += "buyers json,total_reviewers int DEFAULT 0,reviewers_ids json, ";
		SQL += "launch timestamp)";
		return SQL;

	}

	private String user_types() {
		SQL = "create table if not exists " + DB + PREFIX + this.table
				+ "(user_type_id int auto_increment not null primary key,";
		SQL += "type varchar(100))";
		return SQL;

	}

	private DbManager(String table) {
		this.table = table;
	}

}
