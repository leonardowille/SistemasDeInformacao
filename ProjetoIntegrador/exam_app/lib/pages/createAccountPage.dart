import 'package:examapp/models/User.dart';
import 'package:examapp/services/accountService.dart';
import 'package:examapp/uiComponents/inputComponent.dart';
import 'package:examapp/validators/emailValidator.dart';
import 'package:examapp/validators/nameValidator.dart';
import 'package:examapp/validators/passwordValidator.dart';
import 'package:flutter/material.dart';

import 'homePage.dart';

class CreateAccountPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    final AccountService accountService = AccountService();
    GlobalKey<FormState> _key = GlobalKey();
    TextEditingController _inputName = TextEditingController();
    TextEditingController _inputEmail = TextEditingController();
    TextEditingController _inputPassword = TextEditingController();

    _backToLogin(BuildContext context) {
      Navigator.pop(context);
    }

    _goToHomePage(context) {
      Navigator.pushAndRemoveUntil(context,
          MaterialPageRoute(builder: (context) => HomePage()), (r) => false);
    }

    _submitForm(BuildContext context) {
      if (_key.currentState.validate()) {
        User user = User();
        user.name = _inputName.text;
        user.username = _inputEmail.text;
        user.password = _inputPassword.text;
        print("Name: ${_inputName.text} / E-mail: ${_inputEmail.text} / Password: ${_inputPassword.text}");
        accountService.createAccount(user, () => _goToHomePage(context));
      }
    }

    Widget _widgetForm(BuildContext context) {
      return Column(
        children: <Widget>[
          InputComponent(
            hintText: "Nome",
            controller: _inputName,
            validator: NameValidator(),
          ),
          InputComponent(
            hintText: "E-mail",
            controller: _inputEmail,
            validator: EmailValidator(),
          ),
          InputComponent(
            hintText: "Password",
            controller: _inputPassword,
            validator: PasswordValidator(),
            obscureText: true,
          ),
          MaterialButton(
            child: Text("Criar Conta"),
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
                onTap: () => _backToLogin(context),
                child: Container(child: Text("Cancelar")))
          ],
        ),
      ),
    );
  }
}
