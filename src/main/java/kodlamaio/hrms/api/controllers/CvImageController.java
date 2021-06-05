package kodlamaio.hrms.api.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CvImageService;
import kodlamaio.hrms.core.utilities.adapters.imageUploaderAdapter.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CvDao;
import kodlamaio.hrms.entities.concretes.CvImage;


@RestController
@RequestMapping("/api/cvimages")
public class CvImageController {
	
	private CvImageService cvImageService;
	private CvDao cvDao;
	private CloudinaryService cloudinaryService;
	
	@Autowired
	public CvImageController(CvImageService cvImageService, CloudinaryService cloudinaryService, CvDao cvDao) {
		super();
		this.cvImageService = cvImageService;
		this.cloudinaryService = cloudinaryService;
		this.cvDao = cvDao;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		var result = this.cvImageService.getAll();
		
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@GetMapping("/getbyid")
	public ResponseEntity<?> getById(int cvImageId){
		var result = this.cvImageService.getById(cvImageId);
		
		if (!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(@RequestParam MultipartFile multipartFile, @RequestParam int cvId){
		try {
			BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
			
			if (bufferedImage == null) {
				return ResponseEntity.badRequest().body(new ErrorResult("Fotoğraf validasyonu başarısız!"));
			}
			
		} catch (IOException exception) {
			return ResponseEntity.badRequest().body(new ErrorResult("Fotoğraf validasyonu başarısız!"));
		}
		
		try {
			Map result = cloudinaryService.upload(multipartFile);
			CvImage cvImage = new CvImage();
			cvImage.setName((String)result.get("original_filename"));
			cvImage.setUrl((String)result.get("url"));
			cvImage.setImageId((String)result.get("public_id"));
			cvImage.setCv(this.cvDao.getById(cvId));
			return ResponseEntity.ok(this.cvImageService.add(cvImage));
			
		} catch (IOException exception) {
			return ResponseEntity.badRequest().body(new ErrorResult("Fotoğraf yüklenirken beklenmedik bir hata oluştu!"));		
		}
		
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int cvImageId){
		if (!this.cvImageService.isExists(cvImageId)) {
			return ResponseEntity.badRequest().body(new ErrorResult("Not found!"));
		}
		
		try {
			CvImage cvImage = this.cvImageService.getById(cvImageId).getData();
			Map result = cloudinaryService.delete(cvImage.getImageId());
			this.cvImageService.delete(cvImageId);
			return ResponseEntity.ok(new SuccessResult("Fotoğraf başarıyla silindi!"));
					
		} catch (IOException exception) {
			return ResponseEntity.badRequest().body(new ErrorResult("Fotoğraf silinirken beklenmedik bir hata oluştu!"));
		}
	}
	
}
