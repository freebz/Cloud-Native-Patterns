// 리스트 10.2  PostsService.java 내의 메소드

@HystrixCommand(fallbackMethod = "getSponsoredPosts")
public Iterable<Post> getPostsByUserId(String userIds,
				     String secret) throws Exception {
  logger.info(utils.ipTag() + "Attempting getPostsByUserId");
  Iterable<Post> posts;
  if (userIds == null) {
      logger.info(utils.ipTag() + "getting all posts");
      posts = postRepository.finidAll();
      return posts;
  } else {
    ArrayList<Post> postsForUsers = new ArrayList<Post>();
    String userId[] = userIds.split(",");
    for (int i = 0; i < userId.length; i++) {
         logger.info(utils.ipTag() +
		    "getting posts for userId " + userId[i]);
	 posts = postRepository.findByUserId(Long.parseLong(userId[i]));
	 posts.forEach(post -> postsForUsers.add(post));
    }
    return postsForUsers;
  }
}

public Iterable<Post> getSponsoredPosts(String userIds,
					String secret) {
    logger.info(utils.ipTag() +
		"Accessing Hystrix fallback getSponsoredPosts");
    ArrayList<Post> posts = new ArrayList<Post>();
    posts.add(new Post(999L, "Some catchy title",
		        "Some great sponsored content"));
    posts.add(new Post(999L, "Another catchy title",
		        "Some more great sponsored content"));
    return posts;
}
