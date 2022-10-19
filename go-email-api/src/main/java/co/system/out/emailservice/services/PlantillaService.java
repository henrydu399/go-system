package co.system.out.emailservice.services;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;



@Service
public class PlantillaService {
	
	@Value("${spring.url.apifile}")
	String ulrApiFile;
	
	
	public String getPlantilla(String name) throws Exception {
		return this.getPlantillaOn(name);
	}
	
	
	private String getPlantillaOn(String name) throws Exception {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet httpPost = new HttpGet(ulrApiFile + "/filesplantilla/"+name);

		Gson gson = new Gson();
	

		CloseableHttpResponse response = client.execute(httpPost);
		// assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
		client.close();
		
		if ( response.getStatusLine().getStatusCode()!= 200) {
			throw new Exception();
		}else {
			return response.getEntity().getContent().toString();
		}
	}

}
