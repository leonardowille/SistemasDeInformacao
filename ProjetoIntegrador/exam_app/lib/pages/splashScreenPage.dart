import 'package:examapp/pages/loginPage.dart';
import 'package:examapp/services/accountService.dart';
import 'package:flutter/material.dart';

class SplashScreenPage extends StatelessWidget {

  AccountService accountService = AccountService();

  _goToLoginPage(context) {
    Navigator.pushAndRemoveUntil(
        context, MaterialPageRoute(builder: (context) => LoginPage()), (r) => false);
  }

  @override
  Widget build(BuildContext context) {
    accountService.logout();
    Future.delayed(
        Duration(milliseconds: 1000), () => _goToLoginPage(context));
    return Scaffold(
      body: Container(
        child: Align(
          alignment: Alignment.center,
          child: Text("Exam App Splash"),
        ),
      ),
    );
  }
}
