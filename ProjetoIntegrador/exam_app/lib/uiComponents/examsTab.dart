import 'package:examapp/pages/examPage.dart';
import 'package:examapp/uiComponents/examList.dart';
import 'package:flutter/material.dart';

class ExamTab extends StatelessWidget {
  _goToExam(context) {
    Navigator.push(
        context, MaterialPageRoute(builder: (context) => ExamPage()));
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      children: <Widget>[
        Expanded(
          flex: 1,
          child: Container(
            color: Colors.greenAccent,
            width: double.infinity,
            child: Text("Gráfico de exames"),
          ),
        ),
        Container(
          height: 10,
          color: Colors.amber,
        ),
        Container(
          color: Colors.blueAccent,
          child: Row(
            mainAxisAlignment: MainAxisAlignment.spaceAround,
            children: <Widget>[
              Text("Listagem de exames"),
              InkWell(
                child: Text("+"),
                onTap: () => _goToExam(context),
              )
            ],
          ),
        ),
        Expanded(flex: 2, child: ExamList()),
      ],
    );
  }
}
