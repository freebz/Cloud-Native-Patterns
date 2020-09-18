// 리스트 4.3  Connections' Posts 서비스의 EventsController.java 내 함수

@RequestMapping(method = RequestMapping.POST, value="/posts")
public void newPost(@RequestBody Post newPost, HttpServletResponse response) {

    logger.info("[NewFromConnections] Have a new post with title "
		+ newPost.getTitle());
    MPost mPost = new MPost(newPost.getId(),
			    newPost.getDate(),
			    newPost.getUserId(),
			    newPost.getTitle());
    MUser mUser;
    mUser = mUserRepository.findOne(newPost.getUserId());
    mPost.setmUser(mUser);
    mPostRepository.save(mPost);

}
