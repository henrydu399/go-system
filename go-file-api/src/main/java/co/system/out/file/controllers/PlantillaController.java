package co.system.out.file.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.system.out.file.store.StorageService;

@Controller
public class PlantillaController {
	
	
	@Autowired
	private  StorageService storageService;

	
	@GetMapping("/filesplantilla/{filename:.+}")
	@ResponseBody
	public String getPlantilla(@PathVariable String filename)throws  UnsupportedEncodingException, IOException {
		
		return    storageService.loadAsPlantillaEmail(filename);
	}
	
	
	@PostMapping("/filesplantilla/")
	public ResponseEntity<String> savePlantilla(@RequestParam("file") MultipartFile file, 
								                @RequestParam("fileName") String fileName) {
		
		
		return ResponseEntity.ok("GUARDADO EXITOSO ");
	}

}
