package com.myweb.www.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.ProductImageVO;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@AllArgsConstructor
@Component
public class ProductImageHandler {
	
	private final String UP_DIR = "E:\\Workspace\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
			//"D:\\_final_project\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
	
	
	public List<ProductImageVO> uploadFiles(MultipartFile[] files){
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		List<ProductImageVO> piList = new ArrayList<ProductImageVO>();
		for(MultipartFile file : files) {
			ProductImageVO pivo = new ProductImageVO();
			pivo.setPi_dir(today);
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			pivo.setPi_name(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			pivo.setPi_uuid(uuid.toString());
			
			String fullfileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders, fullfileName);
			
			try {
				file.transferTo(storeFile);
				if(isImageFile(storeFile)) {
					File thumbNail = new File(folders, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(190, 200).toFile(thumbNail);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			piList.add(pivo);
		}
		return piList;
	}

	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")? true : false;
	}

	
}
