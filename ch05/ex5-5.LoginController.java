// 리스트 5.5  LoginController.java

...
// CloudnativeApplication.validTokens.put(userToken, username);    삭제한다

ValueOperations<String, String> ops = this.template.opsForValue();
ops.set(userToken, username);
