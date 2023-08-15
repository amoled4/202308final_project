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

import com.myweb.www.domain.BoardLocationImageVO;

import lombok.AllArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;

@AllArgsConstructor
@Component
public class BoardLocationImageHandler {
	private final String UP_DIR = //"E:\\Workspace\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
	"D:\\_final_project\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
	
	public List<BoardLocationImageVO> uploadLocationFiles(MultipartFile[] files){
		LocalDate date = LocalDate.now();
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		List<BoardLocationImageVO> blivoList = new ArrayList<BoardLocationImageVO>();
		for(MultipartFile file : files) {
			BoardLocationImageVO blivo = new BoardLocationImageVO();
			blivo.setBlf_dir(today);
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(originalFileName.lastIndexOf(File.separator)+1);
			blivo.setBlf_name(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			blivo.setBlf_uuid(uuid.toString());
			
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
			blivoList.add(blivo);
		}
		return blivoList;
	}

	private boolean isImageFile(File storeFile) throws IOException {
		String mimeType = new Tika().detect(storeFile);
		return mimeType.startsWith("image")? true : false;
	}
}
