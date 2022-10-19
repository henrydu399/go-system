package co.system.out.file.store;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {

	void init();

	void store(MultipartFile file);
	
	public void storeWithDirectoty(MultipartFile file, String idProceso , String areaProceso , String tipoProceso );
	
	public void storeWithDirectoty(MultipartFile file, String idUser );

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);
	
	public String loadAsPlantillaEmail(String filename) throws IOException ;
	
	boolean delete(String filename);

	void deleteAll();

}
