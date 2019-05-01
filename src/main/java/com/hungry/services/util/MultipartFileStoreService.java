package com.hungry.services.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hungry.repositories.UserRepository;

@Service
public class MultipartFileStoreService {

	private static final Logger LOG = (Logger) LoggerFactory.getLogger(MultipartFileStoreService.class);

	private static final String IMAGES_SAVE_PATH = "/media/saif-sust/WEB_project/hungry/images/";

	@Autowired
	private ImageProcessor imageProcessor;

	@Autowired
	private SecurityMaster securityMaster;
	@Autowired
	private UserRepository userRepository;

	private Map<String, Object> decrytion;

	@Transactional
	public ResponseEntity<Void> store(String token, MultipartFile mpf, HttpServletRequest httpServletRequest) {				
		String url = null, path = null;
		try {
			LOG.debug("store : token : " + token + " | mpf : " + mpf.getContentType());
			Map<String, String> mapper = imageProcessor.move(mpf, IMAGES_SAVE_PATH, httpServletRequest);
			url = mapper.get("url");
			path = mapper.get("path");
			decrytion = securityMaster.decrypt(token);
			long userId = (long) decrytion.get("user_id");
			LOG.debug("store : url : " + url + " user_id : " + userId);
			String local=userRepository.findImageLocalAddresss((int)userId);
			imageProcessor.remove(local);			
			userRepository.updateUserImage(url,path,(int) userId);
		} catch (Exception e) {
			LOG.error("store : " + e.getMessage());
			imageProcessor.remove(path);
			return new ResponseEntity<Void>(HttpStatus.NOT_MODIFIED);
		}
		return ResponseEntity.accepted().body(null);
	}

}
