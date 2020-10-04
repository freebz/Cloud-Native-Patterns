// 리스트 6.6  ConnectionsPostsController.java

public class ConnectionsPostsController {
    ...

    @Value("${connectionpostscontroller.connectionsUrl}")
    private String connectionsUrl;
    @Value("${connectionpostscontroller.postsUrl}")
    private String postsUrl;
    @value("${connectionpostscontroller.usersUrl}")
    private String usersUrl;
    @Value("${com.corneliadavis.cloudnative.posts.secret}")
    private String postsSecret;
    @Value("${com.corneliadavis.cloudnative.connections.secret}")
    private String connectionsSecret;

    @RequestMapping(method = RequestMethod.GET, value="/connectionsposts")
    public Iterable<PostSummary> getByUsername(
	@CookieValue(value = "userToken", required=false) String token,
	HttpServletResponse response) {

	if (token == null) {
	    logger.info(utils.ipTag() + ...);
	    response.setStatus(401);
	} else {
	    ValueOperations<String, String> ops =
		this.template.opsForValue();
	    String username = ops.get(token);
	    if (username == null) {
		logger.info(utils.ipTag() + ...);
		response.setStatus(401);
	    } else {
		ArrayList<PostSummary> postSummaries
		    = new ArrayList<PostSummary>();
		logger.info(utils.ipTag() + ...);

		String ids = "";
		RestTemplate restTemplate = new RestTemplate();

		// 연결 가져오기
		String secretQueryParam = "?secret=" + connectionsSecret;
		ResponseEntity<ConnectionResult[]> respConns
		    = restTemplate.getForEntity(
			connectionsUrl + username + secretQueryParam,
			ConnectionResult[].class);
		ConnectionResult[] connections = respConns.getBody();
		for (int i = 0; i < connections.length; i++) {
		    if (i > 0) ids += ",";
		    ids += connections[i].getFollowed().toString();
		}
		logger.info(utils.ipTag() + ...);

		secretQueryParam = "&secret=" + postsSecret;
		// 연결에 대한 게시물 가져오기
		ResponseEntity<PostResult[]> respPosts
		    = restTemplate.getForEntity(
			postsUrl + ids + secretQueryParam,
			PostResult[].class);
		PostResult[] posts = respPosts.getBody();

		for (int i = 0; i < posts.length; i++)
		    postSummaries.add(
			new PostSummary(
			    getUsername(posts[i].getUserId()),
			    posts[i].getTitle(),
			    posts[i].getDate()));

		return postSummaries;
	    }
	}
	return null;
    }
    ...
}
