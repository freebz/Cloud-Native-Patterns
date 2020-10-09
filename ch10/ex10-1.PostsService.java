// 리스트 10.1  PostsService.java 내의 메소드

@HystrixCommand()
public Iterable<Post> getPostsByUserId(String userIds,
				 String secret) throws Exception {
   logger.info(utils.ipTag() + "Attempting getPostsByUserId");
   Iterable<Post> posts;
   if (userIds == null) {
       logger.info(utils.ipTag() + "getting all posts");
       posts = postRepository.findAll();
       return posts;
   } else {
       ArrayList<Post> postsForUsers = new ArrayList<Post>();
       String userId[] = userIds.split(",");
       for (int i = 0; i < userId.length; i++) {
	    logger.info(utils.ipTag() +
			    "getting posts for userId " + userId[i]);
	    Posts = postRepository.findByUserId(Long.parseLong(userId[i]));
	    posts.forEach(post -> postsForUsers.add(post));
      }
      return postsForUsers;
   }
}
