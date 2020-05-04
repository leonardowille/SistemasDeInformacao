import 'package:examapp/examHandler.dart';
import 'package:examapp/pages/loginPage.dart';
import 'package:examapp/services/accountService.dart';
import 'package:examapp/uiComponents/examsTab.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {

  AccountService accountService = AccountService();

  int _currentItemNavigationIndex = 0;

  _updateItemNavigationIndex(int index) {
    setState(() {
      _currentItemNavigationIndex = index;
    });
  }

  _logout(BuildContext context) {
    accountService.logout();
    Navigator.pushAndRemoveUntil(context,
        MaterialPageRoute(builder: (context) => LoginPage()), (r) => false);
  }

  Widget _bottomNavigationBar() {
    return BottomNavigationBar(
      currentIndex: _currentItemNavigationIndex,
      onTap: _updateItemNavigationIndex,
      items: [
        BottomNavigationBarItem(
          title: Text("Exames"),
          icon: Icon(Icons.insert_drive_file),
        ),
        BottomNavigationBarItem(
          title: Text("Perfil"),
          icon: Icon(Icons.person),
        ),
      ],
    );
  }

  Widget _mainContent() {
    switch (_currentItemNavigationIndex) {
      case 0:
        return ExamTab();
      default:
        return Center(
          child: Column(
            children: <Widget>[
              Text("Perfil - TODO"),
              Text("Usuário: ${ExamHandler.currentUser.name}"),
              InkWell(
                onTap: () => _logout(context),
                child: Text("Logout"),
              )
            ],
          ),
        );
    }
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
          appBar: AppBar(),
          bottomNavigationBar: _bottomNavigationBar(),
          body: _mainContent()),
    );
  }
}
