// 리스트 12.5  EventHandler.java 내의 메소드

@KafkaListener(topics="post",
	       groupId = "postsconsumer",
	       containerFactory = "kafkaListenerContainerFactory")
public void listenForPost(PostEvent postEvent) {

    logger.info("PostEvent Handler processing - event: "
		+ postEvent.getEventType());

    if (postEvent.getEventType().equals("created")) {
	Optional<Post> opt = postRepository.findById(postEvent.getId());
	if (!opt.isPresent()){
	    logger.info("Creating a new post in the cache with title "
			+ postEvent.getTitle());
	    Post post = new Post(postEvent.getId(),
				 postEvent.getDate(),
				 postEvent.getUserId(),
				 postEvent.getTitle(),
				 postEvent.getBody());
	    postRepository.save(post);
	} else
	    logger.info("Did not create already cached post with id "
			+ existingPost.getId());
    }
}
