// 리스트 4.2  PostsWriteController.java 내의 메소드

@RequestMapping(method = RequestMethod.POST, value="/posts")
public void newPost(@RequestBody Post newPost,
		    HttpServletResponse response) {

    logger.info("Have a new post with title " + newPost.getTitle());

    if (newPost.getDate() == null)
	newPost.setDate(new Date());
    postRepository.save(newPost);
    //event
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity<String> resp =
    restTemplate.postForEntity("http://localhost:8080/connectionsposts/posts",
			       newPost, String.class);
    logger.info("[Post] resp " + resp.getStatusCode());

}
