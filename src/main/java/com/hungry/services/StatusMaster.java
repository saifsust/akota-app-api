package com.hungry.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hungry.util.TokenStatus;

@Service
public class StatusMaster {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(StatusMaster.class);

	private static int[] positions;
	private static final int POSITION_SIZE = 2;

	static {
		positions = new int[POSITION_SIZE + 1];
		positions[0] = 13;
		positions[1] = 21;
		for (int i = 2; i <= POSITION_SIZE; ++i)
			positions[i] = positions[i - 2] + positions[i - 1];
	}

	protected TokenStatus seek(String plainText) {

		LOG.info("seek : " + plainText);

		char temp[] = plainText.toCharArray();
		int status = 0;

		for (int i = POSITION_SIZE; i >= 0; --i) {
			int value = (int) (temp[positions[i]] - '0');
			status *= 10;
			status += value;
		}
		return TokenStatus.status(status);
	}

	protected String push(String plainText, TokenStatus status) {
		LOG.info("push : status -> " + status + " | plainttext ->  " + plainText);
		switch (status) {
		case OK:
			return status_adder(plainText, TokenStatus.OK.value());
		case CREATED:
			return status_adder(plainText, TokenStatus.CREATED.value());
		case ACCEPTED:
			return status_adder(plainText, TokenStatus.ACCEPTED.value());
		case IM_USED:
			return status_adder(plainText, TokenStatus.IM_USED.value());
		case UNAUTHORIZED:
			return status_adder(plainText, TokenStatus.UNAUTHORIZED.value());
		case NOT_ACCEPTED:
			return status_adder(plainText, TokenStatus.NOT_ACCEPTED.value());
		default:
			return status_adder(plainText, TokenStatus.NOT_FOUND.value());
		}

	}

	private String status_adder(String plainText, int status) {
		char temp[] = plainText.toCharArray();
		for (int i = 0; i <= POSITION_SIZE; ++i) {
			temp[positions[i]] = (char) (status % 10 + '0');
			status /= 10;
		}
		return String.valueOf(temp);

	}

}
