package com.myweb.www.handler;

import java.io.File;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myweb.www.domain.ProductImageVO;
import com.myweb.www.repository.ProductImageDAO;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductImageSweeper {
	private final String UP_DIR = "D:\\_final_project\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
	//"E:\\Workspace\\ezen_final_project\\src\\main\\webapp\\resources\\fileUpload";
	@Inject
	private ProductImageDAO pidao;
	
	@Scheduled(cron="0 0 10 * * ?")
	public void productImageSweeper() {
		List<ProductImageVO> piList = pidao.selectFile();
		List<String> currImages = new ArrayList<String>();
		
		for(ProductImageVO pivo : piList) {
			String imagePath = pivo.getPi_dir() + "\\" + pivo.getPi_uuid();
			String imageName = pivo.getPi_name();
			currImages.add(UP_DIR+imagePath+"_"+imageName);
			currImages.add(UP_DIR+imagePath+"_th_"+imageName);
		}
		LocalDate now = LocalDate.now();
		String today = now.toString();
		today = today.replace("-", File.separator);
		
		File dir = Paths.get(UP_DIR + today).toFile();
		File[] allImageObjects = dir.listFiles();
		
		for(File file : allImageObjects) {
			String stroedImageName = file.toPath().toString();
			if(!currImages.contains(stroedImageName)) {
				file.delete();
			}
		}
	}
}
