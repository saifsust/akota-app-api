package com.hungry.services.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageProcessor {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(ImageProcessor.class);

	public void remove(String path) {
		try {

			File file = new File(path);
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			LOG.error("remove : " + e.getMessage());
		}
	}

	public Map<String, String> move(MultipartFile mpf, HttpServletRequest httpServletRequest) {

		String name = getName(mpf.toString()) + "_" + mpf.getOriginalFilename();
		String _path = Global.IMAGE_PATH + name;

		LOG.debug("move : path : " + _path);

		File save = new File(_path);
		try {

			InputStream inStream = mpf.getInputStream();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(save));
			int i;
			while ((i = inStream.read()) != -1) {
				stream.write(i);

				stream.flush();
			}
			name = URLEncoder.encode(name, StandardCharsets.UTF_8.toString());

			Map<String, String> mapper = new HashMap<String, String>();
			mapper.put("url", getBaseURL(httpServletRequest) + "/" + name);
			mapper.put("path", _path);
			return mapper;
			// return getBaseURL(httpServletRequest) + "/" + name;
		} catch (Exception e) {
			LOG.debug("move : path : " + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	protected String getName(String name) {
		String temp = "";
		boolean isFound = false;
		for (int i = 0; i < name.length(); ++i) {
			if (name.charAt(i) == '@') {
				isFound = true;
				continue;
			}
			if (isFound) {
				temp += name.charAt(i);
			}
		}
		return temp;
	}

	public String getBaseURL(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
	}

}