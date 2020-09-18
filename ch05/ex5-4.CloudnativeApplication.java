// 리스트 5.4  CloudnativeApplication.java

public class CloudnativeApplication {

    @Value("${redis.hostname}")
    private String redisHostName;
    @Value("${redis.port}")
    private int redisPort;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {

	return new LettuceConnectionFactory
	    (new RedisStandaloneConfiguration(redisHostName, redisPort));
    }

    public static void main(String[] args) {
	SpringApplication.run(CloudnativeApplication.class, args);
    }
}
