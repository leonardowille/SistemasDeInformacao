import 'package:examapp/examHandler.dart';
import 'package:examapp/models/User.dart';
import 'package:examapp/services/accountService.dart';
import 'package:examapp/uiComponents/inputComponent.dart';
import 'package:examapp/validators/emailValidator.dart';
import 'package:examapp/validators/nameValidator.dart';
import 'package:flutter/material.dart';

class EditProfilePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final AccountService accountService = AccountService();
    GlobalKey<FormState> _key = GlobalKey();
    TextEditingController _inputName = TextEditingController();
    TextEditingController _inputBirthday = TextEditingController();

    _backNavigation(BuildContext context) {
      Navigator.pop(context);
    }

    _submitForm(BuildContext context) {
      if (_key.currentState.validate()) {
        User user = User();
        user.name = _inputName.text;
        user.birthday = _inputBirthday.text;
        print("Name: ${_inputName.text} / Birthday: ${_inputBirthday.text}");
        accountService.updateProfile(user, () {
          _backNavigation(context);
        }, () {});
      }
    }

    Widget _widgetForm(BuildContext context) {
      User currentUser = ExamHandler.currentUser;
      _inputName.text = currentUser.name;
      _inputBirthday.text = currentUser.birthday;
      return Column(
        children: <Widget>[
          InputComponent(
            hintText: "Nome",
            controller: _inputName,
            validator: NameValidator(),
          ),
          InputComponent(
            hintText: "Data de Nascimento",
            controller: _inputBirthday,
            validator: EmailValidator(),
          ),
          MaterialButton(
            child: Text("Atualizar Perfil"),
            onPressed: () => _submitForm(context),
          ),
        ],
      );
    }

    return SafeArea(
      child: Scaffold(
        body: Column(
          children: <Widget>[
            Form(
              key: _key,
              child: _widgetForm(context),
            ),
            InkWell(
                onTap: () => _backNavigation(context),
                child: Container(child: Text("Cancelar")))
          ],
        ),
      ),
    );
  }
}
