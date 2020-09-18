// 리스트 5.6  ConnectionsPostsController.java

...
// String username
//     CloudnativeApplication.validTokens.get(token);    삭제한다

ValueOperations<String, String> ops = this.template.opsForValue();
String username = ops.get(token);
