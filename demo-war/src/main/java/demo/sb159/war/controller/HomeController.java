/**
 * 
 */
package demo.sb159.war.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author somnath
 *
 */
@RestController
public class HomeController {

	@GetMapping("/hello")
	public String sayHello(){
		return "Hello welcome to spring boot 1.5.9, war demo";
	}
	
	@PostMapping(path="/hello", consumes=MediaType.APPLICATION_JSON_VALUE)
	public Map<String,String> postHello(@RequestBody String payload){
		Map<String, String> map = new HashMap<>();
		map.put("message",payload);
		return map; 
	}
}
