// 리스트 7.4  ConnectionsPosts 서비스의 Utils 클래스 내의 메소드

@Override
public void onApplicationEvent(ApplicationEvent applicationEvent) {
    if (applicationEvent instanceof ServletWebServerInitializedEvent) {
	ServletWebServerInitializedEvent
	    servletWebServerInitializedEvent
	        = (ServletWebServerInitializedEvent) applicationEvent;
	this.port = servletWebServerInitializedEvent...;
    } else if (applicationEvent instanceof ApplicationPreparedEvent) {
	connectionsSecret = connectionsSecretsIn.split(",")[0];
	postsSecret = postsSecretsIn.split(",")[0];
	logger.info(ipTag()
	    + "Connection Posts Service initialized with Post secret: "
	    + postsSecret + " and Connections secret: "
	    + connectionsSecret);
    }
}
