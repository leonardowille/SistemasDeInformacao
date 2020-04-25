import 'package:examapp/api/examApi.dart';
import 'package:examapp/models/User.dart';
import 'package:examapp/models/examResponse.dart';
import 'package:examapp/services/sharedPreferencesService.dart';

import '../responseTypeEnum.dart';

class AccountService {
  final ExamApi _api = ExamApi();

  createAccount(User user, Function onSuccess) async {
    ExamResponse examResponse = await _api.createAccount(user);
    if (ResponseTypeEnum.SUCCESS == examResponse.responseType) {
      _onAuthenticationSuccess(examResponse.response.toString());
      onSuccess();
    }
  }

  login(User user, Function onSuccess) async {
    ExamResponse examResponse = await _api.login(user);
    if (ResponseTypeEnum.SUCCESS == examResponse.responseType) {
      _onAuthenticationSuccess(examResponse.response.toString());
      onSuccess();
    }
  }

  _getCurrentUser() async {
    ExamResponse examResponse = await _api.getCurrentUser();
    if (ResponseTypeEnum.SUCCESS == examResponse.responseType) {
      print(examResponse.toString());
    }
  }

  _setAuth(String auth) {
    SharedPreferencesService().setString("jwtAuth", auth);
  }

  _onAuthenticationSuccess(String auth) {
    _setAuth(auth);
    _getCurrentUser();
  }
}
