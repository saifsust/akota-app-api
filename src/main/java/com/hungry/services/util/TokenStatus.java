package com.hungry.services.util;

public enum TokenStatus {
	OK, ACCEPTED, NOT_ACCEPTED, CREATED, UNAUTHORIZED, NOT_FOUND, IM_USED;

	public int value() {
		switch (this) {
		case OK:
			return 200;
		case CREATED:
			return 201;
		case ACCEPTED:
			return 202;
		case IM_USED:
			return 226;
		case UNAUTHORIZED:
			return 401;
		case NOT_ACCEPTED:
			return 406;
		case NOT_FOUND:
		default:
			return 404;
		}
	}

	public static TokenStatus status(int code) {

		switch (code) {
		case 200:
			return OK;
		case 201:
			return CREATED;
		case 202:
			return ACCEPTED;
		case 226:
			return IM_USED;
		case 401:
			return UNAUTHORIZED;
		case 406:
			return NOT_ACCEPTED;
		case 404:
		default:
			return NOT_FOUND;
		}
	}

}
