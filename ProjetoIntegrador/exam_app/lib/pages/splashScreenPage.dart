import 'package:examapp/pages/loginPage.dart';
import 'package:flutter/material.dart';

class SplashScreenPage extends StatelessWidget {
  _goToLoginPage(context) {
    Navigator.pushAndRemoveUntil(
        context, MaterialPageRoute(builder: (context) => LoginPage()), (r) => false);
  }

  @override
  Widget build(BuildContext context) {
    Future.delayed(
        Duration(milliseconds: 5000), () => _goToLoginPage(context));
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
