// 리스트 7.1  Utils.java

public class Utils implements
ApplicationContextAware, ApplicationListener<ApplicationEvent> {

    // <간결함을 위해 생략함>
    @Value("${com.corneliadavis.cloudnative.posts.secrets}")
    private String configuredSecretsIn;
    private Set<String> configSecrets;
    // <간결함을 위해 생략함>

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

	if (applicationEvent instanceof ServletWebServerInitializedEvent) {
	    ServletWebServerInitializedEvent
		servletWebServerInitializedEvent
		    = (ServletWebServerInitializedEvent) applicationEvent;
	    this.port = servletWebServerInitializedEvent...
	} else if (applicationEvent instanceof ApplicationPreparedEvnet) {
	    configSecrets = new HashSet<>();
	    String secrets[] = configuredSecretsIn.split(",");
	    for (int i=0; i<secrets.lenght; i++)
		configSecrets.add(secrets[i].trim());
	    logger.info(ipTag()
			+ "Posts Service initialized with secret(s): "
			+ configuredSecretsIn);
	}
    }

    public String ipTag() { return "[" + ip + ":" + port + "] "; }

    public boolean isValidSecret(String secret) {
	return configSecrets.contains(secret);
    }

    // 프로덕션 환경에서는 존재하지 않는 일부 로깅을 용이하게 하기 위해
    // 다음 메소드가 포함됨
    public String validSecrets() {
	String result = "";
	for (String s : configSecrets)
	    result += s + ",";
	return result;
    }
}
