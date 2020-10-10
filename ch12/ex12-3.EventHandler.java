// 리스트 12.3  EventHandler.java 내의 메소드

@KafkaListener(topics="user",
	       groupId = "connectionspostsconsumer",
	       containerFactory = "kafkaListenerContainerFactory")
public void userEvent(UserEvent userEvent) {

    logger.info("Posts UserEvent Handler processing - event: " +
		userEvent.getEventType());

    if (userEvent.getEventType().equals("created")) {

	// 이벤트 핸들러를 멱등성 있게 처리함
	// 사용자 정보가 존재하면 아무것도 안 함
	User existingUser
	    = userRepository.findByUsername(userEvent.getUsername());
	if (existingUser == null) {

	    // 로깅 저장소에 레코드 저장
	    User user = new User(userEvent.getId(), userEvent.getName(),
				 userEvent.getUsername());
	    userRepository.save(user);

	    logger.info("New user cached in local storage " +
			user.getUsername());
	    userRepository.save(new User(userEvent.getId(),
					 userEvent.getName(),
					 userEvent.getUsername()));
	} else
	    logger.info("Already existing user not cached again id " +
			userEvent.getId());
    } else if (userEvent.getEventType().equals("updated")) {
	// 업데이트된 이벤트 처리
    }

}

@KafkaListener(topics="connection",
	       groupId = "connectionspostsconsumer",
	       containerFactory = "kafkaListenerContainerFactory")
public void connectionEvent(ConnectionEvent connectionEvent) {

    // 누가 누구를 팔로우하는지 - 연결의 변경 처리...
    // 이것은 생성과 삭제 이벤트임
}

@KafkaListener(topics="post",
	       groupId = "connectionspostsconsumer",
	       containerFactory = "kafkaListenerContainerFactory")
public void postEvent(PostEvent postEvent) {

    // 게시물에 변경 내용 처리 - 이 경우 새로운 게시물임

}
