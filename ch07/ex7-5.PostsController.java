// 리스트 7.5  Posts_Controller.java 내의 메소드

@RequestMapping(method = RequestMethod.GET, value="/healthz")
public void healthCheck(HttpServletResponse response)
    throws InterruptedException {

    if (this.isHealthy) response.setStatus(200);
    else Thread.sleep(400000);
}
