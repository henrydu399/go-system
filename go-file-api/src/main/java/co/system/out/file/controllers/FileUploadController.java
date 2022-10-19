package co.system.out.file.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import co.system.out.file.store.StorageFileNotFoundException;
import co.system.out.file.store.StorageService;
import co.system.out.filemanager.util.UtilFile;






@Controller
public class FileUploadController {

	private final StorageService storageService;

	@Autowired
	public FileUploadController(StorageService storageService) {
		this.storageService = storageService;
	}

	@GetMapping("/")
	public String listUploadedFiles(Model model) throws IOException {
		model.addAttribute("files", storageService.loadAll().map(
				path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
						"serveFile", path.getFileName().toString()).build().toUri().toString())
				.collect(Collectors.toList()));
		return "uploadForm";
	}
	
	

	@GetMapping("/files2/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename)throws  UnsupportedEncodingException, IOException {
		//String json = URLDecoder.decode(request, StandardCharsets.UTF_8.name()) .replace("_Response=", "");
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	  @CrossOrigin(origins = "*", allowedHeaders = "*")
	//@GetMapping("/download/")
	  @RequestMapping(value = "/download/", method = RequestMethod.GET)
	public ResponseEntity<Resource> getDocuments(
			@RequestParam String area,
			@RequestParam String tipo,
			@RequestParam String idProceso,  
			@RequestParam String fileName ){		
		
		Resource file = storageService.loadAsResource(area+"/"+tipo+"/"+idProceso+"/"+fileName);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	
	@RequestMapping("/filesB64/**")
	public ResponseEntity<String>  serveFileB64(HttpServletRequest request)throws  UnsupportedEncodingException, IOException {
			  String pattern = (String)
			        request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);  		    
			    String searchTerm = new AntPathMatcher().extractPathWithinPattern(pattern, request.getServletPath());		    
		Resource file = storageService.loadAsResource(searchTerm);
		 String encodedString = null;
		if(file != null ) {
		    byte[] bytes = UtilFile.loadFile(file.getFile());
		    byte[] encoded = Base64.encodeBase64(bytes);
		     encodedString = new String(encoded,StandardCharsets.US_ASCII);	
		}	
		if(encodedString != null ) {
			return ResponseEntity.status(200).body(encodedString);
		}else {
			return ResponseEntity.status(500).body("error");
		}
	}
	
	
	

	
	@RequestMapping("/files/**")  
	public ResponseEntity<Resource> searchWithSearchTerm(HttpServletRequest request) { 
	    // Don't repeat a pattern
	    String pattern = (String)
	        request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);  
	    
	    String searchTerm = new AntPathMatcher().extractPathWithinPattern(pattern, 
	            request.getServletPath());

		Resource file = storageService.loadAsResource(searchTerm);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	
	

	@PostMapping("/")
	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("idProceso") String idProceso, @RequestParam("areaProceso") String areaProceso, @RequestParam("tipoProceso") String tipoProceso,
			RedirectAttributes redirectAttributes) {
		storageService.storeWithDirectoty(file, idProceso ,areaProceso , tipoProceso );
		//redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + "!");
		 return ResponseEntity.status(200).build();

		
	}
	
	
	@PostMapping("/saveIMG")
	public ResponseEntity<Object> saveIMG(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId, RedirectAttributes redirectAttributes) {
		storageService.storeWithDirectoty(file, userId    );
		//redirectAttributes.addFlashAttribute("message","You successfully uploaded " + file.getOriginalFilename() + "!");
		 return ResponseEntity.status(200).build();
		
	}
	
	@GetMapping("/getIMG")
	@ResponseBody
	public ResponseEntity<Resource> getIMG(@RequestParam String user, @RequestParam String nameFile ) throws IOException {
		String filename = user+"//"+ nameFile;
		Resource file = storageService.loadAsResource(filename);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}
	

	
	

	@PostMapping("/delete")
	public ResponseEntity<Object> deleteFileUpload( @RequestParam("fileName") String fileName) {

		if( storageService.delete(fileName)) {
			 return ResponseEntity.status(200).build();
		}else {
			 return ResponseEntity.status(500).body("Error eliminando");
		}
	
		

		
	}
	
	

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}

}