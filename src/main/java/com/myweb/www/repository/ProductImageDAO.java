package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.ProductImageVO;
import com.myweb.www.domain.ProductVO;

public interface ProductImageDAO {

	int insertProductImage(ProductImageVO pivo);

	List<ProductImageVO> selectFileList(ProductVO pvo);

	List<ProductImageVO> selectFile(int p_number);

	int deleteFile(String pi_uuid);

	List<ProductImageVO> selectFile();
	
	// 작성자:안세호
	List<ProductImageVO> selectImagesByProduct(int p_number);

}
