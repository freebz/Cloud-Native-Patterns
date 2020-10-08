// 리스트 7.2  PostsController.java 내의 메소드

@RequestMapping(method = RequestMethod.GET, value="/posts")
public Iterable<Post> getPostsByUserId(
    @RequestParam(value="userIds", required=false) String userIds,
    @RequestParam(value="secret", required=true) String secret,
    HttpServletResponse response) {

    Iterable<Post> posts;

    if (utils.isValidSecret(secret)) {

	logger.info(utils.ipTag()
	    + "Accessing posts using secret " + secret);

	if (userIds == null) {
	    logger.info(utils.ipTag() + "getting all posts");
	    posts = postRepository.findAll();
	    return posts;
	} else {
	    ArrayList<Post> postsForUsers = new ArrayList<Post>();
	    String userId[] = userIds.split(",");
	    for (int i = 0; i < userId.length; i++) {
		logger.info(utils.ipTag()
		    + "getting posts for userId " + userId[i]);
		posts = postRepository.findByUserId(
		                       Long.parseLong(userId[i]));
		posts.forEach(post -> postsForUsers.add(post));
	    }
	    return postsForUsers;
	}
    } else {
	logger.info(utils.ipTag()
	    + "Attempt to access Post service with secret " + secret
	    + " (expecting one of " + utils.validSecrets() + ")");
	response.setStatus(401);
	return null;
    }

}
