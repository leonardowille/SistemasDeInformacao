import 'package:examapp/pages/editProfilePage.dart';
import 'package:examapp/pages/loginPage.dart';
import 'package:examapp/services/accountService.dart';
import 'package:flutter/material.dart';

import '../examHandler.dart';

class ProfileTab extends StatefulWidget {
  @override
  _ProfileTabState createState() => _ProfileTabState();
}

class _ProfileTabState extends State<ProfileTab> {

  AccountService accountService = AccountService();

  _logout(BuildContext context) {
    accountService.logout();
    Navigator.pushAndRemoveUntil(context,
        MaterialPageRoute(builder: (context) => LoginPage()), (r) => false);
  }

  _editProfile(BuildContext context) {
    Navigator.push(context,
        MaterialPageRoute(builder: (context) => EditProfilePage()));
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Expanded(
          flex: 1,
          child: Container(),
        ),
        Expanded(
          flex: 4,
          child: Container(
            width: double.infinity,
            height: double.infinity,
            child: Icon(
              Icons.account_circle,
              size: 150,
            ),
          ),
        ),
        Expanded(
          flex: 1,
          child: Container(),
        ),
        Expanded(
          flex: 1,
          child: Column(
            children: <Widget>[
              Text("Nome:", style: TextStyle(fontWeight: FontWeight.bold),),
              Text("${ExamHandler.currentUser.name}"),
            ],
          ),
        ),
        Expanded(
          flex: 1,
          child: Column(
            children: <Widget>[
              Text("E-mail:", style: TextStyle(fontWeight: FontWeight.bold),),
              Text("${ExamHandler.currentUser.username}"),
            ],
          ),
        ),
        Expanded(
          flex: 1,
          child: Column(
            children: <Widget>[
              Text("Data de Nascimento:", style: TextStyle(fontWeight: FontWeight.bold),),
              Text("${ExamHandler.currentUser.birthday}"),
            ],
          ),
        ),
        Expanded(
          flex: 1,
          child: Container(),
        ),
        Expanded(
          flex: 1,
          child: InkWell(
            onTap: () => {
              _editProfile(context)
            },
            child: Text("Editar Perfil"),
          ),
        ),
        Expanded(
          flex: 1,
          child: InkWell(
            onTap: () => _logout(context),
            child: Text("Sair"),
          ),
        ),
        Expanded(
          flex: 1,
          child: Container(),
        ),
      ],
    );
  }
}
