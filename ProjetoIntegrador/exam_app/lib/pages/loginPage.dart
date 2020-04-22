import 'package:examapp/pages/createAccountPage.dart';
import 'package:examapp/pages/homePage.dart';
import 'package:examapp/uiComponents/inputComponent.dart';
import 'package:examapp/validators/emailValidator.dart';
import 'package:examapp/validators/passwordValidator.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget {
  GlobalKey<FormState> _key = GlobalKey();
  TextEditingController _inputLogin = TextEditingController();
  TextEditingController _inputPassword = TextEditingController();

  _submitForm(BuildContext context) {
    if (_key.currentState.validate()) {
      print("Login: ${_inputLogin.text} / Password: ${_inputPassword.text}");
      _goToHomePage(context);
    }
  }

  _goToCreateAccountPage(context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => CreateAccountPage()));
  }

  _goToHomePage(context) {
    Navigator.pushAndRemoveUntil(
        context, MaterialPageRoute(builder: (context) => HomePage()), (r) => false);
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        body: Column(
          children: <Widget>[
            Container(
              width: 50,
              height: 50,
              color: Colors.green,
            ),
            Form(
              key: _key,
              child: _widgetForm(context),
            ),
            InkWell(
              onTap: () => _goToCreateAccountPage(context),
              child: Container(
                child: Text("Criar Conta"),
              ),
            )
          ],
        ),
      ),
    );
  }

  Widget _widgetForm(BuildContext context) {
    return Column(
      children: <Widget>[
        InputComponent(
          hintText: "E-mail",
          validator: EmailValidator(),
          controller: _inputLogin,
        ),
        InputComponent(
          hintText: "Senha",
          validator: PasswordValidator(),
          keyboardType: TextInputType.text,
          controller: _inputPassword,
          obscureText: true,
        ),
        MaterialButton(
          child: Text("Login"),
          onPressed: () => _submitForm(context),
        ),
      ],
    );
  }
}
