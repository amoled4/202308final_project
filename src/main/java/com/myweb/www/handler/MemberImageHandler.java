package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.MemberImageVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@AllArgsConstructor
@Component
public class MemberImageHandler {
	
	
	//주소 주의 (D드라이브 / E드라이브)
	private final String UP_DIR = "D:\\_final_project\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
			//"E:\\Workspace\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
	

	public MemberImageVO uploadFile(MultipartFile file) {
			LocalDate date = LocalDate.now();
			log.info("=-=-> date : "+date);
			String today = date.toString(); 
			today = today.replace("-", File.separator);
			
			File folders = new File(UP_DIR, today);
			
			if(!folders.exists()) {
				folders.mkdirs(); 
			}
	        
	        MemberImageVO mivo = new MemberImageVO();
	        mivo.setMi_dir(today);
	    
	        
	        String originalFileName = file.getOriginalFilename();
	        String onlyFileName = originalFileName.substring(
	                originalFileName.lastIndexOf(File.separator)+1); 
	        log.info("=-=-> onlyFileName : "+onlyFileName); 
	        mivo.setMi_name(onlyFileName); 


	        UUID uuid = UUID.randomUUID();
	        mivo.setMi_uuid(uuid.toString()); 

	        String fullfileName = uuid.toString()+"_"+onlyFileName;
	        File storeFile = new File(folders, fullfileName);

	        
	        
	        try {
	            file.transferTo(storeFile);
	            if(isImageFile(storeFile)) {
	            	//mivo.setFile_type(1);
	                File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
	                Thumbnails.of(storeFile).size(190, 200).toFile(thumbNail);
	            }
	        } catch (Exception e) {
	            log.info("=-=-> file 생성 오류");
	            log.error("File creation error", e);
	            return null; // 예외가 발생하면 null을 반환
	        }
	        
	        return mivo;
	    }
	    
	private boolean isImageFile(File storeFile) throws IOException {
			String mimeType = new Tika().detect(storeFile);
			return mimeType.startsWith("image")? true : false;
	}
}
