import 'package:examapp/pages/examPage.dart';
import 'package:examapp/pages/loginPage.dart';
import 'package:flutter/material.dart';

class HomePage extends StatefulWidget {
  @override
  _HomePageState createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _currentItemNavigationIndex = 0;

  _updateItemNavigationIndex(int index) {
    setState(() {
      _currentItemNavigationIndex = index;
    });
  }

  _goToExam(context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => ExamPage()));
  }

  _logout(BuildContext context) {
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
        return Column(
          children: <Widget>[
            Center(
              child: Container(
                width: double.infinity,
                height: 100,
                child: Text("Gráfico de exames"),
              ),
            ),
            Container(
              height: 50,
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceAround,
              children: <Widget>[
                Text("Listagem de exames"),
                InkWell(
                  child: Text("+"),
                  onTap: () => _goToExam(context),
                )
              ],
            )
          ],
        );
      default:
        return Center(
          child: Column(
            children: <Widget>[
              Text("Perfil - TODO"),
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
