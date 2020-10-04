// 리스트 6.5  PostsController.java

public class PostsController {
    ...

    @Value("${com.corneliadavis.cloudnative.posts.secret}")
    private String configuredSecret;
    ...

    @RequestMapping(method = RequestMethod.GET, value="/posts")
    public Iterable<Post> getPostsByUserId(
	@RequestParam(value="userIds", required=false) String userIds,
	@RequestParam(value="secret", required=true) String secret,
	HttpServletResponse response) {
	Iterable<Post> posts;

	if (secret.equals(configuredSecret)) {

	    logger.info(utils.ipTag() +
		  "Accessing posts using secret " + secret);

	    // DB에서 게시물을 찾아서 반환
	    ...
	} else {
	    logger.info(utils.ipTag() +
	         "Attempt to access Post service with secret " + secret
		 + " (expecting " + password + ")");
	    response.setStatus(401);
	    return null;
	}

    }
    ...
}
