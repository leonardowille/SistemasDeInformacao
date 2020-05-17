class User {
  int id;
  String name;
  String birthday;
  String username;
  String password;

  User();

  User.fromJson(Map jsonMap)
      : id = jsonMap['id'],
        name = jsonMap['name'],
        birthday = jsonMap['birthday'],
        username = jsonMap['username'],
        password = jsonMap['password'];
}
