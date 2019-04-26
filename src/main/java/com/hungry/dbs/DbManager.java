package com.hungry.dbs;

public enum DbManager {
	USERS("users"), ORDERS("orders"), PRODUCTS("products"), USERS_TYPES("users_types");
	private final String PREFIX = "hungry_";
	private final String DB = "hungry.";
	private String table, SQL;

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
		SQL += "first_name varchar(100),last_name varchar(100),user_password text,user_img text,";
		SQL += "phone_number text,registration_date date,";
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
		SQL += "product_name varchar(300),product_types json,product_img text,";
		SQL += "price double,rating double,total_raters int,raters json,";
		SQL += "discount double,discounted_sold_price double,discounted_sold_peices int,";
		SQL += "total_sold_price double,total_sold_peices int,total_orders int,";
		SQL += "order_ids json,total_reviewers int ,reviewers_ids json,";
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
