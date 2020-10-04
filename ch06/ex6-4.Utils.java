// 리스트 6.4  Utils.java

public class Utils implements ApplicationContextAware,
			      ApplicationListener<ServletWebServerInitializedEvent> {

    private ApplicationContext applicationContext;
    private int port;
    @Value("${ipaddress}")
    private String ip;

    public String ipTag() {
	return "[" + ip + ":" + port +"] ";
    }

    @Override
    public void setApplicationContext(
	    ApplicationContext applicationContext)
	                                           throws Beans Exception {
	this.applicationContext = applicationContext;
    }

    @Override
    public void setApplicationEvents(ServletWebServerInitializedEvent
				embeddedServletContainerInitializedEvent) {
	this.port = embeddedServletContainerInitializedEvent
	                 .getApplicationContext().getWebServer().getPort();
    }
}
