// 리스트 5.2  CloudnativeApplication.java

public class CloudnativeApplication {

    public static Map<String, String> validTokens
	= new HashMap<String, String>();

    public static void main(String[] args) {
	SpringApplication.run(CloudnativeApplication.class, args);
    }
}
