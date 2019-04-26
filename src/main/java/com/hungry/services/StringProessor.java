package com.hungry.services;

import org.springframework.stereotype.Service;

import com.hungry.util.Type;

@Service
public class StringProessor {

	private static final int ID_START_POINT = 38;

	private static final int LENGTH_ID = 22;

	private static final int TYPE_START_POINT = ID_START_POINT + LENGTH_ID + 1;
	private static final int TYPE_SIZE = 3;

	protected String push_user_identification(String plainText, long userId) {

		char[] temp = plainText.toCharArray();

		for (int i = ID_START_POINT; i <= ID_START_POINT + LENGTH_ID; ++i) {
			temp[i] = (char) (userId % 10 + '0');
			// System.out.println(temp[i]);
			userId /= 10;
		}

		return String.valueOf(temp);
	}

	protected long seek_user_id(String token) {

		long userId = 0;
		for (int i = LENGTH_ID + ID_START_POINT; i >= ID_START_POINT; --i) {
			userId *= 10;
			userId += (long) (token.charAt(i) - '0');

		}
		return userId;
	}

	protected String push_user_type(String plainText, Type type) {
		char temp[] = plainText.toCharArray();
		int value = type.value();
		for (int i = TYPE_START_POINT; i <= TYPE_START_POINT + TYPE_SIZE; ++i) {
			temp[i] = (char) (value % 10 + '0');
			value /= 10;
		}

		return String.valueOf(temp);
	}

	protected int seek_user_type(String token) {
		char temp[] = token.toCharArray();
		int type = 0;
		for (int i = TYPE_START_POINT + TYPE_SIZE; i >= TYPE_START_POINT; --i) {
			type *= 10;
			type += (int) (token.charAt(i) - '0');
		}
		return type;
	}

}
