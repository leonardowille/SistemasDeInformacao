import 'dart:convert';

import 'package:examapp/api/examApi.dart';
import 'package:examapp/examHandler.dart';
import 'package:examapp/models/User.dart';
import 'package:examapp/services/sharedPreferencesService.dart';

class AccountService {
  final ExamApi _api = ExamApi();

  createAccount(User user, Function onSuccess, onError) async {
    await _api.createAccount(user, (response) {
      _onAuthenticationSuccess(response.toString());
      onSuccess();
    }, onError);
  }

  login(User user, Function onSuccess, Function onError) async {
    await _api.login(user, (response) {
      _onAuthenticationSuccess(response.toString());
      onSuccess();
    }, onError);
  }

  logout() {
    SharedPreferencesService().removeKey("jwtAuth");
    ExamHandler.currentUser = null;
  }

  updateProfile(User user, Function onSuccess, onError) async {
    await _api.updateProfile(user, (response) {
      _populateCurrentUserByResponse(response.toString());
      onSuccess();
    }, onError);
  }

  _getCurrentUser() async {
    await _api.getCurrentUser(
        (response) => _populateCurrentUserByResponse(response.toString()),
        () {});
  }

  _populateCurrentUserByResponse(String response){
    ExamHandler.currentUser =
        User.fromJson(JsonDecoder().convert(response));
  }

  _setAuth(String auth) {
    SharedPreferencesService().setString("jwtAuth", auth);
  }

  _onAuthenticationSuccess(String auth) {
    _setAuth(auth);
    _getCurrentUser();
  }
}
