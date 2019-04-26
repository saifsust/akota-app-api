package com.hungry.util;

public enum Type {

	ADMIN, SUB_ADMIN, USER, NOT_FOUND;

	public int value() {
		switch (this) {
		case ADMIN:
			return 800;
		case SUB_ADMIN:
			return 801;
		case USER:
			return 802;
		case NOT_FOUND:
		default:
			return 404;
		}
	}

	public static Type type(int code) {

		switch (code) {
		case 800:
			return ADMIN;
		case 801:
			return SUB_ADMIN;
		case 802:
			return USER;
		case 404:
		default:
			return NOT_FOUND;
		}
	}

}
