package hello;

import org.springframework.stereotype.Component;

@Component
public class NameBean {

	String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

}