package co.system.out.file;




import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;



import co.system.out.file.store.StorageService;



@SpringBootApplication
public class FilemanagerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilemanagerApiApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
	
	
	
}


