// 리스트 12.4  PostsWriteController.java 내의 메소드

@RequestMapping(method = RequestMethod.POST, value="/posts")
public void newPost(@RequestBody PostApi newPost,
		    HttpServletResponse response) {

    logger.info("Have a new post with title " + newPost.getTitle());

    Long id = idManager.nextId();
    User user = userRepository.findByUsername(newPost.getUsername());
    if (user != null) {
	// 새로운 게시물 이벤트를 보냄
	PostEvent postEvent
	  = new PostEvent("created", id, new Date(), user.getId(),
			  newPost.getTitle(), newPost.getBody());
	kafkaTemplate.send("post", postEvent);
    } else
	logger.info("Something went awry with creating a new Post - user with username "
		    + newPost.getUsername() + " is not known");
}
